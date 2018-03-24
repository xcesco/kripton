	package com.abubusoft.kripton.android.sqlite;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSQLContext implements SQLContext {
	
	protected AbstractSQLContext(boolean session) {
		this.session=session;
	}
	
	private final boolean session;
	
	private final ThreadLocal<Set<String>> daoWithEvents = new ThreadLocal<Set<String>>() {

		@Override
		protected Set<String> initialValue() {
			return new HashSet<String>();
		}

	};

	@Override
	public void onSessionOpened() {		
		daoWithEvents.get().clear();
	}

	@Override
	public boolean isInSession() {
		return session;
	}
	
	@Override
	public void registrySQLEvent(String daoKey) {
		if (session) {
			daoWithEvents.get().add(daoKey);
		}
	}

	@Override
	public Set<String> onSessionClosed() {
		return daoWithEvents.get();		
	}
		
}
