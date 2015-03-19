/**
 * 
 */
package issue.kripton_15;

import java.util.ArrayList;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindValue;

/**
 * @author xcesco
 *
 */
@BindType
public class KriptonMap {
	
	public KriptonMap()
	{
		list=new ArrayList<KriptonMap.KriptonEntry>();
	}

	@BindType
	public static class KriptonEntry {
		@Bind
		public Object key;
		
		@Bind
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
	
	@Bind
	public ArrayList<KriptonEntry> list;
}
