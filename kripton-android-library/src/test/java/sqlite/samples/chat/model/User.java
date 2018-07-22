package sqlite.samples.chat.model;

import java.util.SortedMap;
import java.util.SortedSet;

import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType
public class User extends Entity {
    public String username;
    
    public SortedMap<String, String> sortedMap;
    
    public SortedSet<String> sortedSet;
}
