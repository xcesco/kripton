package com.abubusoft.kripton.samples.sqlite.sample02sql;

import android.support.test.runner.AndroidJUnit4;

import com.abubusoft.kripton.samples.sqlite.BaseTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Constraints:
 * <ul>
 *     <li>every table must have a primary key</li>
 *     <li>every dao must extract or insert/update only its associated model object</li>
 * </ul>
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SampleTest extends BaseTest {
    @Test
    public void test() throws Exception {
        try (BindSampleDataSource dataSource=BindSampleDataSource.open()) {
            PersonDaoImpl personDao = dataSource.getPersonDao();

            // delete all
            personDao.deleteAll();

            Person person=new Person();
            person.name="Tony";
            person.surname="Manero";
            personDao.insert(person);

            Person person1=personDao.selectById(person.id);
            assertEquals(person.id, person1.id);

            // select all
            personDao.selectAll();

        }

    }
}
