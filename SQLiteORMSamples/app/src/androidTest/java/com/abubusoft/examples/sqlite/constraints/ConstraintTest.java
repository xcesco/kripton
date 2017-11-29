package com.abubusoft.examples.sqlite.constraints;

import android.support.test.runner.AndroidJUnit4;

import com.abubusoft.examples.sqlite.BaseTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

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
public class ConstraintTest extends BaseTest {
    @Test
    public void test() throws Exception {
        try (BindFeature01DataSource dataSource=BindFeature01DataSource.open()) {
            CityDaoImpl cityDao = dataSource.getCityDao();
            PersonDaoImpl personDao = dataSource.getPersonDao();

            City city=new City();
            city.name="Venezia";
            cityDao.insert(city);

            Person person=new Person();
            person.name="Tony";
            person.surname="Manero";
            person.cityId=city.id;
            personDao.insert(person);

            List<Person> result=personDao.listForCity(city.id);
            assertEquals(person.id, result.get(0).id);
        }

        Thread.sleep(10000);
    }
}
