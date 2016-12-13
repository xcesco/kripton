package bind.kripton72;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;

@BindType
public class Bean72 {
	
	public Bean72()
	{
		
	}
	
	public Bean72(String name)
	{
		this.name=name;
	}
	
	public String name;
	
	@BindXml(elementTag="item")
	public HashSet<Bean72> valueBeanSet;
	
	@Bind(enabled=true)
	public HashSet<BigDecimal> valueBigDecimalSet;
	
	@Bind(enabled=true)
	@BindXml(elementTag="item")
	public HashSet<BigInteger> valueBigIntegerSet;
	
	@BindXml(elementTag="item")
	public Set<Byte> valueByteSet;
	
	@BindXml(elementTag="item")
	public HashSet<Character> valueCharacterSet;
	
	@BindXml(elementTag="item")
	public Set<Double> valueDoubleSet;
	
	public HashSet<Enum72> valueEnumSet;
	
	@BindXml(elementTag="item")
	public Set<Float> valueFloatSet;
	
	@BindXml(elementTag="item")
	private Set<Integer> valueIntSet;
	
	public Set<Integer> getValueIntSet() {
		return valueIntSet;
	}

	public void setValueIntSet(Set<Integer> valueIntSet) {
		this.valueIntSet = valueIntSet;
	}

	@BindXml(elementTag="item")
	public Set<Long> valueLongSet;

	@BindXml(elementTag="item")
	public Set<Short> valueShortSet;
	
	@BindXml(elementTag="item")
	public Set<String> valueStringSet;
	
	@Bind(enabled=true)
	public String zalueStringFinal;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((valueBeanSet == null) ? 0 : valueBeanSet.hashCode());
		result = prime * result + ((valueBigDecimalSet == null) ? 0 : valueBigDecimalSet.hashCode());
		result = prime * result + ((valueBigIntegerSet == null) ? 0 : valueBigIntegerSet.hashCode());
		result = prime * result + ((valueByteSet == null) ? 0 : valueByteSet.hashCode());
		result = prime * result + ((valueCharacterSet == null) ? 0 : valueCharacterSet.hashCode());
		result = prime * result + ((valueDoubleSet == null) ? 0 : valueDoubleSet.hashCode());
		result = prime * result + ((valueEnumSet == null) ? 0 : valueEnumSet.hashCode());
		result = prime * result + ((valueFloatSet == null) ? 0 : valueFloatSet.hashCode());
		result = prime * result + ((valueIntSet == null) ? 0 : valueIntSet.hashCode());
		result = prime * result + ((valueLongSet == null) ? 0 : valueLongSet.hashCode());
		result = prime * result + ((valueShortSet == null) ? 0 : valueShortSet.hashCode());
		result = prime * result + ((valueStringSet == null) ? 0 : valueStringSet.hashCode());
		result = prime * result + ((zalueStringFinal == null) ? 0 : zalueStringFinal.hashCode());
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
		Bean72 other = (Bean72) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (valueBeanSet == null) {
			if (other.valueBeanSet != null)
				return false;
		} else if (!valueBeanSet.equals(other.valueBeanSet))
			return false;
		if (valueBigDecimalSet == null) {
			if (other.valueBigDecimalSet != null)
				return false;
		} else if (!valueBigDecimalSet.equals(other.valueBigDecimalSet))
			return false;
		if (valueBigIntegerSet == null) {
			if (other.valueBigIntegerSet != null)
				return false;
		} else if (!valueBigIntegerSet.equals(other.valueBigIntegerSet))
			return false;
		if (valueByteSet == null) {
			if (other.valueByteSet != null)
				return false;
		} else if (!valueByteSet.equals(other.valueByteSet))
			return false;
		if (valueCharacterSet == null) {
			if (other.valueCharacterSet != null)
				return false;
		} else if (!valueCharacterSet.equals(other.valueCharacterSet))
			return false;
		if (valueDoubleSet == null) {
			if (other.valueDoubleSet != null)
				return false;
		} else if (!valueDoubleSet.equals(other.valueDoubleSet))
			return false;
		if (valueEnumSet == null) {
			if (other.valueEnumSet != null)
				return false;
		} else if (!valueEnumSet.equals(other.valueEnumSet))
			return false;
		if (valueFloatSet == null) {
			if (other.valueFloatSet != null)
				return false;
		} else if (!valueFloatSet.equals(other.valueFloatSet))
			return false;
		if (valueIntSet == null) {
			if (other.valueIntSet != null)
				return false;
		} else if (!valueIntSet.equals(other.valueIntSet))
			return false;
		if (valueLongSet == null) {
			if (other.valueLongSet != null)
				return false;
		} else if (!valueLongSet.equals(other.valueLongSet))
			return false;
		if (valueShortSet == null) {
			if (other.valueShortSet != null)
				return false;
		} else if (!valueShortSet.equals(other.valueShortSet))
			return false;
		if (valueStringSet == null) {
			if (other.valueStringSet != null)
				return false;
		} else if (!valueStringSet.equals(other.valueStringSet))
			return false;
		if (zalueStringFinal == null) {
			if (other.zalueStringFinal != null)
				return false;
		} else if (!zalueStringFinal.equals(other.zalueStringFinal))
			return false;
		return true;
	}
	
	
	
}
