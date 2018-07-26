package sqlite.feature.kotlin.immutable;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlChildSelect;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;


@BindDao(RssFeed.class)
public interface DaoRss extends DaoBase<RssFeed> {

    @BindSqlSelect(where="uid=${uid}", childrenSelects={@BindSqlChildSelect(field="channels", method="selectByRssFeedId")})
    RssFeed selectOne(String uid);

}
