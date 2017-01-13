package com.abubusoft.kripton.quickstart;

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
                Logger.info("%s post downloaded for userId %s ", postList.size(), userId);

                dataSource.execute(new BindQuickStartDataSource.SimpleTransaction() {

                    @Override
                    public boolean onExecute(BindQuickStartDaoFactory daoFactory) {
                        PostDaoImpl dao = daoFactory.getPostDao();

                        for (Post item : postList) {
                            Logger.info("Store post %s", item.id);
                            dao.insert(item);
                        }
                        Logger.info("finished");
                        return true;
                    }
                });

                return dataSource.getPostDao().selectByUserId(userId);
            } else {
                // user already downloaded
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
        userId = (long) bundle.get("userId");

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

        asyncTask.execute();
    }

}
