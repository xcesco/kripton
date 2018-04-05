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
package bind.feature.generichierarchy.case1.transfer;

// TODO: Auto-generated Javadoc
/**
 * The Enum ServiceStatusType.
 */
public enum ServiceStatusType {
	
	/** The ok. */
	OK(200,"OK", true),
	
	/** The error. */
	ERROR(400,"ERROR"),
	
	/** The error invalid login. */
	ERROR_INVALID_LOGIN(401), 
	
	/** The error no device type. */
	ERROR_NO_DEVICE_TYPE(402), 
	
	/** The error no device fcm id. */
	ERROR_NO_DEVICE_FCM_ID(403), 
	
	/** The error no user. */
	ERROR_NO_USER(404), 
	
	/** The error no device. */
	ERROR_NO_DEVICE(405), 
	
	/** The error invalid auth token. */
	ERROR_INVALID_AUTH_TOKEN(406), 
	
	/** The error no auth token. */
	ERROR_NO_AUTH_TOKEN(407),
	
	/** The error no upload. */
	ERROR_NO_UPLOAD(408),
	
	/** The error invalid message. */
	ERROR_INVALID_MESSAGE(409);

	/**
	 * Instantiates a new service status type.
	 *
	 * @param code the code
	 */
	private ServiceStatusType(int code)
	{
		this(code, null);
	}

	
	/**
	 * Instantiates a new service status type.
	 *
	 * @param code the code
	 * @param status the status
	 * @param value the value
	 */
	private ServiceStatusType(int code, String status, boolean value)
	{
		this.successfull=value;
		this.code=code;
		this.status=status;
	}
	
	/**
	 * Instantiates a new service status type.
	 *
	 * @param code the code
	 * @param status the status
	 */
	private ServiceStatusType(int code, String status)
	{
		this.successfull=false;
		this.code=code;
		this.status=status;
	}

	
	/** The successfull. */
	private boolean successfull;
	
	/**
	 * Checks if is successfull.
	 *
	 * @return true, if is successfull
	 */
	public boolean isSuccessfull() {
		return successfull;
	}

	/** The code. */
	private int code;
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/** The status. */
	private String status;
}
