package kripton08;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

@BindType
public class Bean2 extends Bean0 {

	@Bind
	@BindXml(value=XmlType.VALUE)
	String bla="Bean0";
	

}
