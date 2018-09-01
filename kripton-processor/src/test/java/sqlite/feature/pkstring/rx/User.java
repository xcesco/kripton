package sqlite.feature.pkstring.rx;

import java.util.UUID;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType(name = "users")
public class User {

    @BindSqlColumn(value = "userid", columnType = ColumnType.PRIMARY_KEY)
    private String id;

    @BindSqlColumn(value = "username")
    private String userName;

    public User(String userName) {
        id = UUID.randomUUID().toString();
        this.userName = userName;
    }

    public User(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}