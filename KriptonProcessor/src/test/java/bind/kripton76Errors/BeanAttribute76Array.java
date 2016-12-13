package bind.kripton76Errors;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType(value="root", allFields=true)
public class BeanAttribute76Array {
	
	@Bind("temp")
	@BindXml(xmlType=XmlType.VALUE)
	public Byte[] valueByteArray;
	
}
