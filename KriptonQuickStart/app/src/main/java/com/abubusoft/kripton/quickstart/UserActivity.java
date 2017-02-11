package com.abubusoft.kripton.quickstart;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abubusoft.kripton.android.BindAsyncTaskType;
import com.abubusoft.quickstart.R;
import com.abubusoft.kripton.quickstart.model.User;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartAsyncTask;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDaoFactory;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDataSource;
import com.abubusoft.kripton.quickstart.persistence.UserDaoImpl;

import java.util.List;

public class UserActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private DividerItemDecoration mDividerItemDecoration;

    public static boolean isNetworkAvailable(Context ct) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ct.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null &&
                activeNetworkInfo.isConnectedOrConnecting();
    }


    BindQuickStartAsyncTask.Simple<List<User>> asyncTask = new BindQuickStartAsyncTask.Simple<List<User>>(BindAsyncTaskType.READ_WRITE) {

        List<User> userList;

        @Override
        public List<User> onExecute(BindQuickStartDataSource dataSource) throws Throwable {
            userList = dataSource.getUserDao().selectAll();

            if (isNetworkAvailable(UserActivity.this) && userList.size() == 0) {
                userList = QuickStartApplication.service.listUsers().execute().body();
                dataSource.execute(new BindQuickStartDataSource.SimpleTransaction() {

                    @Override
                    public boolean onExecute(BindQuickStartDaoFactory daoFactory) {
                        UserDaoImpl dao = daoFactory.getUserDao();

                        for (User item : userList) {
                            dao.insert(item);
                        }
                        return true;
                    }
                });

                return dataSource.getUserDao().selectAll();
            } else {
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
        setContentView(R.layout.activity_user);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new UserAdapter();
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
