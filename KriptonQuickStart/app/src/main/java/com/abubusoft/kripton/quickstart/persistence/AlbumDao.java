package com.abubusoft.kripton.quickstart.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.quickstart.model.Album;
import com.abubusoft.kripton.quickstart.model.Post;

import java.util.List;

/**
 * Created by xcesco on 04/01/2017.
 */
@BindDao(Album.class)
public interface AlbumDao {
    @BindSqlInsert(includePrimaryKey = true)
    void insert(Album bean);

    @BindSqlSelect(where="userId = ${value}")
    List<Album> selectByUserId(@BindSqlParam("value") long userId);

    @BindSqlSelect(where="id = ${value}")
    Album selectOneByUserId(@BindSqlParam("value") long userId);

}
