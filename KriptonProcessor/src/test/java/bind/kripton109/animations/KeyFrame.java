package bind.kripton109.animations;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

import bind.kripton109.animations.interpolations.Interpolation;
import bind.kripton109.animations.interpolations.InterpolationLinear;

@BindType
public class KeyFrame {

	@Bind(order = 0)
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public String name;

	@Bind(order = 1)
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public long duration;

	@BindDisabled
	protected Interpolation interpolation = InterpolationLinear.instance();

}
