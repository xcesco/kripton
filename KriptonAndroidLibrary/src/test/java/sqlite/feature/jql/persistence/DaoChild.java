package sqlite.feature.jql.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

import sqlite.feature.jql.entities.Child;


@BindDao(Child.class)
public interface DaoChild extends DaoBean<Child> {

	@BindSqlSelect(jql = "select * from Child where parentId in (select id from Person where id=${parentId})")
	public List<Child> selectByParent(long parentId);
	
	@BindSqlSelect(jql = "select count(*) from Child where parentId in (select id from Person where id=${parentId})")
	public int selectByParent2(long parentId);
	
	@BindSqlSelect(where="parentId=${parentId}")
	public List<Child> selectByParentId(long parentId);

	// @BindSqlSelect(where = "id=${id}")
	// public List<Child> selectAll(long id);
	
	@BindSqlInsert(jql="insert into Child (name, parentId) select name, parentId from Child where id=${parentId} or id=${test} or id=${aliasParentId}")
	public void insertByCopy(long parentId, long aliasParentId,@BindSqlParam("test") long parent);
	
	@BindSqlInsert(jql="insert into Child (name, parentId) values (${bean.name}, ${bean.parentId})")
	public void insertByCopy3(Child bean);
	
	@BindSqlInsert
	public int insertByCopy(long parentId, String name);
	
	@BindSqlUpdate(jql="update or replace Child set name=${name} where parentId=${a}")
	public void updateJQL(@BindSqlParam("a") long parentId, String name);
	
	@BindSqlUpdate(jql="update or replace Child set parentId=${parentId}, name=(select id from Person where id=${parentId} )  where parentId=${parentId}")
	public void updateJQL2(long parentId);

}
