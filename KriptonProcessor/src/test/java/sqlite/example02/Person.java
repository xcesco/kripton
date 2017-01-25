package sqlite.example02;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindTable()
public class Person {
  public long id;
  
  
  @BindColumn
  public String name;
  
  public String surname;
  public String birthCity;
  public Date birthDay;
}