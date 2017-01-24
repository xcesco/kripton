package sqlite.example02;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Person {
  public long id;
  public String name;
  public String surname;
  public String birthCity;
  public Date birthDay;
}