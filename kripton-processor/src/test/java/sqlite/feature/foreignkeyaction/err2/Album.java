package sqlite.feature.foreignkeyaction.err2;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;

@BindTable
public class Album {
	
	public long id;

	@BindColumn(onDelete=ForeignKeyAction.CASCADE)
	public long artistId;
	
	public String name;
		
}
