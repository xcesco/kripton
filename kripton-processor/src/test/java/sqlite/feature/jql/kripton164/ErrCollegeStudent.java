package sqlite.feature.jql.kripton164;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindTable(name="err_students")
public class ErrCollegeStudent {
    public String firstName;

    @BindColumn("err_surname")
    public String surname;

    public long id;
}