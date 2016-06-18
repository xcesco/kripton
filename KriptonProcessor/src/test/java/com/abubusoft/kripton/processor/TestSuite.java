package com.abubusoft.kripton.processor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.abubusoft.kripton.processor.kripton33.TestKripton33;
import com.abubusoft.kripton.processor.test01.TestDatabase01;
import com.abubusoft.kripton.processor.test02.TestDao01;
import com.abubusoft.kripton.processor.test03.Test03;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestDatabase01.class, TestDao01.class, Test03.class, com.abubusoft.kripton.processor.kripton38.TestKripton38.class,TestKripton33.class })
public class TestSuite {

}
