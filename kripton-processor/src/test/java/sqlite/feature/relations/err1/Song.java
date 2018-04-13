package sqlite.feature.relations.err1;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Song {
	public long id;
	public String name;
	
	@BindColumn(parentEntity=Album.class)
	public long albumId;
}
