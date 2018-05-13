package sqlite.feature.childselect.error7;

import java.net.URL;

import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType("item")
public class Article extends Entity {
	public String title;
	public String description;
	public URL link;
	public String author;
	public String guid;
	public URL comments;

	@BindSqlColumn(parentEntity=Channel.class)
	public long channelId;
}
