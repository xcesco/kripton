/**
 * 
 */
package com.abubusoft.kripton.android;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Time;
import java.util.Currency;
import java.util.TimeZone;

import com.abubusoft.kripton.annotation.BindElement;

/**
 * Bean for test
 * @author xcesco
 *
 */
public class Bean0 {

	@BindElement
	public BigDecimal fieldBigDecimal;
	
	@BindElement
	public Boolean fieldBoolean;
	
	@BindElement
	public byte[] fieldBytes;
	
	@BindElement
	public Currency fieldCurrency;

	@BindElement
	public Double fieldDouble;

	@BindElement
	public Float fieldFloat;

	@BindElement
	public Integer fieldInt;

	@BindElement
	public Long fieldLong;

	@BindElement
	public Short fieldShort;

	@BindElement
	public String fieldString;

	@BindElement
	public Time fieldTime;
	
	@BindElement
	public TimeZone fieldTimeZone;
	
	@BindElement
	public URL fieldURL;

	@BindElement
	public Byte fieldByte;

}
