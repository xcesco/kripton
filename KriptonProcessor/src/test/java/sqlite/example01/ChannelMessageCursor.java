/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.example01;

import java.util.List;

import android.database.Cursor;

public class ChannelMessageCursor {
	
	public interface OnChannelMessageListener
	{
		void onRow(int rowCount, ChannelMessageCursor bean);
	}
	
	public static ChannelMessageCursor create(Cursor cursor)
	{
		return new ChannelMessageCursor(cursor);
	}

	protected Cursor cursor;
	
	public ChannelMessageCursor(Cursor value) {
		cursor=value;
				
	}

	public List<ChannelMessageCursor> execute()
	{
		return null;
	}
	
	public void execute(OnChannelMessageListener listener)
	{
		
	}
	
	public void wrap(Cursor cursor)
	{
		this.cursor=cursor;
		
		int index0=cursor.getColumnIndex("a");
		
		if (index0>-1 && cursor.isNull(index0)) cursor.getDouble(index0);
		
		
	}
	
	
	
}
