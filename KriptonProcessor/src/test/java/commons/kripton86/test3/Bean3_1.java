package commons.kripton86.test3;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean3_1 {

	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Long[] valueLongArray;
}
