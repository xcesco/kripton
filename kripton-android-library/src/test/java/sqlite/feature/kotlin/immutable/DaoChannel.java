package sqlite.feature.kotlin.immutable;

import androidx.lifecycle.LiveData;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlChildSelect;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;

@BindDao(Channel.class)
public interface DaoChannel extends DaoBase<Channel> {

    @BindSqlSelect(where="rssFeedId=${rssFeedId}", childrenSelects = {@BindSqlChildSelect(field = "articles", method = "selectByChannelUd")})
    List<Channel> selectByRssFeedId(long rssFeedId);

    @BindSqlSelect(where="rssFeedId=${rssFeedId}")
    Channel selectOneByRssFeedId(long rssFeedId);

    @BindSqlSelect
    LiveData<Channel> selectOne();
}
