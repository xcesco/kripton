/**
 * 
 */
package kripton_14;


import com.abubusoft.kripton.annotation.BindAllFields;
import com.abubusoft.kripton.annotation.BindType;



/**
 * @author xcesco
 *
 */
@BindType
@BindAllFields
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
