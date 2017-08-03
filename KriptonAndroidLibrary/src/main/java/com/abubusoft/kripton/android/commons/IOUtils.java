package com.abubusoft.kripton.android.commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.abubusoft.kripton.android.Logger;

import android.content.Context;

public class IOUtils {

	public static String readTextFile(Context context, int resId) {
		InputStream inputStream = context.getResources().openRawResource(resId);
		
		return readText(inputStream);
	}

	public static String readTextFile(String fileName) {		
		try {
			return readText(new FileInputStream(new File(fileName)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
			return null;
		}
	}
	
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
}
