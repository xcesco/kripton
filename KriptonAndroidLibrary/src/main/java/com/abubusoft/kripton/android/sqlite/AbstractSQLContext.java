package com.abubusoft.kripton.android.sqlite;

import java.util.HashSet;

public abstract class AbstractSQLContext implements SQLContext {

	/**
	 * in a transation or shared connection, it gives the instance of DAO has
	 * modified its table.
	 */
	protected HashSet<AbstractDao> daosSubjectToModification = new HashSet<>();
	
	@Override
	public void onSessionOpened() {
		this.daosSubjectToModification.clear();
		
	}

	@Override
	public <D extends AbstractDao> void onSessionSQLEvent(D dao) {
		this.daosSubjectToModification.add(dao);
	}

	@Override
	public void onSessionClosed() {
		// TODO Auto-generated method stub
		
	}
}
