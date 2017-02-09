package bind.generichierarchy.case1.transfer;

import java.io.Serializable;

public abstract class RestResponse  implements Serializable {

	private static final long serialVersionUID = -1707936337366965471L;

	protected ServiceStatusType status;
	
	protected String detailMessage;

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}


	public void setStatus(ServiceStatusType status) {
		this.status = status;
	}
	

	public ServiceStatusType getStatus() {
		return status;
	}
	
	public boolean isSuccessfull()
	{
		return status.isSuccessfull();
	}

	@Override
	public String toString() {
		return "RestResponse [status=" + status + "]";
	}

}
