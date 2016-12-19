package bind.kripton76Errors;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType(value="root", allFields=true)
public class BeanAttribute76List {
	
	@BindXml(xmlType=XmlType.VALUE_CDATA)
	public List<Byte> valueByteList;
	
}
