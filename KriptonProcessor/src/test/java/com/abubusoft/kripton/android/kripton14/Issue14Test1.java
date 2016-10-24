/**
 * 
 */
package com.abubusoft.kripton.android.kripton14;

import org.junit.Before;

import com.abubusoft.kripton.android.all.IssueBaseTest;


/**
 * Test array of objects
 * 
 * @author xcesco
 *
 */
public class Issue14Test1 extends IssueBaseTest<Bean1> {

	@Before
	public void setup()
	{
		beanInput=new Bean1();
		
		beanInput.set.add(new Bean0("helo", 244));
		beanInput.set.add(new Bean0("helo", 244));
		
	}

}
