package sqlite.feature.foreignkeyaction;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.orm.ForeignKeyAction;

@BindTable
public class Track {

	public long id;
	
	@BindColumn(foreignKey=Album.class, onDelete=ForeignKeyAction.CASCADE, onUpdate=ForeignKeyAction.SET_NULL)
	public long albumId;
}
