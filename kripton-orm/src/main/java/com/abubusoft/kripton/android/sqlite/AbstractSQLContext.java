package com.abubusoft.kripton.android.sqlite;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSQLContext implements SQLContext {

	protected AbstractSQLContext(boolean session) {
		this.session = session;
	}

	private final boolean session;

	private final ThreadLocal<Set<Integer>> daoWithEvents = new ThreadLocal<Set<Integer>>() {

		@Override
		protected Set<Integer> initialValue() {
			return new HashSet<Integer>();
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
	public void registrySQLEvent(int daoKey) {
		daoWithEvents.get().add(daoKey);
	}

	@Override
	public Set<Integer> onSessionClosed() {
		return daoWithEvents.get();
	}

}
