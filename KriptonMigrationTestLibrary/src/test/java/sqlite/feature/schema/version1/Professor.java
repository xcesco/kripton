package sqlite.feature.schema.version1;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Professor extends Entity {
	public Date birthDate;
}
