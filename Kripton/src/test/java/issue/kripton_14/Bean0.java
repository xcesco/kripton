/**
 * 
 */
package issue.kripton_14;


import com.abubusoft.kripton.annotation.BindDefault;
import com.abubusoft.kripton.annotation.BindRoot;



/**
 * @author xcesco
 *
 */
@BindRoot
@BindDefault
public class Bean0 {

	public Bean0(String string, int i) {
		this.fieldName=string;
		this.fieldValue=i;
	}
	
	public Bean0() {
		
	}


	String fieldName;
	
	int fieldValue;

}
