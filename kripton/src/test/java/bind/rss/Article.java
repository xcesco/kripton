package bind.rss;


import java.net.URL;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.annotation.BindXmlNamespace;
import com.abubusoft.kripton.annotation.BindXmlType;

@BindType("item")
@BindXmlType(namespaces={
        @BindXmlNamespace(prefix="dc",uri="http://purl.org/dc/elements/1.1/"),
        @BindXmlNamespace(prefix="content",uri="http://purl.org/dc/elements/1.1/"),
})
public class Article extends Entity {
    public String title;
    public String description;
    public URL link;
    public String author;

    public String guid;

    public URL comments;

    public long channelId;

    @Bind("thumbnail")
    @BindXml(namespace="media")
    public Thumbnail thumbnail;
}