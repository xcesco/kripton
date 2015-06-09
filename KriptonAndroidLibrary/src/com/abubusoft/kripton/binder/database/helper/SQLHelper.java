/**
 * 
 */
package com.abubusoft.kripton.binder.database.helper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.abubusoft.kripton.binder.database.DatabaseColumn;
import com.abubusoft.kripton.binder.database.Filter;
import com.abubusoft.kripton.binder.transform.Transform;

/**
 * 
 * Utility class for parse SQL string.
 * 
 * @author xcesco
 *
 */
public abstract class SQLHelper {
	
	static final Pattern patternFieldName = Pattern.compile("([\\w\\.]+)");
	
	static final Pattern patternPlaceHolder = Pattern.compile("\\#\\s*\\{\\s*([\\w\\.]*)\\s*\\}");
	
	/**
	 * Get input string, replace field name with relative column name.
	 * 
	 * @param input
	 *            sql with field names
	 * @param field2ColumnMap
	 * @return sql with column names
	 */
	public static String createSql(String input, LinkedHashMap<String, DatabaseColumn> field2ColumnMap) {
		Matcher matcher = patternFieldName.matcher(input);
		StringBuffer sb = new StringBuffer();

		if (matcher.find()) {
			int previousIndex = 0;
			int currentIndex;
			DatabaseColumn column;
			String temp;

			do {
				currentIndex = matcher.start();
				sb.append(input.substring(previousIndex, currentIndex));

				temp = matcher.group(1);
				column = field2ColumnMap.get(temp);

				if (column != null) {
					sb.append(column.name);
				} else {
					sb.append(matcher.group(1));
				}

				previousIndex = matcher.end();
			} while (matcher.find());

			if (previousIndex <= input.length() - 1) {
				sb.append(input.substring(previousIndex));
			}

		} else {
			sb.append(input);
		}

		return sb.toString();
	}
	
	/**
	 * In sgl replace the placeholders with ? and retrieve field name
	 * 
	 * @param input
	 * @return
	 */
	public static Filter createFilterAndFieldNames(String input) {
		Filter result = new Filter();
		ArrayList<String> paramsArray = new ArrayList<String>();

		if (input == null || "".equals(input.trim())) {
			result.fieldNames = new String[0];
			result.sql = "";

			return result;
		}

		Matcher matcher = patternPlaceHolder.matcher(input);
		StringBuffer sb = new StringBuffer();

		if (matcher.find()) {
			int previousIndex = 0;
			int currentIndex;

			do {
				currentIndex = matcher.start();
				sb.append(input.substring(previousIndex, currentIndex));
				sb.append("?");
				paramsArray.add(matcher.group(1));

				previousIndex = matcher.end();
			} while (matcher.find());

			if (previousIndex <= input.length() - 1) {
				sb.append(input.substring(previousIndex));
			}

		} else {
			sb.append(input);
		}

		result.fieldNames = new String[paramsArray.size()];
		result.field = new Field[paramsArray.size()];
		result.fieldTransform = new Transform[paramsArray.size()];

		if (paramsArray.size() > 0) {
			paramsArray.toArray(result.fieldNames);
		}
		result.sql = sb.toString();
		return result;

	}
}
