package com.abubusoft.kripton.processor.core.reflect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.abubusoft.kripton.common.Pair;

public abstract class MethodAnalyzer {
	
	private static final Pattern pattern = Pattern.compile("\\((.*)\\)(.*)");

    public static Pair<String, String> extractResultAndArguments(String value) {
        Matcher matcher = pattern.matcher(value);
        
        Pair<String, String> result=new Pair<String, String>();
        if (matcher.matches())
        {
        	result.value0=matcher.group(1);
        	result.value1=matcher.group(2);
        }
        return result;
    }

	
}
