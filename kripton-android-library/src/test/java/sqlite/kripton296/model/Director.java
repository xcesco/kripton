package sqlite.kripton296.model;


import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindIndex;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

import androidx.annotation.NonNull;

@BindSqlType(name = "director")
public class Director {
    @BindSqlColumn(columnType = ColumnType.PRIMARY_KEY)
    public long did;

    @NonNull
    @BindSqlColumn(value="full_name", columnType = ColumnType.UNIQUE)
    public String fullName;

    @BindSqlColumn(enabled = false)
    public int age;

}