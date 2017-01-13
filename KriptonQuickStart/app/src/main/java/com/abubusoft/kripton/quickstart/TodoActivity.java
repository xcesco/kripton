package com.abubusoft.kripton.quickstart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.quickstart.model.Todo;
import com.abubusoft.quickstart.R;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartAsyncTask;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDaoFactory;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDataSource;
import com.abubusoft.kripton.quickstart.persistence.TodoDaoImpl;

import java.util.List;

public class TodoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TodoAdapter mAdapter;

    BindQuickStartAsyncTask.Simple<List<Todo>> asyncTask = new BindQuickStartAsyncTask.Simple<List<Todo>>() {

        List<Todo> list;

        @Override
        public List<Todo> onExecute(BindQuickStartDataSource dataSource) throws Throwable {
            list = dataSource.getTodoDao().selectByUserId(userId);

            if (list.size() == 0) {
                list = QuickStartApplication.service.listTodos(userId).execute().body();
                Logger.info("%s todo downloaded for userId %s ", list.size(), userId);

                dataSource.execute(new BindQuickStartDataSource.SimpleTransaction() {

                    @Override
                    public boolean onExecute(BindQuickStartDaoFactory daoFactory) {
                        TodoDaoImpl dao = daoFactory.getTodoDao();

                        for (Todo item : list) {
                            Logger.info("Store todo %s", item.id);
                            dao.insert(item);
                        }
                        Logger.info("finished");
                        return true;
                    }
                });

                return dataSource.getTodoDao().selectByUserId(userId);
            } else {
                // user already downloaded
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
        userId = (long) bundle.get("userId");

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

        asyncTask.execute();
    }

}
