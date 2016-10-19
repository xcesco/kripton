/**
 * 
 */
package kripton04;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean1 {

	
	public final String fieldFinal="12";
	
	@Bind
	public static String fieldStatic="111111";
	

}
