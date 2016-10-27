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
import com.abubusoft.kripton.processor.kripton58.TestKripton58Array;
import com.abubusoft.kripton.processor.kripton58.TestKripton58List;
import com.abubusoft.kripton.processor.kripton60.TestKripton60;
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
			TestKripton58Array.class,TestKripton58List.class,
			TestKripton60.class,
		TestFirstAid.class })
//@formatter:on
public class BindDataSourceProcessorSuiteTest {

}
