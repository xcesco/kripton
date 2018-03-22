package com.abubusoft.kripton.android.sqlite;

public abstract class AbstractSQLContext implements SQLContext {
	
	protected AbstractSQLContext(boolean session) {
		this.session=session;
	}
	
	private final boolean session;

	@Override
	public void onSessionOpened() {		
		
	}

	@Override
	public boolean isSessionOpened() {
		return session;
	}

	@Override
	public void onSessionClosed() {
		// TODO Auto-generated method stub
		
	}

		
}
