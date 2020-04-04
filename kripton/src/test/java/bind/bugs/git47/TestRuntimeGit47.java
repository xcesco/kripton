package bind.bugs.git47;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.abubusoft.kripton.BinderContext;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;

import bind.AbstractBaseTest;
import bind.bugs.git46.JasperReport;

public class TestRuntimeGit47 extends AbstractBaseTest {

	@Test
	public void testRun() throws Exception {
		BinderContext binder = KriptonBinder.bind(BinderType.XML);

		File asset=new File("src/test/resources/bind/bugs/git47/ricetta.xml");
		log(asset.getAbsolutePath());

		
		JasperReport result = binder.parse(asset, JasperReport.class);
		String output1 = KriptonBinder.bind(BinderType.JSON).serialize(result);
		System.out.println(output1);
		assertEquals(10, result.property.size());
		assertTrue(result.property.get(0).name.equals("com.jaspersoft.studio.data.defaultdataadapter"));
		assertTrue(result.property.get(9).name.equals("com.jaspersoft.studio.unit.columnSpacing"));
		
		

	}

}