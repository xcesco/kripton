package sqlite.includeFields;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(Person.class)
public interface PersonDAO {
	@BindSqlSelect(value={"name", "id"}, where="typeName=${bean.name}",orderBy="name")
	List<Person> selectIncludeOne(Person bean);
	
	@BindSqlSelect(excludedFields={"name", "id"}, orderBy="name")
	List<Person> selectExcludeOne();
	
	@BindSqlInsert(value={"name", "id"})
	void insertIncludeOne(Person bean);
	
	@BindSqlInsert(excludedFields={"name", "id"})
	void insertExcludeOne(Person bean);	
	
	@BindSqlUpdate(value={"name", "id"})
	void updateIncludeOne(Person bean);
	
	@BindSqlUpdate(excludedFields={"name", "id"})
	void updateExcludeOne(Person bean);
	
	@BindSqlDelete
	void deleteIncludeOne(Person bean);
	
	@BindSqlDelete
	void deleteExcludeOne(Person bean);
	
	

}