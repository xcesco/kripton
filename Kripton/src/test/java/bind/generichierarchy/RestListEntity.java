package bind.generichierarchy;

import java.util.List;
import java.util.Map;

import com.abubusoft.kripton.annotation.BindXml;

public abstract class RestListEntity<E extends UIDObject> extends RestResponse {

	private static final long serialVersionUID = -7911782943679996559L;
	
	public E bean;
	
	@BindXml(elementTag="item")
	protected List<E> list;
	
	public Map<String, E> map;

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	

}
