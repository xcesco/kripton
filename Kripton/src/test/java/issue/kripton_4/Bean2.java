/**
 * 
 */
package issue.kripton_4;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.annotation.XmlType;

@BindType
public class Bean2 {

	
	public final String fieldFinal="12";
	
	@BindXml(value=XmlType.ATTRIBUTE)
	public static String fieldStatic="111111";
	

}
