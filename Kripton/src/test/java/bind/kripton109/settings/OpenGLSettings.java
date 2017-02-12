package bind.kripton109.settings;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class OpenGLSettings {

	@Bind("openGLVersion")
	public int version = 2;

	@Bind("openGLDebug")
	public boolean debug = false;

	@Bind("openGLMaxFPS")
	public int maxFPS = 0;

	@Bind("openGLSafeMode")
	public boolean safeMode = true;
	
	@Bind("openGLAsyncMode")
	public boolean asyncMode = true;
	
}
