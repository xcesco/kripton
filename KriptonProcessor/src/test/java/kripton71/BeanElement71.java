package kripton71;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;

@BindType(allFields=false)
public class BeanElement71 {
	
	public BeanElement71()
	{
		
	}
	
	public BeanElement71(String name)
	{
		this.name=name;
	}
	
	public String name;
	
	@BindXml(elementTag="item")
	public LinkedList<BeanElement71> valueBeanList;
	
	@Bind(enabled=true)
	public LinkedList<BigDecimal> valueBigDecimalList;
	
	@Bind(enabled=true)
	@BindXml(elementTag="item")
	public LinkedList<BigInteger> valueBigIntegerList;
	
	@BindXml(elementTag="item")
	public List<Byte> valueByteList;
	
	@BindXml(elementTag="item")
	public ArrayList<Character> valueCharacterList;
	
	@BindXml(elementTag="item")
	public List<Double> valueDoubleList;
	
	public LinkedList<BeanEnum71> valueEnumList;
	
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
	
	public List<Integer> getValueIntList() {
		return valueIntList;
	}
	
	public void setValueIntList(List<Integer> valueIntList) {
		this.valueIntList = valueIntList;
	}
	
}
