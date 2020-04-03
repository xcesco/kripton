package bind.feature.git45;

import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;


@BindType
public class ContainerBean {

	@Bind("item1")
	public List<Bean01> elements;
	
	@Bind("list2")
	@BindXml(elementTag="item2")
	private Set<Bean02> elements2;
	
	public Set<Bean02> getElements2() {
		return elements2;
	}

	public void setElements2(Set<Bean02> elements2) {
		this.elements2 = elements2;
	}

	public Bean03[] getElements3() {
		return elements3;
	}

	public void setElements3(Bean03[] elements3) {
		this.elements3 = elements3;
	}

	@Bind("item3")
	private Bean03[] elements3;
}
