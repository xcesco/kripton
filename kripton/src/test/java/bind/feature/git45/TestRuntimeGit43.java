package bind.feature.git45;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.Test;

import com.abubusoft.kripton.BinderContext;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;

import bind.AbstractBaseTest;

public class TestRuntimeGit43 extends AbstractBaseTest {

	@Test
	public void testRun() throws Exception {
		BinderContext binder = KriptonBinder.bind(BinderType.XML);

		ContainerBean bean = new ContainerBean();
		bean.elements = new ArrayList<>();

		Bean01 bean1 = new Bean01();
		bean1.setName("ciuaua");

		Bean02 bean2 = new Bean02();
		bean2.setDate(new Date());

		bean.elements.add(bean1);
		bean.setElements2(new HashSet<>());
		bean.getElements2().add(bean2);
		log(binder.serialize(bean));

		String input = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><containerBean><list2><item2><date>2020-04-03T22:01:26.140Z</date></item2></list2><item1><name>ciuaua1</name></item1><item2><date>2020-04-03T18:55:09.955Z</date></item2><item1><name>ciuaua2</name></item1><list2><item2><date>2020-04-03T22:01:26.140Z</date></item2></list2></containerBean>";

		ContainerBean result=binder.parse(input, ContainerBean.class);
		
		assertTrue(result.elements.size()==2);
		assertTrue(result.elements.get(1).getName().equals("ciuaua2"));
		
		assertTrue(result.getElements2().size()==2);
	}

}