/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.android.sqlite;

import java.util.HashSet;
import java.util.Set;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractSQLContext.
 */
public abstract class AbstractSQLContext implements SQLContext {

	/**
	 * Instantiates a new abstract SQL context.
	 *
	 * @param session the session
	 */
	protected AbstractSQLContext(boolean session) {
		this.session = session;
	}

	/** The session. */
	private final boolean session;

	/** The dao with events. */
	private final ThreadLocal<Set<Integer>> daoWithEvents = new ThreadLocal<Set<Integer>>() {

		@Override
		protected Set<Integer> initialValue() {
			return new HashSet<Integer>();
		}

	};

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#onSessionOpened()
	 */
	@Override
	public void onSessionOpened() {
		daoWithEvents.get().clear();
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#isInSession()
	 */
	@Override
	public boolean isInSession() {
		return session;
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#registrySQLEvent(int)
	 */
	@Override
	public void registrySQLEvent(int daoKey) {
		daoWithEvents.get().add(daoKey);
	}

	/* (non-Javadoc)
	 * @see com.abubusoft.kripton.android.sqlite.SQLContext#onSessionClosed()
	 */
	@Override
	public Set<Integer> onSessionClosed() {
		return daoWithEvents.get();
	}

}
