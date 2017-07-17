package sqlite.featJQL.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.featJQL.entities.Child;

@BindDao(Child.class)
public interface DaoChild extends DaoBean<Child> {

	@BindSqlSelect(jql = "select * from Child where parentId in (select id from Person where id=${parentId})")
	public List<Child> selectByParent(long parentId);
	
	@BindSqlSelect(where="parentId=${parentId}")
	public List<Child> selectByParentId(long parentId);

	// @BindSqlSelect(where = "id=${id}")
	// public List<Child> selectAll(long id);
	
	@BindSqlInsert(jql="insert into Child (name, parentId) select name, parentId from Child where id=${parentId} or id=${aliasParentId} or id=${test}")
	public void insertByCopy(long parentId, long aliasParentId,@BindSqlParam("test") long parent);
	
	@BindSqlInsert
	public int insertByCopy(long parentId, String name);

}
