package sqlite.feature.performance.simple;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.ArrayList;

/**
 * Created by xcesco on 23/09/2017.
 */
@BindDao(SimpleAddressItem.class)
public interface SimpleAddressDao {

    @BindSqlSelect(where="id=${id}")
    SimpleAddressItem selectById(long id);

    @BindSqlDelete
    void deleteAll();

    @BindSqlSelect
    ArrayList<SimpleAddressItem> selectAll();

    @BindSqlInsert
    void insert(SimpleAddressItem bean);
}
