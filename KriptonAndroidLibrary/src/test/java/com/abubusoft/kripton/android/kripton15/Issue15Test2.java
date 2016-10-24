/**
 * 
 */
package com.abubusoft.kripton.android.kripton15;

import org.junit.Before;

import com.abubusoft.kripton.android.all.IssueBaseTest;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue15Test2 extends IssueBaseTest<Bean2> {

	@Before
	public void setup()
	{
		beanInput=new Bean2();
		
		beanInput.map.put(1, "content1");
		beanInput.map.put(2, "content2");
		
	}

}
