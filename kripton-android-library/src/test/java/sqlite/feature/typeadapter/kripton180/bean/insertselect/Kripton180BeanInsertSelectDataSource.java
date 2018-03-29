package sqlite.feature.typeadapter.kripton180.bean.insertselect;

import com.abubusoft.kripton.android.annotation.BindDataSource;

import sqlite.feature.typeadapter.kripton180.bean.insertselect.EmployeeBeanInsertSelectDao;

@BindDataSource(daoSet={EmployeeBeanInsertSelectDao.class}, fileName="kripton180.db", rx=true)
public interface Kripton180BeanInsertSelectDataSource {

}
