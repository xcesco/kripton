package com.abubusoft.kripton.processor.kripton49.persistence;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDelete;
import com.abubusoft.kripton.android.annotation.BindInsert;
import com.abubusoft.kripton.android.annotation.BindSelect;
import com.abubusoft.kripton.android.annotation.BindUpdate;
import com.abubusoft.kripton.processor.kripton49.entities.Bean01Entity;

@BindDao(Bean01Entity.class)
public interface DaoBean01 extends BaseDao<Bean01Entity>  {
	@BindSelect(where="id=${id}")
	Bean01Entity selectOne(Long id);
	
	@BindUpdate(where="id=${id}")
	long updateOne(String text, Long id);
	
	@BindDelete(where="id=${id}")
	long deleteOne(Long id);
	
	@BindInsert()
	long insertOne(Long id);
	
}