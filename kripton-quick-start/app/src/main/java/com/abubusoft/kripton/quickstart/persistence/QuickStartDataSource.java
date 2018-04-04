package com.abubusoft.kripton.quickstart.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 04/01/2017.
 */
@BindDataSource(daoSet={UserDao.class, PostDao.class, CommentDao.class, TodoDao.class, AlbumDao.class, PhotoDao.class}, fileName = "kripton.quickstart.db", asyncTask = true)
public interface QuickStartDataSource {
}
