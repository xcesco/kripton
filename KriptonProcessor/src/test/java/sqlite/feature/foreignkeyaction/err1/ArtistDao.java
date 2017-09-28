package sqlite.feature.foreignkeyaction.err1;

import com.abubusoft.kripton.android.annotation.BindDao;

import sqlite.feature.foreignkeyaction.BaseDao;

@BindDao(Artist.class)
public interface ArtistDao extends BaseDao<Artist>  {

}
