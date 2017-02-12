package bind.kripton109.settings;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

import bind.kripton109.settings.logger.ElioLoggerLevelType;

/**
 * Configurazione di un appender di log
 * 
 * @author Francesco Benincasa
 * 
 */
@BindType
public class LoggerAppenderSettings {

	@Bind
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public String tag = "";

	@Bind
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public ElioLoggerLevelType level;

}
