package com.abubusoft.kriptonquickstart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abubusoft.kripton.android.Logger;

import java.io.IOException;
import java.util.List;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    BindQuickStartAsyncTask<Void, Void, List<User>> asyncTask=new BindQuickStartAsyncTask<Void, Void, List<User>>() {
        @Override
        public List<User> onExecute(BindQuickStartDataSource dataSource) {
            try {
                final List<User> userList = QuickStartApplication.service.list().execute().body();
                Logger.info("%s users downloaded ", userList.size());

                dataSource.execute(new BindQuickStartDataSource.Transaction() {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public boolean onExecute(BindQuickStartDaoFactory daoFactory) {
                        UserDaoImpl dao = daoFactory.getUserDao();

                        for (User item : userList) {
                            if (dao.isPresent(item.id) == null) {
                                Logger.info("User %s is not yet stored", item.id);
                                dao.insert(item);
                            } else {
                                Logger.info("User %s is already stored", item.id);
                            }
                        }
                        Logger.info("finished");
                        return true;
                    }
                });

                return userList;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
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
