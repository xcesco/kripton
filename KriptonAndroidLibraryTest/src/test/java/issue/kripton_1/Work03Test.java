/**
 * 
 */
package issue.kripton_1;

import issue.kripton_1.Bean03.SubBean03;

import java.util.Calendar;
import java.util.Date;

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
public class Work03Test {

	private Bean03 bean;

	@Before
	public void setup()
	{
		bean=new Bean03();
		
		bean.setName("Tonj");
		bean.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		bean.setBirthday(calendar.getTime());
		SubBean03[] array= new SubBean03[3];
		array[0]=new SubBean03(new Date(), "ticket01");
		array[1]=new SubBean03(new Date(), "ticket02");
		array[2]=new SubBean03(new Date(), "ticket03");		
		
		bean.setTickets(array);
	}
	
	@Test
	public void testJSON() throws WriterException, MappingException, ReaderException {
		BinderWriter writer=BinderFactory.getJSONWriter();
		String buffer=writer.write(bean);
		System.out.println(buffer);
		
		BinderReader reader=BinderFactory.getJSONReader();
		Bean03 bean2=reader.read(Bean03.class, buffer);
		String buffer2=writer.write(bean2);
		System.out.println(buffer2);
	}

	
	@Test
	public void testXMLSAX() throws WriterException, MappingException, ReaderException {
		BinderWriter writer=BinderFactory.getXMLWriter();
		String buffer=writer.write(bean);
		System.out.println(buffer);
		
		BinderFactory.readerType=ReaderType.SAX;
		BinderReader reader=BinderFactory.getXMLReader();
		Bean03 bean2=reader.read(Bean03.class, buffer);
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
		Bean03 bean2=reader.read(Bean03.class, buffer);
		String buffer2=writer.write(bean2);
		System.out.println(buffer2);
	}
}
