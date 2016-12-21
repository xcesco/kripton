package bind.kripton87TypeAdapter;

import java.io.IOException;

import org.junit.Test;

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
		buildBindProcessorTest(BooleanByteArrayTypeAdapter.class, EnumIntegerTypeAdapter.class, Bean87A_3.class, Enum87A.class);
	}
}
