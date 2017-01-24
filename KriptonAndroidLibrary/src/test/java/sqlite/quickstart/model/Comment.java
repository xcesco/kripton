package sqlite.quickstart.model;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
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
