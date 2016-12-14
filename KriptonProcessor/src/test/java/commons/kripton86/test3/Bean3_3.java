package commons.kripton86.test3;

import java.util.Set;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean3_3 {

	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public Set<Long> valueLongSet;
}
