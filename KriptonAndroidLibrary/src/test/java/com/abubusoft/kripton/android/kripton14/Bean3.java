/**
 * 
 */
package com.abubusoft.kripton.android.kripton14;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.binder.xml.XmlType;



/**
 * @author xcesco
 *
 */
@BindType
public class Bean3 implements Serializable {

	public Bean3()
	{
		set=new HashSet<Bean0>();
	}


	private static final long serialVersionUID = 3113613163524431347L;

	@BindXml(value=XmlType.ATTRIBUTE)
	Set<Bean0> set;

}
