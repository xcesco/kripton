package com.abubusoft.kripton.quickstart;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.quickstart.network.NetworkClient;
import com.abubusoft.kripton.quickstart.service.QuickStartService;
import com.abubusoft.kripton.retrofit2.KriptonBinderConverterFactory;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDaoFactory;
import com.abubusoft.kripton.quickstart.persistence.BindQuickStartDataSource;

import retrofit2.Retrofit;

/**
 * Created by xcesco on 04/01/2017.
 */

public class QuickStartApplication extends Application {

    public static QuickStartService service;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .client(NetworkClient.instance())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(KriptonBinderConverterFactory.create())
                .build();

        service = retrofit.create(QuickStartService.class);

        KriptonLibrary.init(this);
        BindQuickStartDataSource.instance().setOnDatabaseUpdateListener(new AbstractDataSource.OnDatabaseListener() {
            @Override
            public void onUpdate(SQLiteDatabase db, int oldVersion, int newVersion, boolean upgrade) {
                // additional code after database upgrade/downgrade
            }

            @Override
            public void onCreate(SQLiteDatabase database) {
                // after creation of database
            }

            @Override
            public void onConfigure(SQLiteDatabase database) {
                // after configuration of database
            }
        });
    }
}
