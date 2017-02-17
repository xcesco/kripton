package bind.kripton76Errors;

import java.util.Set;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType(value="root", allFields=true)
public class BeanAttribute76Set {
	
	@BindXml(xmlType=XmlType.VALUE)
	public Set<Byte> valueByteSet;
	
}

