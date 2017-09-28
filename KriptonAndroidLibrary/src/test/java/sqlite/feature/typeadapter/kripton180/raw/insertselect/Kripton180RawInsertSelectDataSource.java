package sqlite.feature.typeadapter.kripton180.raw.insertselect;

import com.abubusoft.kripton.android.annotation.BindDataSource;

import sqlite.feature.typeadapter.kripton180.bean.insertselect.EmployeeBeanInsertSelectDao;

@BindDataSource(daoSet={EmployeeRawInsertSelectDao.class}, fileName="kripton180.db")
public interface Kripton180RawInsertSelectDataSource {

}
