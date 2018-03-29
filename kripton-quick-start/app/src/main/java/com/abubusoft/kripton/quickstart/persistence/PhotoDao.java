package com.abubusoft.kripton.quickstart.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.quickstart.model.Photo;
import com.abubusoft.kripton.quickstart.model.Post;

import java.util.List;

/**
 * Created by xcesco on 04/01/2017.
 */
@BindDao(Photo.class)
public interface PhotoDao {
    @BindSqlInsert(includePrimaryKey = true)
    void insert(Photo bean);

    @BindSqlSelect(where="albumId = ${value}")
    List<Photo> selectByUserId(@BindSqlParam("value") long albumId);

    @BindSqlSelect(where="id = ${value}")
    Photo selectOneByUserId(@BindSqlParam("value") long albumId);

}
