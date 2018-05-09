package bind.rss;

import java.net.URL;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class Thumbnail {
    @BindXml(xmlType = XmlType.ATTRIBUTE)
    public Integer width;

    @BindXml(xmlType = XmlType.ATTRIBUTE)
    public Integer height;

    @BindXml(xmlType = XmlType.ATTRIBUTE)
    public URL url;
}