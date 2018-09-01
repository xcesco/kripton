package sqlite.feature.kotlin.immutable;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import android.arch.lifecycle.MutableLiveData;

@BindDao(Article.class)
public interface DaoArticle extends DaoBase<Article> {

    @BindSqlUpdate(where = "id=${id}")
    void update(long id, long channelId, boolean read);

    @BindSqlSelect
    MutableLiveData<List<Article>> selectByChannel(@BindSqlDynamicWhere String where);

    @BindSqlSelect(where = "channelId=${channelId}")
    List<Article> selectByChannelUd(long channelId);

    @BindSqlSelect(where="channelId=${channelId} AND guid=${guid}")
    Article selectByGuid(long channelId, String guid);
}
