package sqlite.feature.foreignkeyaction.err1;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.foreignkeyaction.BaseDao;

@BindDao(Album.class)
public interface AlbumDao extends BaseDao<Album> {

}
