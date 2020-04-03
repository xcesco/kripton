package bind.feature.git45;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean02 {
	Date date;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
