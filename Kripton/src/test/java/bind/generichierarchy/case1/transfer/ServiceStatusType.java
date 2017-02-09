package bind.generichierarchy.case1.transfer;

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
