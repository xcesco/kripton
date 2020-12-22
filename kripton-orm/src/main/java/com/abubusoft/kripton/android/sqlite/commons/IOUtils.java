/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
package com.abubusoft.kripton.android.sqlite.commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.abubusoft.kripton.android.Logger;

import android.content.Context;


/**
 * The Class IOUtils.
 */
public class IOUtils {

	/**
	 * The listener interface for receiving onReadLine events.
	 * The class that is interested in processing a onReadLine
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addOnReadLineListener</code> method. When
	 * the onReadLine event occurs, that object's appropriate
	 * method is invoked.
	 *
	 */
	public interface OnReadLineListener {

		/**
		 * On text line.
		 *
		 * @param line the line
		 */
		void onTextLine(String line);

	}

	/**
	 * Read text file.
	 *
	 * @param context the context
	 * @param resId the res id
	 * @return the string
	 */
	public static String readTextFile(Context context, int resId) {
		InputStream inputStream = context.getResources().openRawResource(resId);
		
		return readText(inputStream);
	}

	/**
	 * Read text file.
	 *
	 * @param fileName the file name
	 * @return the string
	 */
	public static String readTextFile(String fileName) {		
		try {
			return readText(new FileInputStream(new File(fileName)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * Read text.
	 *
	 * @param inputStream the input stream
	 * @return the string
	 */
	public static String readText(InputStream inputStream) {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append('\n');
			}
		} catch (IOException e) {
			Logger.error(e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					Logger.error(e.getMessage());
					e.printStackTrace();
				}
			}
			
		}
		return stringBuilder.toString();
	}

	/**
	 * Read text lines.
	 *
	 * @param openRawResource the open raw resource
	 * @return the list
	 */
	public static List<String> readTextLines(InputStream openRawResource) {
		final List<String> result=new LinkedList<>();
		
		readTextLines(openRawResource, new OnReadLineListener() {
			
			@Override
			public void onTextLine(String line) {
				result.add(line);
			}
		});
		
		return result;		
	}
	
	/**
	 * Read text lines.
	 *
	 * @param openRawResource the open raw resource
	 * @param listener the listener
	 */
	public static void readTextLines(InputStream openRawResource, OnReadLineListener listener) {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openRawResource));
		String line;		
		try {
			while ((line = bufferedReader.readLine()) != null) {
				listener.onTextLine(line);				
			}
		} catch (IOException e) {
			Logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					Logger.error(e.getMessage());
					e.printStackTrace();
				}
			}
			
		}
	}
}
