package com.abubusoft.kripton.processor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.abubusoft.kripton.processor.test01.TestDatabase01;
import com.abubusoft.kripton.processor.test02.TestDao01;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestDatabase01.class, TestDao01.class })
public class TestSuite {

}
