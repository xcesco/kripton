package sqlite.quickstart.model;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

/**
 * Created by 908099 on 13/01/2017.
 */

@BindType
public class Comment {

    @BindColumn(foreignKey = Post.class)
    public long postId;

    public long id;

    public String name;

    public String email;

    public String body;
}
