/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.feature.optional;

import base.BaseAndroidTest;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import org.junit.Assert;
import org.junit.Test;
import sqlite.feature.optional.case1.BindAppDataSource;
import sqlite.feature.optional.model.Article;


/**
 * The Class Test209Model1Runtime.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class TestOptionalCase1Runtime extends BaseAndroidTest {

  /**
   * Test run sqlite 1.
   */
  @Test
  public void testRunSqlite1() {
    BindAppDataSource ds = BindAppDataSource.getInstance();

    ds.execute(daoFactory -> {
              daoFactory.getDaoArticle().insert(new Article(9, "test"));
              return TransactionResult.COMMIT;
            }
    );

    Assert.assertEquals(1, ds.executeBatch(daoFactory -> daoFactory.getDaoArticle().selectAll()).size());
    Assert.assertTrue(ds.executeBatch(daoFactory -> daoFactory.getDaoArticle().selectById(1)).isPresent());
    Assert.assertTrue(!ds.executeBatch(daoFactory -> daoFactory.getDaoArticle().selectById(10)).isPresent());
  }

}
