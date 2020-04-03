package bind.feature.git43;

import java.util.List;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.annotation.BindXmlElement;

@BindType
public class ContainerBean {

	@BindXml(elements = { @BindXmlElement(type = Bean01.class), @BindXmlElement(type = Bean02.class) })
	public List<Bean01> elements;
}
