package bind.generichierarchy.case1.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class ChannelUser extends UIDObject {

	private static final long serialVersionUID = 8393678197733870803L;

	protected boolean administrator;
	
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
}
