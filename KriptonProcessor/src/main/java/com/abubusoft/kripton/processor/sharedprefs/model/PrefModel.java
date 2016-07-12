package com.abubusoft.kripton.processor.sharedprefs.model;

import java.util.ArrayList;
import java.util.List;

public class PrefModel {
	protected List<PrefEntity> entities = new ArrayList<PrefEntity>();

	public List<PrefEntity> getEntities() {
		return entities;
	}

	public void entityAdd(PrefEntity item) {
		entities.add(item);
	}


	public void clear() {
		entities.clear();
	}

	public int count() {
		return entities.size();
	}
}
