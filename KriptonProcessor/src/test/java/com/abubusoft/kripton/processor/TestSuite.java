package com.abubusoft.kripton.processor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.abubusoft.kripton.processor.test01.TestDatabase01;
import com.abubusoft.kripton.processor.test02.TestDao01;
import com.abubusoft.kripton.processor.test03.TestBean01;
import com.abubusoft.kripton.processor.test03.TestBean02;
import com.abubusoft.kripton.processor.test03.TestBean03;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestDatabase01.class, TestDao01.class, TestBean01.class, TestBean02.class, TestBean03.class, com.abubusoft.kripton.processor.test04primary_key.TestBean01.class })
public class TestSuite {

}
