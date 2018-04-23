package sqlite.samples.chat.model;

import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class User extends Entity {
    public String username;
}
