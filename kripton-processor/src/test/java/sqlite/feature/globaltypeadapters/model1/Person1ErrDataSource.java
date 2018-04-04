package sqlite.feature.globaltypeadapters.model1;

import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindSqlAdapter;

import sqlite.feature.globaltypeadapters.DaoPerson;

@BindDataSource(daoSet = { DaoPerson.class }, typeAdapters = {
		@BindSqlAdapter(adapter = Date2Long.class), @BindSqlAdapter(adapter = Date2Long.class) }, fileName = "person.db")
public interface Person1ErrDataSource {

}
