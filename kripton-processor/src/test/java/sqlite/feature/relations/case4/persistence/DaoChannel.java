package sqlite.feature.relations.case4.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlChildSelect;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.relations.case4.model.Channel;

@BindDao(Channel.class)
public interface DaoChannel extends DaoBase<Channel> {
	
	@BindSqlSelect(childrenSelects={
			@BindSqlChildSelect(field="articles", method = "selectByChannel")
	})
	List<Channel> selectAll();
}
