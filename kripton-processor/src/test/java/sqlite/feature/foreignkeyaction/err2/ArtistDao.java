package sqlite.feature.foreignkeyaction.err2;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.foreignkeyaction.BaseDao;

@BindDao(Artist.class)
public interface ArtistDao extends BaseDao<Artist>  {

}
