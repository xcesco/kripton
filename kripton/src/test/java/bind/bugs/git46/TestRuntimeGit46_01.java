package bind.bugs.git46;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.abubusoft.kripton.BinderContext;
import com.abubusoft.kripton.BinderType;
import com.abubusoft.kripton.KriptonBinder;

import bind.AbstractBaseTest;

public class TestRuntimeGit46_01 extends AbstractBaseTest {

	@Test
	public void testRun() throws Exception {
		String input = "<jasperReport "
				+ "			  name=\"ricetta\" pageWidth=\"595\" pageHeight=\"421\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\""
				+ "			  topMargin=\"20\" bottomMargin=\"20\" uuid=\"dfcc67f8-27a8-4010-90a8-70d1d9f2f28d\">"
				+ "	<property name=\"com.jaspersoft.studio.data.defaultdataadapter\" value=\"Sanita Ricetta\"/>"
				+ "	<property name=\"com.jaspersoft.studio.unit.\" value=\"pixel\"/>"
				+ "	<property name=\"com.jaspersoft.studio.unit.pageHeight\" value=\"pixel\"/>"
				+ "	<property name=\"com.jaspersoft.studio.unit.pageWidth\" value=\"pixel\"/>"
				+ "	<property name=\"com.jaspersoft.studio.unit.topMargin\" value=\"pixel\"/>"
				+ "	<property name=\"com.jaspersoft.studio.unit.bottomMargin\" value=\"pixel\"/>"
				+ "	<property name=\"com.jaspersoft.studio.unit.leftMargin\" value=\"pixel\"/>"
				+ "	<property name=\"com.jaspersoft.studio.unit.rightMargin\" value=\"pixel\"/>"
				+ "	<property name=\"com.jaspersoft.studio.unit.columnWidth\" value=\"pixel\"/>"
				+ "	<property name=\"com.jaspersoft.studio.unit.columnSpacing\" value=\"pixel\"/>" + "</jasperReport>";

		BinderContext binder = KriptonBinder.bind(BinderType.XML);
		JasperReport result = binder.parse(input, JasperReport.class);
		String output1 = KriptonBinder.bind(BinderType.JSON).serialize(result);
		System.out.println(output1);
		assertTrue(result.property.size() == 10);
		assertTrue(result.property.get(0).name.equals("com.jaspersoft.studio.data.defaultdataadapter"));
		assertTrue(result.property.get(9).name.equals("com.jaspersoft.studio.unit.columnSpacing"));
	}

}