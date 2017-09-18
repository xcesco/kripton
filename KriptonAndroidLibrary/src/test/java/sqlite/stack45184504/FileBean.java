package sqlite.stack45184504;

import java.util.Date;

import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class FileBean {
	public long id;
	
	public Date date;
	
	public String title;
	
	public String text;
	
	public String address;
	
	public byte[] image;
}
