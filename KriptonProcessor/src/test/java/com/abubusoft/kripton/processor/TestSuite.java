package com.abubusoft.kripton.processor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.abubusoft.kripton.processor.kripton33.TestKripton33;
import com.abubusoft.kripton.processor.kripton38.TestKripton38;
import com.abubusoft.kripton.processor.kripton40.TestKripton40;
import com.abubusoft.kripton.processor.kripton41.TestKripton41;
import com.abubusoft.kripton.processor.test01.TestDatabase01;
import com.abubusoft.kripton.processor.test02.TestDao01;
import com.abubusoft.kripton.processor.test03.Test03;
import com.abubusoft.kripton.processor.test05firt_aid.TestFirstAid;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestDatabase01.class, TestDao01.class, Test03.class, TestKripton33.class, TestKripton38.class, TestKripton40.class, TestKripton41.class, TestFirstAid.class })
public class TestSuite {

}
