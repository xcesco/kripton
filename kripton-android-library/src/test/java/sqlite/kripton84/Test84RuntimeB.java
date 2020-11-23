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
package sqlite.kripton84;

import base.BaseAndroidTest;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// TODO: Auto-generated Javadoc

/**
 * The Class Test84RuntimeB.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class Test84RuntimeB extends BaseAndroidTest {

  /**
   * Test run.
   */
  @Test
  public void testRun() {
    BindBean84BDataSource dataSource = BindBean84BDataSource.getInstance();

    dataSource.execute(daoFactory -> {
      Bean84BDaoImpl dao = daoFactory.getBean84BDao();

      Bean84B2 innerBean = new Bean84B2();
      innerBean.columnString = "test01";

      Bean84B bean = new Bean84B();
      bean.columnBean = innerBean;
      dao.insert(bean);

      Bean84B bean2 = dao.selectById(bean.id);
      assertEquals(bean, bean2);

      Bean84B bean3 = dao.selectByBean(innerBean);
      assertEquals(bean, bean3);

      return TransactionResult.COMMIT;
    });

  }

}
