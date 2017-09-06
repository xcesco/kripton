package sqlite.feature.jql.kripton164;

import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindTable(name="students")
public class CollegeStudent {
    public String firstName;

    public String surname;

    public long id;
}