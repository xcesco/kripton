package android.kripton_22;

public enum ChatMessageType {
	LOGIN(true),
	SAY(true),
	LIST(false),
	LOGOUT(true);
	
	private ChatMessageType(boolean value)
	{
		persistent=value;
	}
	
	public boolean persistent;
	
}
