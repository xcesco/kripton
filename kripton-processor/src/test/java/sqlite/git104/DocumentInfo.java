package sqlite.git104;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class DocumentInfo {
	private final String bucket1;
	private final String bucket2;

	public String getBucket1() {
		return bucket1;
	}

	public String getBucket2() {
		return bucket2;
	}

	public DocumentInfo(String bucket1, String bucket2) {
		super();
		this.bucket1 = bucket1;
		this.bucket2 = bucket2;
	}

}
