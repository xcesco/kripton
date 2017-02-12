package bind.kripton109.settings;

import java.util.ArrayList;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

import bind.kripton109.settings.logger.ElioLoggerLevelType;

@BindType
public class LoggerSettings {

	@Bind
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public ElioLoggerLevelType level = ElioLoggerLevelType.NONE;

	@Bind
	@BindXml
	public ArrayList<LoggerAppenderSettings> appenders = new ArrayList<LoggerAppenderSettings>();
}
