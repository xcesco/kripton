package sqlite.kripton186.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.kripton186.model.PrefixConfig;

/**
 * Created by xcesco on 18/02/2017.
 */
@BindDao(PrefixConfig.class)
public interface PrefixConfigDao extends AbstractDao<PrefixConfig> {

    @BindSqlSelect
    PrefixConfig selectOne();

    @BindSqlUpdate(where ="id = ${bean.id} ")
    int update(PrefixConfig bean);


}
