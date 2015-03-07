/**
 * 
 */
package issue.kripton_4;

import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;

@BindRoot
public class Bean1 {

	
	public final String fieldFinal="12";
	
	@BindElement
	public static String fieldStatic="111111";
	

}
