package sqlite.kripton296.model;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindIndex;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;

import androidx.annotation.NonNull;

@BindSqlType(name = "movie",
        indexes = {@BindIndex("title"), @BindIndex("directorId")})
public class Movie {

    //@BindSqlColumn(columnType = ColumnType.PRIMARY_KEY, value = "mid")
    public long id;

    @NonNull
    @BindSqlColumn(value = "title")
    public String title;

    @BindSqlColumn(parentEntity = Director.class, onDelete = ForeignKeyAction.CASCADE)
    public long directorId;

    public Movie(@NonNull String title, long directorId) {
        this.title = title;
        this.directorId = directorId;
    }

    public Movie() {

    }
}