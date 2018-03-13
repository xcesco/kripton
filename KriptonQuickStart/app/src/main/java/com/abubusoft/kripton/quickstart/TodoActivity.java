package com.abubusoft.kripton.quickstart;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.abubusoft.kripton.android.BindAsyncTaskType;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.quickstart.model.Todo;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartAsyncTask;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDataSource;
import com.abubusoft.kripton.quickstart.persistence.TodoDaoImpl;
import com.abubusoft.quickstart.R;

import java.util.List;

public class TodoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TodoAdapter mAdapter;

    BindQuickStartAsyncTask.Simple<List<Todo>> asyncTask = new BindQuickStartAsyncTask.Simple<List<Todo>>(BindAsyncTaskType.READ_WRITE) {

        List<Todo> list;

        @Override
        public List<Todo> onExecute(BindQuickStartDataSource dataSource) throws Throwable {
            list = dataSource.getTodoDao().selectByUserId(userId);

            if (list.size() == 0) {
                list = QuickStartApplication.service.listTodos(userId).execute().body();
                dataSource.execute(daoFactory -> {
                    TodoDaoImpl dao = daoFactory.getTodoDao();

                    for (Todo item : list) {
                        dao.insert(item);
                    }
                    return TransactionResult.COMMIT;
                });

                return dataSource.getTodoDao().selectByUserId(userId);
            } else {
                return list;
            }


        }

        @Override
        public void onFinish(List<Todo> result) {
            mAdapter.update(result);
            mAdapter.notifyDataSetChanged();
        }
    };
    private DividerItemDecoration mDividerItemDecoration;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userId = (long) bundle.get("userId");
            BindApplicationPreferences state = BindApplicationPreferences.instance();
            state.edit().putUserId(userId).commit();
        } else {
            userId = BindApplicationPreferences.instance().userId();
        }

        setContentView(R.layout.activity_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_todo);

        mAdapter = new TodoAdapter();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}
