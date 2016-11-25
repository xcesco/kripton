package kripton73;

import java.math.BigDecimal;
import java.math.BigInteger;

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
		
	
	
}
