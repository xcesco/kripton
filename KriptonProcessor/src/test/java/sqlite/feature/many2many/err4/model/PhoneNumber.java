package sqlite.feature.many2many.err4.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class PhoneNumber {

    public long id;

    public ActionType action;

    public String number;

    public String countryCode;

    public String contactName;

    public String contactId;
}
