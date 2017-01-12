package com.abubusoft.kriptonquickstart;

import android.app.Application;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.retrofit2.KriptonBinderConverterFactory;
import com.abubusoft.kriptonquickstart.persistence.BindQuickStartDaoFactory;
import com.abubusoft.kriptonquickstart.persistence.BindQuickStartDataSource;
import com.abubusoft.kriptonquickstart.service.QuickStartService;

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
