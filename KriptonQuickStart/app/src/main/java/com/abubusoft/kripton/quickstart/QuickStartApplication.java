package com.abubusoft.kripton.quickstart;

import android.app.Application;

import com.abubusoft.kripton.android.KriptonLibrary;
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

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(KriptonBinderConverterFactory.create()).build();

        service = retrofit.create(QuickStartService.class);
        KriptonLibrary.init(this);
       BindQuickStartDataSource.instance().execute(new BindQuickStartDataSource.Transaction() {
           @Override
           public void onError(Throwable e) {

           }

           @Override
           public boolean onExecute(BindQuickStartDaoFactory daoFactory) {
               return false;
           }
       });

    }


}
