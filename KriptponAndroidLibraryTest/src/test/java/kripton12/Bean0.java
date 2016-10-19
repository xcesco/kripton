package kripton12;

import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;

public class Bean0<T, E> {

	@Bind
	protected T genericElement;
	
	@Bind
	@BindXml(value=XmlType.ATTRIBUTE)
	protected E genericAttribute;
	
	@Bind(elementName="item")
	protected List<E> genericListAttribute;
}
