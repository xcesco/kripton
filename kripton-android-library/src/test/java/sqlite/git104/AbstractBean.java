package sqlite.git104;

import java.time.ZonedDateTime;

import com.abubusoft.kripton.android.annotation.BindSqlColumn;

public abstract class AbstractBean {
    protected final long id;

    @BindSqlColumn(nullable = false)
    protected ZonedDateTime updateTime;

    public AbstractBean(long id, ZonedDateTime updateTime) {
        this.id = id;
        this.updateTime = updateTime;
    }

    public long getId() {
        return id;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }
}