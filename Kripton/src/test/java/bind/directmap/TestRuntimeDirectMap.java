package bind.directmap;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.junit.Test;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;

import bind.AbstractBaseTest;

public class TestRuntimeDirectMap extends AbstractBaseTest {

	@Test
	public void testRun() throws Exception {
		KriptonJsonContext context = KriptonBinder.jsonBind();
		
		Person bean=new Person();
		bean.name="ma";
		bean.surname="test";
		bean.birthday=new Date();
		bean.tags=new ArrayList<>();
		bean.tags.add("hello");
		bean.tags.add("hello2");
		
		String buffer=context.serialize(bean);
		System.out.println(buffer);
		
		Map<String, String> map = context.parseMap(buffer);
		
		System.out.println(map.get("name"));
		System.out.println(map.get("tags"));
		System.out.println(map);
	}

}
