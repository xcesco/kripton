package bind.kripton110.model.stage2;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Name {

	public String common;
	public String official;

	@Bind("native")
	public Native _native;

}
