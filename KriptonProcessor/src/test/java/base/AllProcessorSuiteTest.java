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
package base;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import bind.kripton70.TestKripton70;
import bind.kripton71.TestKripton71;
import bind.kripton72.TestKripton72;
import bind.kripton73.TestKripton73;
import bind.kripton74Map.TestKripton74;
import bind.kripton75.TestKripton75;
import bind.kripton76.TestKripton76Attribute;
import bind.kripton76.TestKripton76Value;
import bind.kripton78.TestKripton78;
import example01.SQLiteProcessorTest;
import kripton42faster.TestKripton42;
import shared.kripton45.TestKripton45;
import shared.kripton46.TestKripton46;
import shared.kripton47.TestKripton47;
import sqlite.kripton33.TestKripton33;
import sqlite.kripton38.TestKripton38;
import sqlite.kripton40.TestKripton40;
import sqlite.kripton41.TestKripton41;
import sqlite.kripton48.TestKripton48;
import sqlite.kripton49.TestKripton49;
import sqlite.kripton50.TestKripton50;
import sqlite.kripton56.TestKripton56;
import sqlite.kripton58.TestKripton58Array;
import sqlite.kripton58.TestKripton58List;
import sqlite.kripton60.TestKripton60;
import sqlite.kripton62.TestKripton62;
import sqlite.kripton63.TestKripton63;
import sqlite.kripton64.TestKripton64;
import test01.TestDatabase01;
import test02.TestDao01;
import test03.Test03;
import test05firt_aid.TestFirstAid;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ TestDatabase01.class,
			SQLiteProcessorTest.class,
			TestDao01.class, 
			Test03.class, 
			TestKripton33.class, TestKripton38.class, TestKripton40.class, TestKripton41.class, 
			TestKripton42.class, TestKripton45.class, TestKripton46.class, TestKripton47.class,
			TestKripton48.class, TestKripton49.class, TestKripton50.class, TestKripton56.class,
			TestKripton58Array.class,TestKripton58List.class,
			TestKripton60.class,
			TestKripton62.class,
			TestKripton63.class,
			TestKripton64.class,
			TestKripton70.class,
			TestKripton71.class,
			TestKripton72.class,
			TestKripton73.class,
			TestKripton74.class,
			TestKripton75.class,
			TestKripton76Value.class,
			TestKripton76Attribute.class,
			TestKripton78.class,
		TestFirstAid.class })
//@formatter:on
public class AllProcessorSuiteTest {

}
