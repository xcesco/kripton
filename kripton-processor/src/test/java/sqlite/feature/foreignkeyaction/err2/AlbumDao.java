package sqlite.feature.foreignkeyaction.err2;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.foreignkeyaction.BaseDao;

@BindDao(Album.class)
public interface AlbumDao extends BaseDao<Album> {

}
