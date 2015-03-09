/**
 * 
 */
package issue.kripton_4;

import com.abubusoft.kripton.annotation.BindAttribute;
import com.abubusoft.kripton.annotation.BindRoot;

@BindRoot
public class Bean2 {

	
	public final String fieldFinal="12";
	
	@BindAttribute
	public static String fieldStatic="111111";
	

}
