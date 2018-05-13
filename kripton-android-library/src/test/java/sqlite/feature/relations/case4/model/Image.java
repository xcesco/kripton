package sqlite.feature.relations.case4.model;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Image {
	public String url;
	public String title;
	public String link;
	public int width = 88;
	public int height = 31;
}
