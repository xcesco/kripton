package sqlite.git20.immutable;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Count {
	/**
	 * @param title
	 * @param count
	 */
	public Count(String title, int count) {
		super();
		this.title = title;
		this.count = count;
	}
	private String title;
	public String getTitle() {
		return title;
	}
	public int getCount() {
		return count;
	}
	private int count;
}
