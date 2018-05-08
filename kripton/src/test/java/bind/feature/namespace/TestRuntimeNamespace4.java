package bind.feature.namespace;

import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

import bind.AbstractBaseTest;
import bind.feature.namespace.case4.Person;

public class TestRuntimeNamespace4 extends AbstractBaseTest {

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
		person.tags=new HashMap<String, String>();
		person.tags.put("test1","test2");
		
		
		check(person);
	}

}