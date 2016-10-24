/**
 * 
 */
package com.abubusoft.kripton.android.kripton04;

import java.net.MalformedURLException;

import org.junit.Before;

import com.abubusoft.kripton.android.all.IssueBaseTest;

/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue4Test2 extends IssueBaseTest<Bean2> {
	

	@Before
	public void setup() throws MalformedURLException
	{
		beanInput=new Bean2();
		
		Bean0.fieldStatic="vaaa";
	}
}
