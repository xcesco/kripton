package issue.kripton_8;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.annotation.XmlType;

@BindType
public class Bean2 extends Bean0 {

	@Bind
	@BindXml(type=XmlType.VALUE)
	String bla="Bean0";
	

}
