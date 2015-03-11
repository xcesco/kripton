package issue.kripton_12;

import java.util.List;

import com.abubusoft.kripton.annotation.BindAttribute;
import com.abubusoft.kripton.annotation.BindElement;

public class Bean0<T, E> {

	@BindElement
	protected T genericElement;
	
	@BindAttribute
	protected E genericAttribute;
	
	@BindElement(elementName="item")
	protected List<E> genericListAttribute;
}
