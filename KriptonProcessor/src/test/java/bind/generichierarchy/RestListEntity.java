package bind.generichierarchy;

import java.util.List;

public abstract class RestListEntity<E extends UIDObject> extends RestResponse {

	private static final long serialVersionUID = -7911782943679996559L;
	
	protected List<E> list;

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	

}
