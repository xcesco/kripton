package bind.kripton109.settings;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType("settings")
public class ArgonSettings {
	
	public ArgonSettings()
	{
		application=new ApplicationSettings();
	}
	
	@Bind
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public String version="1.0.0";

	@Bind
	public ApplicationSettings application=new ApplicationSettings();
	
	@Bind
	public ViewFrustumSettings viewFrustum=new ViewFrustumSettings();
	
	@Bind
	public OpenGLSettings openGL=new OpenGLSettings();
	
	@Bind
	public LoggerSettings logger=new LoggerSettings();
		
	/**
	 * Impostazioni standard per una grafica did tipo 2d (simulata)
	 */
	public static ArgonSettings configAsWorld2D() {
		ArgonSettings instance = new ArgonSettings();

		// indica se disegnare in safe mode
		instance.openGL.safeMode=true;
		
		instance.viewFrustum.projection = ProjectionType.ORTHOGONAL;
		instance.viewFrustum.align = ViewFrustumAlignType.HEIGHT_ALIGN;		

		// vale solo per il 2D
		instance.viewFrustum.size = 1000.0f;

		// non serve
		instance.viewFrustum.fieldOfView = 0;

		instance.viewFrustum.zNear = -10.0f;
		instance.viewFrustum.zFar = 10.0f;

		instance.openGL.version=1;
		instance.openGL.debug = false;
		instance.openGL.maxFPS = 30;

		// impostiamo i listener di default
		instance.application.gestureListenerClazz = null;
		
		return instance;
	}

	/**
	 * @return
	 */
	public static ArgonSettings configAsWorld3D() {
		ArgonSettings instance = new ArgonSettings();

		// indica se disegnare in safe mode
		instance.openGL.safeMode=true;
		
		// parametri non devono essere resettati
		instance.application.resetConfig = false;

		instance.viewFrustum.projection = ProjectionType.PERSPECTIVE;
		instance.viewFrustum.align = ViewFrustumAlignType.HEIGHT_ALIGN;

		// vale solo per il 2D
		instance.viewFrustum.size = 0.0f;

		// non serve
		instance.viewFrustum.fieldOfView = 60f;
		instance.viewFrustum.zNear = 10f;
		instance.viewFrustum.zFar = 3000f;

		instance.openGL.version=1;
		instance.openGL.debug = false;
		instance.openGL.maxFPS = 30;

		// impostiamo i listener di default
		instance.application.gestureListenerClazz = null;

		return instance;
	}

}
