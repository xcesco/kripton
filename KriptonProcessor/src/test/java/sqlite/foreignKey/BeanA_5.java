package sqlite.foreignKey;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class BeanA_5 {

	public long id;

	@BindColumn(foreignKey = BeanA_6.class, nullable = false)
	public Long beanA2Id;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beanA2Id == null) ? 0 : beanA2Id.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((valueString == null) ? 0 : valueString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BeanA_5 other = (BeanA_5) obj;
		if (beanA2Id == null) {
			if (other.beanA2Id != null)
				return false;
		} else if (!beanA2Id.equals(other.beanA2Id))
			return false;
		if (id != other.id)
			return false;
		if (valueString == null) {
			if (other.valueString != null)
				return false;
		} else if (!valueString.equals(other.valueString))
			return false;
		return true;
	}

	public String valueString;
}
