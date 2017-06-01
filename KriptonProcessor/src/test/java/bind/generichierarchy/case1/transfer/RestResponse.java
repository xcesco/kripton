/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
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
