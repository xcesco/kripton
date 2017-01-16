package com.abubusoft.kripton.quickstart;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.quickstart.R;
import com.abubusoft.kripton.quickstart.model.Post;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartAsyncTask;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDaoFactory;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDataSource;
import com.abubusoft.kripton.quickstart.persistence.PostDaoImpl;

import java.util.List;

public class PostActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter mAdapter;

    BindQuickStartAsyncTask.Simple<List<Post>> asyncTask = new BindQuickStartAsyncTask.Simple<List<Post>>() {

        List<Post> postList;

        @Override
        public List<Post> onExecute(BindQuickStartDataSource dataSource) throws Throwable {
            postList = dataSource.getPostDao().selectByUserId(userId);

            if (postList.size() == 0) {
                postList = QuickStartApplication.service.listPosts(userId).execute().body();
                dataSource.execute(new BindQuickStartDataSource.SimpleTransaction() {

                    @Override
                    public boolean onExecute(BindQuickStartDaoFactory daoFactory) {
                        PostDaoImpl dao = daoFactory.getPostDao();
                        for (Post item : postList) {
                            dao.insert(item);
                        }
                        return true;
                    }
                });

                return dataSource.getPostDao().selectByUserId(userId);
            } else {
                return postList;
            }
        }

        @Override
        public void onFinish(List<Post> result) {
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
        if (bundle!=null) {
            userId = (long) bundle.get("userId");
            BindApplicationPreferences.instance().edit().putUserId(userId).commit();
        }
        else
        {
            userId=BindApplicationPreferences.instance().userId();
        }

        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_post);

        mAdapter = new PostAdapter();
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
