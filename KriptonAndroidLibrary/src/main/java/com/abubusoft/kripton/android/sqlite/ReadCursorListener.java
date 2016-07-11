/**
 * 
 */
package com.abubusoft.kripton.android.sqlite;

import android.database.Cursor;

/**
 * <p>Listener for query with raw result.</p>
 * 
 * @author xcesco
 *
 *
 * @since 17/mag/2016
 */
public interface ReadCursorListener {

	/**
	 * This event is fired for each row of resultset.
	 * 
	 * @param cursor
	 * 		cursor from database
	 */
	void onRead(Cursor cursor);
}
