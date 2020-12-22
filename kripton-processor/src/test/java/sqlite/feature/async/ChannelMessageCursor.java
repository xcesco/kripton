/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.feature.async;

import java.util.List;

import android.database.Cursor;


/**
 * The Class ChannelMessageCursor.
 */
public class ChannelMessageCursor {
	
	/**
	 * The listener interface for receiving onChannelMessage events.
	 * The class that is interested in processing a onChannelMessage
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addOnChannelMessageListener<code> method. When
	 * the onChannelMessage event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see OnChannelMessageEvent
	 */
	public interface OnChannelMessageListener
	{
		
		/**
		 * On row.
		 *
		 * @param rowCount the row count
		 * @param bean the bean
		 */
		void onRow(int rowCount, ChannelMessageCursor bean);
	}
	
	/**
	 * Creates the.
	 *
	 * @param cursor the cursor
	 * @return the channel message cursor
	 */
	public static ChannelMessageCursor create(Cursor cursor)
	{
		return new ChannelMessageCursor(cursor);
	}

	/** The cursor. */
	protected Cursor cursor;
	
	/**
	 * Instantiates a new channel message cursor.
	 *
	 * @param value the value
	 */
	public ChannelMessageCursor(Cursor value) {
		cursor=value;
				
	}

	/**
	 * Execute.
	 *
	 * @return the list
	 */
	public List<ChannelMessageCursor> execute()
	{
		return null;
	}
	
	/**
	 * Execute.
	 *
	 * @param listener the listener
	 */
	public void execute(OnChannelMessageListener listener)
	{
		
	}
	
	/**
	 * Wrap.
	 *
	 * @param cursor the cursor
	 */
	public void wrap(Cursor cursor)
	{
		this.cursor=cursor;
		
		int index0=cursor.getColumnIndex("a");
		
		if (index0>-1 && cursor.isNull(index0)) cursor.getDouble(index0);
		
		
	}
	
	
	
}
