package sqlite.feature.join.model;

import com.abubusoft.kripton.android.annotation.BindTable;

/**
 * Created by xcesco on 20/02/2018.
 */
@BindTable
public class User extends Entity {
    public String name;

    public String lastName;

    public int age;
}
