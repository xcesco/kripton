package bind.kripton76Errors;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.IncompatibleAttributesInAnnotationException;

import bind.AbstractBindTypeProcessorTest;

public class Test76Value extends AbstractBindTypeProcessorTest {
	
	@Test
	public void testCompile() throws InstantiationException, IllegalAccessException, IOException 
	{		
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanValue76.class, BeanEnum.class);
	}
	
	@Test
	public void testArrayOnXmlValue() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76Array.class, BeanEnum.class);
	}
	
	@Test
	public void testListOnXmlValue() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76List.class, BeanEnum.class);
	}
	
	@Test
	public void testSetOnXmlValue() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76Set.class, BeanEnum.class);
	}
	
	@Test
	public void testMapOnXmlValue() throws Throwable
	{
		this.expectedException(IncompatibleAttributesInAnnotationException.class);
		buildBindProcessorTest(BeanAttribute76Map.class, BeanEnum.class);
	}
	
	
	
}
