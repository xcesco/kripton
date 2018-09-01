package sqlite.feature.kotlin.immutable;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Image {
	private final String url;
	/**
	 * @param url
	 */
	public String getUrl() {
		return url;
	}


	

	/**
	 * @param url
	 * @param title
	 * @param link
	 * @param width
	 * @param height
	 */
	public Image() {
		super();
		this.url = null;
		this.title = null;
		this.link = null;
		this.width = 0;
		this.height = 0;
	}


	/**
	 * @param url
	 * @param title
	 * @param link
	 * @param width
	 * @param height
	 */
	public Image(String url, String title, String link, int width, int height) {
		super();
		this.url = url;
		this.title = title;
		this.link = link;
		this.width = width;
		this.height = height;
	}






	public String getTitle() {
		return title;
	}
	public String getLink() {
		return link;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	private final String title;
	private final String link;
	private final int width;
	private final int height;
}
