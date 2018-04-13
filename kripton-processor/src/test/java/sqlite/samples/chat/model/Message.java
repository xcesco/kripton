package sqlite.samples.chat.model;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Message extends Entity {
    public String content;

    @BindColumn(parentEntity = User.class)
    public long senderId;

    @BindColumn(parentEntity = User.class)
    public long receiverId;
}
