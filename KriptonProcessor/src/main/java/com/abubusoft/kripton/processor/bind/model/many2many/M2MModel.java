package com.abubusoft.kripton.processor.bind.model.many2many;

import java.util.ArrayList;
import java.util.List;

public class M2MModel {

	List<M2MEntity> entities=new ArrayList<M2MEntity>();

	public List<M2MEntity> getEntities() {
		return entities;
	}

	public void entityAdd(M2MEntity entity) {
		entities.add(entity);		
	}
		
	
	
}
