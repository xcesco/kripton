package sqlite.feature.contentprovider.base;

import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;

/**
 * Created by xcesco on 02/10/2017.
 */
@BindContentProviderPath(path = "artists")
@BindDao(Artist.class)
public interface ArtistDao extends BaseDao<Artist> {

}
