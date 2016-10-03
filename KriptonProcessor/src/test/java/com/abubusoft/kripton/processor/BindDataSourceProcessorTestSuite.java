package com.abubusoft.kripton.processor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.abubusoft.kripton.processor.kripton33.TestKripton33;
import com.abubusoft.kripton.processor.kripton38.TestKripton38;
import com.abubusoft.kripton.processor.kripton40.TestKripton40;
import com.abubusoft.kripton.processor.kripton41.TestKripton41;
import com.abubusoft.kripton.processor.kripton42.TestKripton42;
import com.abubusoft.kripton.processor.kripton45.TestKripton45;
import com.abubusoft.kripton.processor.kripton46.TestKripton46;
import com.abubusoft.kripton.processor.kripton47.TestKripton47;
import com.abubusoft.kripton.processor.kripton48.TestKripton48;
import com.abubusoft.kripton.processor.kripton49.TestKripton49;
import com.abubusoft.kripton.processor.kripton50.TestKripton50;
import com.abubusoft.kripton.processor.test01.TestDatabase01;
import com.abubusoft.kripton.processor.test02.TestDao01;
import com.abubusoft.kripton.processor.test03.Test03;
import com.abubusoft.kripton.processor.test05firt_aid.TestFirstAid;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ TestDatabase01.class, 
			TestDao01.class, 
			Test03.class, 
			TestKripton33.class, TestKripton38.class, TestKripton40.class, TestKripton41.class, 
			TestKripton42.class, TestKripton45.class, TestKripton46.class, TestKripton47.class,
			TestKripton48.class, TestKripton49.class, TestKripton50.class, 
		TestFirstAid.class })
//@formatter:on
public class BindDataSourceProcessorTestSuite {

}
