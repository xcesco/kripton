package sqlite.feature.relations.case4.model;

import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType(value="rss")
public class RSSFeed {

	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public String version;

	@Bind("channel")	
	public List<Channel> channels;
}