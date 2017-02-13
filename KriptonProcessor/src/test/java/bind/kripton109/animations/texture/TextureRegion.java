package bind.kripton109.animations.texture;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

@BindType
public class TextureRegion {

	@Bind
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public float highX;
	@Bind
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public float lowX;
	@Bind
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public float highY;
	@Bind
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public float lowY;

	public TextureRegion() {
		super();
	}

}