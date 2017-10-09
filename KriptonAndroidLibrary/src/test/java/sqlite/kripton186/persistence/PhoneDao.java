package sqlite.kripton186.persistence;

import android.provider.ContactsContract;
import sqlite.kripton186.model.PhoneNumber;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import java.util.List;


/**
 * Created by 908099 on 16/02/2017.
 */
@BindDao(PhoneNumber.class)
public interface PhoneDao extends AbstractDao<PhoneNumber> {

    @BindSqlSelect(where = " number = ${number}")
    PhoneNumber selectByNumber(String number);

    @BindSqlSelect(orderBy = "contactName, number")
    List<PhoneNumber> selectAll();
}
