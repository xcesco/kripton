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
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.quickstart.model.Album;
import com.abubusoft.kripton.quickstart.persistence.AlbumDaoImpl;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartAsyncTask;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDaoFactory;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDataSource;
import com.abubusoft.quickstart.R;

import java.util.List;

public class AlbumActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumAdapter mAdapter;

    BindQuickStartAsyncTask.Simple<List<Album>> asyncTask = new BindQuickStartAsyncTask.Simple<List<Album>>(BindAsyncTaskType.READ_WRITE) {

        List<Album> list;

        @Override
        public List<Album> onExecute(BindQuickStartDataSource dataSource) throws Throwable {
            list = dataSource.getAlbumDao().selectByUserId(userId);

            if (list.size() == 0) {
                list = QuickStartApplication.service.listAlbums(userId).execute().body();
                dataSource.execute(new BindQuickStartDataSource.SimpleTransaction() {

                    @Override
                    public boolean onExecute(BindQuickStartDaoFactory daoFactory) {
                        AlbumDaoImpl dao = daoFactory.getAlbumDao();

                        for (Album item : list) {
                            dao.insert(item);
                        }
                        return true;
                    }
                });

                return dataSource.getAlbumDao().selectByUserId(userId);
            } else {
                return list;
            }
        }

        @Override
        public void onFinish(List<Album> result) {
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

        setContentView(R.layout.activity_album);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_album);

        mAdapter = new AlbumAdapter();
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
