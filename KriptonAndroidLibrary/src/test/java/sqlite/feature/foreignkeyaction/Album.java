package sqlite.feature.foreignkeyaction;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;

@BindTable
public class Album {
	
	public long id;

	@BindColumn(foreignKey=Artist.class, onDelete=ForeignKeyAction.CASCADE, onUpdate=ForeignKeyAction.CASCADE)
	public long artistId;
	
	public String name;
		
}
