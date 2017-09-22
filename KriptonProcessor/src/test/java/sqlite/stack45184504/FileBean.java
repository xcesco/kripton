package sqlite.stack45184504;

import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable(name="files")
public class FileBean {
	public long id;
		
	public String name;
	
	public byte[] content;
	
	public String contentType;
}
