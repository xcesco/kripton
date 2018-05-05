package sqlite.samples.chat.model;

import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Message extends Entity {
    public String content;

    @BindSqlColumn(parentEntity = User.class)
    public long senderId;

    @BindSqlColumn(parentEntity = User.class)
    public long receiverId;
}
