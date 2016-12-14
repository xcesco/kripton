package commons.kripton86.test3;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean3_2 {

	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public List<Long> valueLongList;
}
