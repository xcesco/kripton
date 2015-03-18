package issue.kripton_15;

import java.io.Serializable;

import com.abubusoft.kripton.annotation.BindDefault;
import com.abubusoft.kripton.annotation.BindRoot;

@BindRoot
@BindDefault
public class Level1 implements Serializable {

	private static final long serialVersionUID = 2195822642540109939L;

	public Level2 info;
	
	public String name;
}
