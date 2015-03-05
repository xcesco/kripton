/**
 * 
 */
package issue.kripton_1;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.abubusoft.kripton.android.BinderFactory;
import com.abubusoft.kripton.android.BinderFactory.ReaderType;
import com.abubusoft.kripton.binder.BinderReader;
import com.abubusoft.kripton.binder.BinderWriter;
import com.abubusoft.kripton.binder.exception.MappingException;
import com.abubusoft.kripton.binder.exception.ReaderException;
import com.abubusoft.kripton.binder.exception.WriterException;

/**
 * @author xcesco
 *
 */
public class Main {

	private Employee bean;

	@Before
	public void setup()
	{
		bean=new Employee();
		
		bean.setName("Tonj");
		bean.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		int[] array= {1, 2, 4};
		
		bean.setTickets(array);
	}
	
	@Test
	public void testJSON() throws WriterException, MappingException, ReaderException {
		BinderWriter writer=BinderFactory.getJSONWriter();
		String buffer=writer.write(bean);
		System.out.println(buffer);
		
		BinderReader reader=BinderFactory.getJSONReader();
		Employee bean2=reader.read(Employee.class, buffer);
		String buffer2=writer.write(bean2);
		System.out.println(buffer2);
	}

	
	@Test
	public void testXMLSAX() throws WriterException, MappingException, ReaderException {
		BinderWriter writer=BinderFactory.getXMLWriter();
		String buffer=writer.write(bean);
		System.out.println(buffer);
		
		BinderReader reader=BinderFactory.getXMLReader();
		Employee bean2=reader.read(Employee.class, buffer);
		String buffer2=writer.write(bean2);
		System.out.println(buffer2);
	}
	
	@Test
	public void testXMLDOM() throws WriterException, MappingException, ReaderException {
		
		BinderWriter writer=BinderFactory.getXMLWriter();
		String buffer=writer.write(bean);
		System.out.println(buffer);
		
		BinderFactory.readerType=ReaderType.DOM;
		BinderReader reader=BinderFactory.getXMLReader();
		Employee bean2=reader.read(Employee.class, buffer);
		String buffer2=writer.write(bean2);
		System.out.println(buffer2);
	}
}
