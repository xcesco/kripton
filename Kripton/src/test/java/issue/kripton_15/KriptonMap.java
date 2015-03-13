/**
 * 
 */
package issue.kripton_15;

import java.util.ArrayList;

import com.abubusoft.kripton.annotation.BindElement;
import com.abubusoft.kripton.annotation.BindRoot;
import com.abubusoft.kripton.annotation.BindValue;

/**
 * @author xcesco
 *
 */
@BindRoot
public class KriptonMap {
	
	public KriptonMap()
	{
		list=new ArrayList<KriptonMap.KriptonEntry>();
	}

	@BindRoot
	public static class KriptonEntry {
		@BindElement
		public Object key;
		
		@BindElement
		public Object value;
		
		public KriptonEntry()
		{
			
		}
		
		public KriptonEntry(Object key, Object value)
		{
			this.key=key;
			this.value=value;
		}
	}
	
	@BindElement
	public ArrayList<KriptonEntry> list;
}
