package com.abubusoft.kripton.android;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.BinderReader;
import com.abubusoft.kripton.BinderWriter;
import com.abubusoft.kripton.Format;
import com.abubusoft.kripton.binder.exception.MappingException;
import com.abubusoft.kripton.binder.exception.ReaderException;
import com.abubusoft.kripton.binder.exception.WriterException;
import com.abubusoft.kripton.model0.Bean0;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.DEFAULT)
public class JSONTest0 extends BaseTest {

	private BinderWriter writer0;
	private BinderWriter writer1;
	private BinderReader reader;

	@Before
	public void createBinder() {
		writer0 = BinderFactory.getJSONWriter(Format.build().indent(false));
		writer1 = BinderFactory.getJSONWriter(Format.build());
		reader = BinderFactory.getJSONReader();
	}

	@Test(expected = WriterException.class)
	public void testNull() throws WriterException, MappingException, ReaderException {
		writer0.write(null);		
	}

	@Test
	public void testNoPropertis() throws WriterException, MappingException, ReaderException {
		String expected = "{\"bean0\":{}}";
		Bean0 bean = new Bean0();

		String buffer0 = writer0.write(bean);

		logger.info(buffer0);
		Assert.assertTrue(buffer0.equals(expected));

		Bean0 beanOutput = reader.read(Bean0.class, buffer0);
		String buffer1 = writer0.write(beanOutput);
		logger.info(buffer1);
	}

	@Test
	public void testPropertis() throws WriterException, MappingException, ReaderException, MalformedURLException {
		Bean0 beanInput = new Bean0();
		beanInput.fieldBigDecimal = BigDecimal.valueOf(25.0);
		beanInput.fieldBytes = "hello world!".getBytes();
		beanInput.fieldBigDecimal = new BigDecimal(123);
		beanInput.fieldBoolean = Boolean.TRUE;
		beanInput.fieldLong = 45L;
		beanInput.fieldString = "hellobean";
		beanInput.fieldCurrency = Currency.getInstance(Locale.ITALY);
		beanInput.fieldDouble = 451.0;
		beanInput.fieldFloat = 43f;
		beanInput.fieldInt = 11;
		beanInput.fieldShort = 12;
		beanInput.fieldByte = 14;
		beanInput.fieldTime = Time.valueOf(LocalTime.MIN);
		beanInput.fieldTimeZone = TimeZone.getDefault();
		beanInput.fieldURL = new URL("http://www.abubusoft.com");

		String buffer0 = writer0.write(beanInput);
		logger.info(buffer0);

		logger.info(buffer0);
		Bean0 beanOutput = reader.read(Bean0.class, buffer0);

		String buffer1 = writer1.write(beanOutput);
		Assert.assertTrue(buffer1.replaceAll("\\s+", "").equals(buffer0));

		Assert.assertTrue(beanOutput.fieldBigDecimal.equals(beanInput.fieldBigDecimal));
		Assert.assertTrue(beanOutput.fieldBoolean.equals(beanInput.fieldBoolean));
		Assert.assertTrue(beanOutput.fieldLong.equals(beanInput.fieldLong));
		Assert.assertArrayEquals("hello world!".getBytes(), beanOutput.fieldBytes);
		Assert.assertTrue(beanOutput.fieldCurrency.equals(beanInput.fieldCurrency));
		Assert.assertTrue(beanOutput.fieldDouble.equals(beanInput.fieldDouble));
		Assert.assertTrue(beanOutput.fieldFloat.equals(beanInput.fieldFloat));
		Assert.assertTrue(beanOutput.fieldInt.equals(beanInput.fieldInt));
		Assert.assertTrue(beanOutput.fieldShort.equals(beanInput.fieldShort));
		Assert.assertTrue(beanOutput.fieldString.equals(beanInput.fieldString));
		Assert.assertTrue(beanOutput.fieldTime.equals(beanInput.fieldTime));
		Assert.assertTrue(beanOutput.fieldTimeZone.equals(beanInput.fieldTimeZone));
		Assert.assertTrue(beanOutput.fieldURL.equals(beanInput.fieldURL));

		logger.info(buffer1);
	}

}
