package kripton71;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;

@BindType("root")
public class BeanElement71 {
	
//	@BindXml(elementTag="item")
//	public List<Byte> valueByteList;
//	
//	@BindXml(elementTag="item")
//	public List<Short> valueShortList;
//	
//	@BindXml(elementTag="item")
//	public List<Character> valueCharacterList;
//	
//	@BindXml(elementTag="item")
//	public List<Integer> valueIntList;
//	
//	@BindXml(elementTag="item")
//	public List<Long> valueLongList;
//	
//	@BindXml(elementTag="item")
//	public List<Float> valueFloatList;
//	
	//@BindXml(elementTag="item")
	//public List<Double> valueDoubleList;
	
	@BindXml(elementTag="item")
	public List<String> valueStringList;
	
	@BindXml(elementTag="item")
	public LinkedList<BeanEnum71> valueEnumList;
	
	@BindXml(elementTag="item")
	public HashSet<BeanEnum71> valueEnumSet;
	
//	@BindXml(elementTag="item")
//	public List<BigDecimal> valueBigDecimalList;
//	
//	@Bind(value="list", elementName="item")
//	@BindXml(elementTag="item")
//	public List<String> valueStringList;

}
