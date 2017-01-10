package com.abubusoft.kriptonquickstart;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kriptonquickstart.model.User;
import com.abubusoft.kriptonquickstart.persistence.BindQuickStartAsyncTask;
import com.abubusoft.kriptonquickstart.persistence.BindQuickStartDaoFactory;
import com.abubusoft.kriptonquickstart.persistence.BindQuickStartDataSource;
import com.abubusoft.kriptonquickstart.persistence.UserDaoImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static boolean isNetworkAvailable(Context ct) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ct.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null &&
                activeNetworkInfo.isConnectedOrConnecting();

    }


    BindQuickStartAsyncTask.Simple<List<User>> asyncTask = new BindQuickStartAsyncTask.Simple<List<User>>() {
        @Override
        public List<User> onExecute(BindQuickStartDataSource dataSource) throws Throwable {
            final List<User> userList = QuickStartApplication.service.listUsers().execute().body();
            Logger.info("%s users downloaded ", userList.size());

            dataSource.execute(new BindQuickStartDataSource.SimpleTransaction() {

                @Override
                public boolean onExecute(BindQuickStartDaoFactory daoFactory) {
                    UserDaoImpl dao = daoFactory.getUserDao();

                    for (User item : userList) {
                        if (dao.selectById(item.id) == null) {
                            Logger.info("Post %s is not yet stored", item.id);
                            dao.insert(item);
                        } else {
                            Logger.info("Post %s is already stored", item.id);
                        }
                    }
                    Logger.info("finished");
                    return true;
                }
            });

            return userList;
        }

        @Override
        public void onFinish(List<User> result) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asyncTask.execute();
        // Qui
        //BindQuickStart

    }
}
