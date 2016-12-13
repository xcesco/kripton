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
package sqlite.kripton51;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.kripton51.entities.MessageEntity;
import sqlite.kripton51.entities.OwnerType;
import sqlite.kripton51.internal.MessageType;
import sqlite.kripton51.persistence.DaoMessage;
import sqlite.kripton51.persistence.WhisperDataSource;

@RunWith(JUnit4.class)
public class TestKripton51 extends AbstractBindSQLiteProcessorTest {

	@Test
	public void testDatabase() throws IOException, InstantiationException, IllegalAccessException {
		buildDataSourceProcessorTest(WhisperDataSource.class, MessageEntity.class, OwnerType.class, DaoMessage.class, MessageType.class);
	}

}
