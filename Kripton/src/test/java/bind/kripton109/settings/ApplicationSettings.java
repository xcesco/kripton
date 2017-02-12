package bind.kripton109.settings;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class ApplicationSettings {
	
	/**
	 * Costruttore
	 */
	public ApplicationSettings()
	{
		resetConfig=false;
		gestureListenerClazz="org.abubu.argon.android.listener.ArgonGestureDefaultListenerImpl";
		upgradePolicyClazz="org.abubu.elio.application.ApplicationUpgradePolicyImpl";
	}
	
	/**
	 * Classe che implementa la prima activity da invocare dopo lo splash screen
	 */
	@Bind("applicationActivityClazz")
	public String activityClazz;
	
	/**
	 * Classe che implementa l'applicazione
	 */
	@Bind("applicationClazz")
	public String clazz;
	
	/**
	 * Classe che implementa la configurazione dell'applicazione
	 */
	@Bind("applicationConfigClazz")
	public String configClazz;

	
	/**
	 * Se impostato a true, fa resettare tutti i parametri di applicazione e di sistema
	 */
	@Bind("applicationResetConfig")
	public Boolean resetConfig;
	
	/**
	 * Classe da istanziare come listener per le gesture
	 */
	@Bind("applicationGestureListenerClazz")
	public String gestureListenerClazz;
	
	/**
	 * Classe da istanziare come gestore delle policy di upgrade di versione
	 */
	@Bind("applicationUpgradePolicyClazz")
	public String upgradePolicyClazz;
	
	/**
	 * indica il tempo di visualizzazione dello splash screen
	 */
	@Bind("applicationSplashScreenTimeout")
	public int splashScreenTimeout=3000;
	
	/**
	 * task da eseguire durante lo splash screen. Se non definito, non fa niente.
	 */
	@Bind("applicationStartupTaskClazz")
	public String startupTaskClazz;
	
	/**
	 * <p>Indica il modo di funzionare dell'applicazione.</p>
	 */
	@Bind("applicationMode")
	public ModeType mode;

}
