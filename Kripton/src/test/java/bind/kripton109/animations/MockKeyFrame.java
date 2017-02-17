package bind.kripton109.animations;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;


@BindType
public class MockKeyFrame extends KeyFrame {
		
	@Bind
	public int val;
	
	/**
	 * Costruisce frame
	 * 
	 * @param typeName
	 * @param value
	 * @param duration
	 * @return
	 */
	public static MockKeyFrame build(String name, int value, long duration)
	{
		MockKeyFrame ret=new MockKeyFrame();
		
		ret=new MockKeyFrame();
		ret.name=name;
		ret.val=value;
		ret.duration=duration;
		
		return ret;
	}

}
