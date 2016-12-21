package bind.kripton87TypeAdapter;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.IncompatibleAnnotationException;

import bind.AbstractBindTypeProcessorTest;

public class Test87ACompile extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile_1() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(DateLongTypeAdapter.class, StringInverterTypeAdapter.class, UrlByteArrayTypeAdapter.class, Bean87A_1.class);
	}
	
	@Test
	public void testCompile_2() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(UrlByteArrayTypeAdapter.class, Bean87A_2.class);
	}
	
	@Test
	public void testCompile_3() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(BooleanByteArrayTypeAdapter.class, Enum87IntegerTypeAdapter.class, Bean87A_3.class, Enum87A.class);
	}
	
	@Test
	public void testCompile_4() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(IncompatibleAnnotationException.class);
		buildBindProcessorTest(BooleanBigDecimalTypeAdapter.class, Enum87BigIntegerTypeAdapter.class, Bean87A_4.class, Enum87A.class);
	}
	
	@Test
	public void testCompile_5() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(BooleanBigDecimalTypeAdapter.class, Enum87BigIntegerTypeAdapter.class, Bean87A_5.class, Enum87A.class);
	}
	
	@Test
	public void testCompile_6() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(StringUrlTypeAdapter.class, Bean87A_6.class, Enum87A.class);
	}
	
	@Test
	public void testCompile_7() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(StringInverterTypeAdapter.class, Bean87A_7.class, Enum87A.class);
	}
	
	@Test
	public void testCompile_8() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(IncompatibleAnnotationException.class);
		buildBindProcessorTest(StringEnum87ATypeAdapter.class, Bean87A_8.class, Enum87A.class);
	}
}
