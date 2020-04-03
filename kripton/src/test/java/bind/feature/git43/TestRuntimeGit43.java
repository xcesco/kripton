package bind.feature.git43;

import java.util.ArrayList;

import org.junit.Test;

import com.abubusoft.kripton.BinderContext;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;

import bind.AbstractBaseTest;

public class TestRuntimeGit43 extends AbstractBaseTest {

	
	@Test
	public void testRun() throws Exception {
		BinderContext binder = KriptonBinder.bind(BinderType.XML);
		
		ContainerBean bean=new ContainerBean();
		bean.elements=new ArrayList<>();
		
		Bean01 bean1=new Bean01();
		bean1.setName("ciuaua");
		
		bean.elements.add(bean1);
		bean.elements.add(bean1);
		log(binder.serialize(bean));
		
	}

}