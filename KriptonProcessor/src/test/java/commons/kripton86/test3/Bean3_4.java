package commons.kripton86.test3;

import java.util.Set;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean3_4 {

	@BindXml(xmlType=XmlType.VALUE)
	public Set<Long> valueLongSet1;
	
	@BindXml(xmlType=XmlType.VALUE)
	public Set<Long> valueLongSet;
}
