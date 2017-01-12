package com.abubusoft.kriptonquickstart;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kriptonquickstart.model.User;
import com.abubusoft.kriptonquickstart.persistence.BindQuickStartAsyncTask;
import com.abubusoft.kriptonquickstart.persistence.BindQuickStartDaoFactory;
import com.abubusoft.kriptonquickstart.persistence.BindQuickStartDataSource;
import com.abubusoft.kriptonquickstart.persistence.UserDaoImpl;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private DividerItemDecoration mDividerItemDecoration;

    public static boolean isNetworkAvailable(Context ct) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ct.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null &&
                activeNetworkInfo.isConnectedOrConnecting();

    }


    BindQuickStartAsyncTask.Simple<List<User>> asyncTask = new BindQuickStartAsyncTask.Simple<List<User>>() {

        List<User> userList;

        @Override
        public List<User> onExecute(BindQuickStartDataSource dataSource) throws Throwable {
            userList = dataSource.getUserDao().selectAll();

            if (userList.size() == 0) {
                Logger.info("Start user download");
                userList = QuickStartApplication.service.listUsers().execute().body();
                Logger.info("%s users downloaded ", userList.size());

                dataSource.execute(new BindQuickStartDataSource.SimpleTransaction() {

                    @Override
                    public boolean onExecute(BindQuickStartDaoFactory daoFactory) {
                        UserDaoImpl dao = daoFactory.getUserDao();

                        for (User item : userList) {
                            Logger.info("User %s is not yet stored", item.id);
                            dao.insert(item);
                        }
                        Logger.info("finished");
                        return true;
                    }
                });

                return dataSource.getUserDao().selectAll();
            } else {
                Logger.info("Already user downloaded");
                // user already downloaded
                return userList;
            }


        }

        @Override
        public void onFinish(List<User> result) {
            mAdapter.update(result);
            mAdapter.notifyDataSetChanged();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new UserAdapter();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);
        recyclerView.setAdapter(mAdapter);

        asyncTask.execute();
        // Qui
        //BindQuickStart

    }
}
