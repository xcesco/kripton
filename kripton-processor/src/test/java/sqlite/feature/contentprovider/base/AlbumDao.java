package sqlite.feature.contentprovider.base;

import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;

@BindContentProviderPath(path = "albums")
@BindDao(Album.class)
public interface AlbumDao extends BaseDao<Album> {

}
