package bind.feature.namespace;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import bind.AbstractBaseTest;
import bind.feature.namespace.case1.Person;

public class TestRuntimeNamespace1 extends AbstractBaseTest {

	/**
	 * Test run.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRun() throws Exception {
		Person person=new Person();
		
		person.birthday=new Date();
		person.name="toni";
		person.surname="manero";
		person.tags=new ArrayList<String>();
		person.tags.add("test1");
		
		Person person1=new Person();
		person1.birthday=new Date();
		person1.name="toni";
		person1.surname="manero";
		person1.tags=new ArrayList<String>();
		person1.tags.add("test1");
		
		person.parent=person1;
		
		
		check(person);
	}

}