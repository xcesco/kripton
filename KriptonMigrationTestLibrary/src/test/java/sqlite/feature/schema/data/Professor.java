package sqlite.feature.schema.data;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Professor extends Entity {
	public Date birthDate;
}
