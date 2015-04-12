/**
 * 
 */
package com.abubusoft.kripton.database;

/**
 * @author xcesco
 *
 */
public class InsertOptions {
	
	public String name;
	
	public String fields="*";

	 public InsertOptions select(String value)
	 {
		 fields=value;
		 return this;
	 }
	 
	 public InsertOptions name(String value)
	 {
		 name=value;
		 return this;
	 }
	 
	 public static InsertOptions build()
	 {
		 return new InsertOptions();
	 }
	 
}
