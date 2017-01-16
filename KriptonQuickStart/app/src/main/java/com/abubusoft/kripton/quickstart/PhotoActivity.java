package com.abubusoft.kripton.quickstart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.quickstart.model.Photo;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartAsyncTask;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDaoFactory;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDataSource;
import com.abubusoft.kripton.quickstart.persistence.PhotoDaoImpl;
import com.abubusoft.quickstart.R;

import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhotoAdapter mAdapter;

    BindQuickStartAsyncTask.Simple<List<Photo>> asyncTask = new BindQuickStartAsyncTask.Simple<List<Photo>>() {

        List<Photo> list;

        @Override
        public List<Photo> onExecute(BindQuickStartDataSource dataSource) throws Throwable {
            list = dataSource.getPhotoDao().selectByUserId(albumId);

            if (list.size() == 0) {
                list = QuickStartApplication.service.listPhotos(albumId).execute().body();

                dataSource.execute(new BindQuickStartDataSource.SimpleTransaction() {

                    @Override
                    public boolean onExecute(BindQuickStartDaoFactory daoFactory) {
                        PhotoDaoImpl dao = daoFactory.getPhotoDao();
                        for (Photo item : list) {
                            dao.insert(item);
                        }
                        return true;
                    }
                });

                return dataSource.getPhotoDao().selectByUserId(albumId);
            } else {
                return list;
            }
        }

        @Override
        public void onFinish(List<Photo> result) {
            mAdapter.update(result);
            mAdapter.notifyDataSetChanged();
        }
    };

    private long albumId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            albumId = (long) bundle.get("albumId");
            BindApplicationPreferences.instance().edit().putAlbumId(albumId).commit();
        }
        else
        {
            albumId =BindApplicationPreferences.instance().albumId();
        }

        setContentView(R.layout.activity_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_photo);

        mAdapter = new PhotoAdapter();
        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        asyncTask.execute();
    }

}
