package sqlite.feature.contentprovider.base;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 02/10/2017.
 */
@BindContentProvider(authority = "com.abubusoft.kripton.example")
@BindDataSource(daoSet = { ArtistDao.class }, fileName = "artists.db")
public interface ArtistsDataSource {
}
