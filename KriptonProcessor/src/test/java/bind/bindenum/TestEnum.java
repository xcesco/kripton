package bind.bindenum;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.InvalidKindForAnnotationException;

import bind.AbstractBindTypeProcessorTest;

/**
 * Test bean field
 * 
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class TestEnum extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Bean.class, LogLevel.class);
	}

	/**
	 * Try to annotate Enum with @BindType
	 * 
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@Test
	public void testErr01() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(InvalidKindForAnnotationException.class);
		buildBindProcessorTest(ErrBean.class, ErrLogLevel.class);
	}

}
