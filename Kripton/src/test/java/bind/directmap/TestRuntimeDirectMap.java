package bind.directmap;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.KriptonJsonContext;
import com.abubusoft.kripton.map.BindMapListener;
import com.abubusoft.kripton.map.BindMapVisitor;
import com.abubusoft.kripton.map.BindMapVisitor.VisitorStatusType;

import bind.AbstractBaseTest;

public class TestRuntimeDirectMap extends AbstractBaseTest {

	@Test
	public void testRun() throws Exception {
		KriptonJsonContext context = KriptonBinder.jsonBind();
		
		Person bean=new Person();
		bean.name="name";
		bean.surname="sunrame";
		bean.birthday=new Date();
		bean.tags=new ArrayList<>();
		bean.tags.add("hello");
		bean.tags.add(null);
		bean.tags.add("hello2");
		
		String buffer=context.serialize(bean);
		System.out.println(buffer);
		
		Map<String, Object> map = context.parseMap(buffer);
		
		Assert.assertTrue("name",bean.name.equals(map.get("name")));
		Assert.assertTrue("surname",bean.surname.equals(map.get("surname")));
		
		BindMapVisitor.execute(map, new BindMapListener() {
			
			@Override
			public void onField(String name, String value, VisitorStatusType status) {
				System.out.println(String.format("%s = %s", name, value));
				
			}
		});		
	}

}
