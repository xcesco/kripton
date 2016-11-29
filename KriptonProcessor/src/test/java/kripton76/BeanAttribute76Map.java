package kripton76;

import java.util.Map;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType(value="root", allFields=true)
public class BeanAttribute76Map {
	
	@BindXml(xmlType=XmlType.VALUE)
	public Map<String, Byte> valueStringByteMap;
	
}

