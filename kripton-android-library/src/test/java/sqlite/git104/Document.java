package sqlite.git104;

import java.time.ZonedDateTime;

import com.abubusoft.kripton.android.annotation.BindSqlType;

@BindSqlType(name = "documents")
public class Document extends AbstractBean {

	private final DocumentInfo info;

	public Document(long id, ZonedDateTime updateTime, DocumentInfo info, ZonedDateTime data, String fileName,
			String secret) {
		super(id, updateTime);
		this.info = info;
		this.data = data;
		this.fileName = fileName;
		this.secret = secret;
	}

	public String getSecret() {
		return secret;
	}

	public DocumentInfo getInfo() {
		return info;
	}

	public ZonedDateTime getData() {
		return data;
	}

	public String getFileName() {
		return fileName;
	}

	private final ZonedDateTime data;
	private final String fileName;
	private final String secret;

}
