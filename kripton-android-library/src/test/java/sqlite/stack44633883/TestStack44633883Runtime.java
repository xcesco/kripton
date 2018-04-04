/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
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
package sqlite.stack44633883;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import base.BaseAndroidTest;

/**
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class TestStack44633883Runtime extends BaseAndroidTest {

	@Test
	public void test() {
		HashSet<String> fruitSet = new HashSet<>();
        fruitSet.add("Apple");
        fruitSet.add("Orange");

        SchoolLunch schoolLunch = new SchoolLunch();
        schoolLunch.setContainsMeat(false);
        schoolLunch.setFresh(true);
        schoolLunch.setFruits(fruitSet);
        
        BindSchoolLunchDataSource dataSource=BindSchoolLunchDataSource.instance();
        
        dataSource.openWritableDatabase();

        SchoolLunchDAOImpl schoolLunchDAO = dataSource.getSchoolLunchDAO();
        
        schoolLunchDAO.insertAll(schoolLunch);

        List<SchoolLunch> schoolLunches = schoolLunchDAO.getAll();
        assertEquals(schoolLunches.size(), 1);

        SchoolLunch extractedSchoolLunch = schoolLunches.get(0);
        assertEquals(false, extractedSchoolLunch.isContainsMeat());
        assertEquals(true, extractedSchoolLunch.isFresh());
        assertEquals(2, extractedSchoolLunch.getFruits().size());
        
        dataSource.close();
	}

}