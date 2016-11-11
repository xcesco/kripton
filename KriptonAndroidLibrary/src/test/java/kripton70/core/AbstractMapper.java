package kripton70.core;

import kripton70.typeconverters.ByteConverter;
import kripton70.typeconverters.CharacterConverter;
import kripton70.typeconverters.DoubleConverter;
import kripton70.typeconverters.FloatConverter;
import kripton70.typeconverters.IntegerConverter;
import kripton70.typeconverters.LongConverter;
import kripton70.typeconverters.ShortConverter;
import kripton70.typeconverters.StringConverter;

public abstract class AbstractMapper<E> {	
	protected static ByteConverter byteMapper=new ByteConverter();
	protected static CharacterConverter characterMapper=new CharacterConverter();
	protected static ShortConverter shortMapper=new ShortConverter();	
	protected static IntegerConverter integerMapper=new IntegerConverter();
	protected static FloatConverter floatMapper=new FloatConverter();
	protected static DoubleConverter doubleMapper=new DoubleConverter();	
	protected static LongConverter longMapper=new LongConverter();	
	protected static StringConverter stringMapper=new StringConverter();
	
	protected Binder2 binderFactory;
	
	
}
