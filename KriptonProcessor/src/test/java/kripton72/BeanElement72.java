package kripton72;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindJson;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;

@BindType("root")
public class BeanElement72 {
	
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
	public Set<String> valueStringSet;
	
	public HashSet<Double> valueDoubleSet;
		
	@BindXml(elementTag="item")
	public LinkedHashSet<BeanEnum72> valueEnumSet;
	
//	@BindXml(elementTag="item")
//	public List<BigDecimal> valueBigDecimalList;
//	
//	@Bind(value="list", elementName="item")
//	@BindXml(elementTag="item")
//	public List<String> valueStringList;

}
