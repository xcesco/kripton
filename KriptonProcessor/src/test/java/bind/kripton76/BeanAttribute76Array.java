package bind.kripton76;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder2.xml.XmlType;

@BindType(value="root", allFields=true)
public class BeanAttribute76Array {
	
	@Bind("temp")
	@BindXml(xmlType=XmlType.VALUE)
	public Byte[] valueByteArray;
	
}
