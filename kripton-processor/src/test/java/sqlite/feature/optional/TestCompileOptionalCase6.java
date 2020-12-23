/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.feature.optional;

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import org.junit.Test;
import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.optional.case6.AppDataSource;
import sqlite.feature.optional.case6.DaoArticle;
import sqlite.feature.optional.case6.DaoArtist;
import sqlite.feature.optional.model.Article;
import sqlite.feature.optional.model.Artist;
import sqlite.feature.optional.model.Simple;

import java.io.IOException;

public class TestCompileOptionalCase6 extends AbstractBindSQLiteProcessorTest {

  // TODO
  // scalar
  // byte
  // bean other
  // generation D2D
  @Test
  public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
    expectedException(InvalidMethodSignException.class, "InvalidMethodSignException: In class 'DaoArtist', method 'selectTitle2ById' has an invalid signature: 'java.util.Optional<java.util.List<sqlite.feature.optional.model.Simple>>' as return type is not supported");
    buildBindProcessorTest(Article.class,
            Artist.class, Simple.class,
            DaoArticle.class,
            DaoArtist.class,
            AppDataSource.class);
  }

}
