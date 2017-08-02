package sqlite.feature.schema.version1;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindTable(indexes={"surname"})
public class Professor extends Entity {
	public Date birthDate;
	
	@BindColumn(nullable=false)
	public String surname;
}
