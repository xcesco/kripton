package sqlite.feature.kotlin.immutable2;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;


@BindDao(RssFeed.class)
public interface DaoRss extends DaoBase<RssFeed> {

    @BindSqlSelect(where="uid=${uid}")
    RssFeed selectOne(String uid);

}
