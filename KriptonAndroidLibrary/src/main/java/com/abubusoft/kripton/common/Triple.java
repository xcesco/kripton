/**
 * 
 */
package com.abubusoft.kripton.common;
/**
 * @author xcesco
 *
 */
public class Triple<V1, V2, V3> {

	public Triple()
	{
		
	}
	
	public Triple(V1 v1, V2 v2, V3 v3)
	{
		this.v1=v1;
		this.v2=v2;
		this.v3=v3;
	}
	
	public V1 v1;
	
	public V2 v2;
	
	public V3 v3;
}