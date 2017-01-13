package com.abubusoft.kripton.quickstart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.quickstart.model.Comment;
import com.abubusoft.quickstart.R;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartAsyncTask;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDaoFactory;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDataSource;
import com.abubusoft.kripton.quickstart.persistence.CommentDaoImpl;

import java.util.List;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommentAdapter mAdapter;

    BindQuickStartAsyncTask.Simple<List<Comment>> asyncTask = new BindQuickStartAsyncTask.Simple<List<Comment>>() {

        List<Comment> list;

        @Override
        public List<Comment> onExecute(BindQuickStartDataSource dataSource) throws Throwable {
            list = dataSource.getCommentDao().selectByPostId(postId);

            if (list.size() == 0) {
                list = QuickStartApplication.service.listComments(postId).execute().body();
                Logger.info("%s post downloaded for postId %s ", list.size(), postId);

                dataSource.execute(new BindQuickStartDataSource.SimpleTransaction() {

                    @Override
                    public boolean onExecute(BindQuickStartDaoFactory daoFactory) {
                        CommentDaoImpl dao = daoFactory.getCommentDao();

                        for (Comment item : list) {
                            Logger.info("Store comment %s", item.id);
                            dao.insert(item);
                        }
                        Logger.info("finished");
                        return true;
                    }
                });

                return dataSource.getCommentDao().selectByPostId(postId);
            } else {
                // user already downloaded
                return list;
            }


        }

        @Override
        public void onFinish(List<Comment> result) {
            mAdapter.update(result);
            mAdapter.notifyDataSetChanged();
        }
    };
    private DividerItemDecoration mDividerItemDecoration;
    private long postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        postId = (long) bundle.get("postId");

        setContentView(R.layout.activity_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_post);

        mAdapter = new CommentAdapter();
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
