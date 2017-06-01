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
package bind.generichierarchy;

public enum ServiceStatusType {
	
	OK(200,"OK", true),
	ERROR(400,"ERROR"),
	ERROR_INVALID_LOGIN(401), 
	ERROR_NO_DEVICE_TYPE(402), 
	ERROR_NO_DEVICE_FCM_ID(403), 
	ERROR_NO_USER(404), 
	ERROR_NO_DEVICE(405), 
	ERROR_INVALID_AUTH_TOKEN(406), 
	ERROR_NO_AUTH_TOKEN(407),
	ERROR_NO_UPLOAD(408),
	ERROR_INVALID_MESSAGE(409);

	private ServiceStatusType(int code)
	{
		this(code, null);
	}

	
	private ServiceStatusType(int code, String status, boolean value)
	{
		this.successfull=value;
		this.code=code;
		this.status=status;
	}
	
	private ServiceStatusType(int code, String status)
	{
		this.successfull=false;
		this.code=code;
		this.status=status;
	}

	
	private boolean successfull;
	
	public boolean isSuccessfull() {
		return successfull;
	}

	private int code;
	
	public int getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	private String status;
}
