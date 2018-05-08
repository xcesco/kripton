package bind.feature.namespace;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import bind.AbstractBaseTest;
import bind.feature.namespace.case2.Person;

public class TestRuntimeNamespace2 extends AbstractBaseTest {

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
		
		
		check(person);
	}

}