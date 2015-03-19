package issue.kripton_12;

import java.util.List;

import com.abubusoft.kripton.annotation.BindAttribute;
import com.abubusoft.kripton.annotation.Bind;

public class Bean0<T, E> {

	@Bind
	protected T genericElement;
	
	@BindAttribute
	protected E genericAttribute;
	
	@Bind(elementName="item")
	protected List<E> genericListAttribute;
}
