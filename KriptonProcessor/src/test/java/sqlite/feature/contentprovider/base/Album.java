package sqlite.feature.contentprovider.base;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Album extends Entity{

	public String name;
	
	@BindColumn(foreignKey=Artist.class, onUpdate=ForeignKeyAction.CASCADE)
	public long artistId;
}
