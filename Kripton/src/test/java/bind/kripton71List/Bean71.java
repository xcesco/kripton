package bind.kripton71List;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;

@BindType
public class Bean71 {
	
	@Bind(enabled=true)
	@BindXml(elementTag="item")
	public LinkedList<Bean71> valueBeanList;
	
	@Bind(enabled=true)
	public LinkedList<BigDecimal> valueBigDecimalList;
	
	@BindXml(elementTag="item")
	public LinkedList<BigInteger> valueBigIntegerList;

	@BindXml(elementTag="item")
	public List<Byte> valueByteList;

	@BindXml(elementTag="item")
	public ArrayList<Character> valueCharacterList;
	
	@BindXml(elementTag="item")
	public List<Double> valueDoubleList;
		
	public LinkedList<Enum71> valueEnumList;
	
	@BindXml(elementTag="item")
	public List<Float> valueFloatList;
	
	@BindXml(elementTag="item")
	private List<Integer> valueIntList;
	
	@BindXml(elementTag="item")
	public List<Long> valueLongList;
	
	@BindXml(elementTag="item")
	public List<Short> valueShortList;
	
	@BindXml(elementTag="item")
	public List<String> valueStringList;
	
	@Bind(enabled=true)
	public String zalueStringFinal;
	
	public Bean71()
	{
		
	}

	public Bean71(String name)
	{
		this.zalueStringFinal=name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bean71 other = (Bean71) obj;
		if (valueBeanList == null) {
			if (other.valueBeanList != null)
				return false;
		} else if (!valueBeanList.equals(other.valueBeanList))
			return false;
		if (valueBigDecimalList == null) {
			if (other.valueBigDecimalList != null)
				return false;
		} else if (!valueBigDecimalList.equals(other.valueBigDecimalList))
			return false;
		if (valueBigIntegerList == null) {
			if (other.valueBigIntegerList != null)
				return false;
		} else if (!valueBigIntegerList.equals(other.valueBigIntegerList))
			return false;
		if (valueByteList == null) {
			if (other.valueByteList != null)
				return false;
		} else if (!valueByteList.equals(other.valueByteList))
			return false;
		if (valueCharacterList == null) {
			if (other.valueCharacterList != null)
				return false;
		} else if (!valueCharacterList.equals(other.valueCharacterList))
			return false;
		if (valueDoubleList == null) {
			if (other.valueDoubleList != null)
				return false;
		} else if (!valueDoubleList.equals(other.valueDoubleList))
			return false;
		if (valueEnumList == null) {
			if (other.valueEnumList != null)
				return false;
		} else if (!valueEnumList.equals(other.valueEnumList))
			return false;
		if (valueFloatList == null) {
			if (other.valueFloatList != null)
				return false;
		} else if (!valueFloatList.equals(other.valueFloatList))
			return false;
		if (valueIntList == null) {
			if (other.valueIntList != null)
				return false;
		} else if (!valueIntList.equals(other.valueIntList))
			return false;
		if (valueLongList == null) {
			if (other.valueLongList != null)
				return false;
		} else if (!valueLongList.equals(other.valueLongList))
			return false;
		if (valueShortList == null) {
			if (other.valueShortList != null)
				return false;
		} else if (!valueShortList.equals(other.valueShortList))
			return false;
		if (valueStringList == null) {
			if (other.valueStringList != null)
				return false;
		} else if (!valueStringList.equals(other.valueStringList))
			return false;
		if (zalueStringFinal == null) {
			if (other.zalueStringFinal != null)
				return false;
		} else if (!zalueStringFinal.equals(other.zalueStringFinal))
			return false;
		return true;
	}
	
	public List<Integer> getValueIntList() {
		return valueIntList;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valueBeanList == null) ? 0 : valueBeanList.hashCode());
		result = prime * result + ((valueBigDecimalList == null) ? 0 : valueBigDecimalList.hashCode());
		result = prime * result + ((valueBigIntegerList == null) ? 0 : valueBigIntegerList.hashCode());
		result = prime * result + ((valueByteList == null) ? 0 : valueByteList.hashCode());
		result = prime * result + ((valueCharacterList == null) ? 0 : valueCharacterList.hashCode());
		result = prime * result + ((valueDoubleList == null) ? 0 : valueDoubleList.hashCode());
		result = prime * result + ((valueEnumList == null) ? 0 : valueEnumList.hashCode());
		result = prime * result + ((valueFloatList == null) ? 0 : valueFloatList.hashCode());
		result = prime * result + ((valueIntList == null) ? 0 : valueIntList.hashCode());
		result = prime * result + ((valueLongList == null) ? 0 : valueLongList.hashCode());
		result = prime * result + ((valueShortList == null) ? 0 : valueShortList.hashCode());
		result = prime * result + ((valueStringList == null) ? 0 : valueStringList.hashCode());
		result = prime * result + ((zalueStringFinal == null) ? 0 : zalueStringFinal.hashCode());
		return result;
	}
	
	public void setValueIntList(List<Integer> valueIntList) {
		this.valueIntList = valueIntList;
	}
	
}
