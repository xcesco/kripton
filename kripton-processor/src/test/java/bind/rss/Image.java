package bind.rss;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Image {
    public String url;
    public String title;
    public String link;
    public Integer width = 88;
    public Integer height = 31;
}