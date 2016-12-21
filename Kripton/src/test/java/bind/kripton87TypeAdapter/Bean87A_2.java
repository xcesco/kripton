package bind.kripton87TypeAdapter;

import java.net.URL;

import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Bean87A_2 {

	@BindXml(xmlType = XmlType.ATTRIBUTE)
	@BindAdapter(adapter = UrlByteArrayTypeAdapter.class, dataType = byte[].class)
	public URL attributeURL;

	@BindXml(xmlType = XmlType.TAG)
	@BindAdapter(adapter = UrlByteArrayTypeAdapter.class, dataType = byte[].class)
	public URL elementURL;

	@BindXml(xmlType = XmlType.VALUE)
	@BindAdapter(adapter = UrlByteArrayTypeAdapter.class, dataType = byte[].class)
	public URL dataURL;
}