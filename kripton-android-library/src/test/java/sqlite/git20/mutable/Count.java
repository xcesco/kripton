package sqlite.git20.mutable;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Count {
	private String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private int count;
}
