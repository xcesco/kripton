package sqlite.feature.typeadapter;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Contact {

	protected long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String surname;

	@BindSqlAdapter(adapter = DateAdapterType.class, dataType = Long.class)
	public Date birthDay;
		
	@BindSqlAdapter(adapter = PasswordAdapterType.class, dataType = byte[].class)
	protected String password;
	
	@BindSqlAdapter(adapter = EnumAdapterType.class, dataType=Integer.class)
	public ContactType type;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public java.sql.Date updateDate;
	
	public java.sql.Time updateTime;
	
//	@BindSqlAdapter(adapter = AdapterType.class, dataType = Long.class)
//	public byte[] password;
}
