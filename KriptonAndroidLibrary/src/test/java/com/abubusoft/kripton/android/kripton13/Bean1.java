/**
 * 
 */
package com.abubusoft.kripton.android.kripton13;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;



/**
 * @author xcesco
 *
 */
@BindType
public class Bean1 implements Serializable {

	public Bean1()
	{
		list=new LinkedList<Bean0>();
	}


	private static final long serialVersionUID = 3113613163524431347L;

	@Bind(elementName="item")
	List<Bean0> list;

}
