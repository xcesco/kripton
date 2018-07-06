package sqlite.feature.pkstring.case2;

import java.sql.Date;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

public interface DaoBase<E> {

	@BindSqlInsert
	void insert(E bean);
	
	@BindSqlInsert
	String insert1(E bean);
	
	@BindSqlInsert
	long insert2(E bean);
	
	@BindSqlInsert
	void insert3(Date year);
	
	@BindSqlInsert
	String insert4(Date year);
	
	@BindSqlInsert
	long insert5(Date year);
	
	@BindSqlUpdate(where="name=:bean.name")
	void update(E bean);
	
	@BindSqlUpdate(where="name=:bean.name")
	int update2(E bean);
	
	@BindSqlUpdate(where="name=:bean.name")
	long update3(E bean);
}
