package sqlite.feature.relations.case4.model;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindSqlRelation;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindAdapter;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Channel extends Entity {

	public String title;
	public String link;
	public String description;
	public String language;
	public String copyright;
	
	@BindAdapter(adapter = DateAdapter.class)
	public Date pubDate;
	
	@BindAdapter(adapter = DateAdapter.class)
	public Date lastBuildDate;
	
	public Image image;

	@Bind("item")
	@BindSqlRelation
	public List<Article> articles;
}
