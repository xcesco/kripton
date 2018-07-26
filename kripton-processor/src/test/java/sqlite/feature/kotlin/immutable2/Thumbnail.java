package sqlite.feature.kotlin.immutable2;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

import java.net.URL;

@BindType
public class Thumbnail {
    @BindXml(xmlType = XmlType.ATTRIBUTE)
    private final long width;

    public long getWidth() {
		return width;
	}

	public long getHeight() {
		return height;
	}

	public URL getUrl() {
		return url;
	}

	/**
	 * @param width
	 * @param height
	 * @param url
	 */
	public Thumbnail(long width, long height, URL url) {
		super();
		this.width = width;
		this.height = height;
		this.url = url;
	}
	
	public Thumbnail() {
		super();
		this.width = 0;
		this.height = 0;
		this.url = null;
	}

	@BindXml(xmlType = XmlType.ATTRIBUTE)
    private final long height;

    @BindXml(xmlType = XmlType.ATTRIBUTE)
    private final URL url;
}
