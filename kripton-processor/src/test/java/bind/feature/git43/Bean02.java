package bind.feature.git43;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean02 {
	Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
