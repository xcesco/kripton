package org.abubu.kripton.test;
/**
 * 
 */


import java.io.Serializable;

import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;

/**
 * @author Cesco
 *
 */
@BindRoot
public class ChatMessageResponse implements Serializable {

	private static final long serialVersionUID = 3647567554654362016L;
	
	@BindElement
	protected ChatMessageType type;
	
	@BindElement
	protected ChatMessageResponseStatusType status=ChatMessageResponseStatusType.OK;

	@BindElement
	protected String serverUID;
	
	@BindElement
	protected String clientUID;

	@BindElement
	protected long receivedTime;

	@BindElement
	protected String data;

	public String getClientUID() {
		return clientUID;
	}
	
	public String getData() {
		return data;
	}

	public long getReceivedTime() {
		return receivedTime;
	}
	
	public String getServerUID() {
		return serverUID;
	}

	public ChatMessageResponseStatusType getStatus() {
		return status;
	}

	public ChatMessageType getType() {
		return type;
	}

	public void setClientUID(String clientUID) {
		this.clientUID = clientUID;
	}
	
	public void setData(String data) {
		this.data = data;
	}

	public void setReceivedTime(long receivedTime) {
		this.receivedTime = receivedTime;
	}

	public void setServerUID(String serverUID) {
		this.serverUID = serverUID;
	}

	public void setStatus(ChatMessageResponseStatusType status) {
		this.status = status;
	}

	public void setType(ChatMessageType type) {
		this.type = type;
	}
}
