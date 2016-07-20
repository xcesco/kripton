package kripton15;

import java.io.Serializable;
import java.util.Currency;
import java.util.Locale;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Level2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2890454196477386027L;

	public Locale local;
	
	public Currency currency;
	
}
