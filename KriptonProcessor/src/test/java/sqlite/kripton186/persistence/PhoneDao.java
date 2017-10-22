package sqlite.kripton186.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.kripton186.model.PhoneNumber;


@BindDao(PhoneNumber.class)
public interface PhoneDao extends AbstractDao<PhoneNumber> {

    @BindSqlSelect(where = " number = ${number}")
    PhoneNumber selectByNumber(String number);

    @BindSqlSelect(orderBy = "contactName, number")
    List<PhoneNumber> selectAll();
}
