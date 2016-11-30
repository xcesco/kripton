package bind.kripton73;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;

@BindType
public class BeanElement73 {
	
	public BeanElement73()
	{
		
	}
	
	public BeanElement73(String name)
	{
		this.name=name;
	}
	
	public String name;
	
	@BindXml(elementTag="item")
	public BeanElement73[] valueBeanArray;
	
	@Bind(enabled=true)
	public BigDecimal[] valueBigDecimalArray;
	
	@Bind(enabled=true)
	@BindXml(elementTag="item")
	public BigInteger[] valueBigIntegerArray;
	
	@BindXml(elementTag="item")
	public Byte[] valueByteArray;
	
	@BindXml(elementTag="item")
	public Character[] valueCharacterArray;
	
	@BindXml(elementTag="item")
	public Double[] valueDoubleArray;
	
	public BeanEnum73[] valueEnumArray;
	
	@BindXml(elementTag="item")
	public Float[] valueFloatArray;
	
	@BindXml(elementTag="item")
	private Integer[] valueIntArray;
	
	public Integer[] getValueIntArray() {
		return valueIntArray;
	}

	public void setValueIntArray(Integer[] valueIntArray) {
		this.valueIntArray = valueIntArray;
	}

	@BindXml(elementTag="item")
	public Long[] valueLongArray;

	@BindXml(elementTag="item")
	public Short[] valueShortArray;
	
	@BindXml(elementTag="item")
	public String[] valueStringArray;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(valueBeanArray);
		result = prime * result + Arrays.hashCode(valueBigDecimalArray);
		result = prime * result + Arrays.hashCode(valueBigIntegerArray);
		result = prime * result + Arrays.hashCode(valueByteArray);
		result = prime * result + Arrays.hashCode(valueCharacterArray);
		result = prime * result + Arrays.hashCode(valueDoubleArray);
		result = prime * result + Arrays.hashCode(valueEnumArray);
		result = prime * result + Arrays.hashCode(valueFloatArray);
		result = prime * result + Arrays.hashCode(valueIntArray);
		result = prime * result + Arrays.hashCode(valueLongArray);
		result = prime * result + Arrays.hashCode(valueShortArray);
		result = prime * result + Arrays.hashCode(valueStringArray);
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
		BeanElement73 other = (BeanElement73) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(valueBeanArray, other.valueBeanArray))
			return false;
		if (!Arrays.equals(valueBigDecimalArray, other.valueBigDecimalArray))
			return false;
		if (!Arrays.equals(valueBigIntegerArray, other.valueBigIntegerArray))
			return false;
		if (!Arrays.equals(valueByteArray, other.valueByteArray))
			return false;
		if (!Arrays.equals(valueCharacterArray, other.valueCharacterArray))
			return false;
		if (!Arrays.equals(valueDoubleArray, other.valueDoubleArray))
			return false;
		if (!Arrays.equals(valueEnumArray, other.valueEnumArray))
			return false;
		if (!Arrays.equals(valueFloatArray, other.valueFloatArray))
			return false;
		if (!Arrays.equals(valueIntArray, other.valueIntArray))
			return false;
		if (!Arrays.equals(valueLongArray, other.valueLongArray))
			return false;
		if (!Arrays.equals(valueShortArray, other.valueShortArray))
			return false;
		if (!Arrays.equals(valueStringArray, other.valueStringArray))
			return false;
		return true;
	}
		
	
	
}
