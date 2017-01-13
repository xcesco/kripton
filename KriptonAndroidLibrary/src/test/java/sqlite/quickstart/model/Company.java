package sqlite.quickstart.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Company {

    public String name;
    public String catchPhrase;
    public String bs;

    @Override
    public String toString() {
        return name;
    }
}