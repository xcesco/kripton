package bind.kripton109.animations;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindDisabled;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

import bind.kripton109.animations.interpolations.Interpolation;
import bind.kripton109.animations.interpolations.InterpolationLinear;

/**
 * key frame delle animazioni. Ogni frame contiene un valore. A seconda del tipo di animazione il valore cambia. Ogni frame può avere anche un nome. Sempre e comunque deve avere
 * una durata.
 * 
 * @author Francesco Benincasa
 * 
 */
@BindType
public class KeyFrame {
	
	/**
	 * nome del keyframe
	 */
	@Bind(order=0)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public String name;
	
	/**
	 * <p>
	 * Durata del frame in millisecondi.
	 * </p>
	 */
	@Bind(order=1)
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public long duration;
	
	/**
	 * interpolazione da usare nel momento in cui questo value diventa quello corrente.
	 */
	@BindDisabled
	protected Interpolation interpolation = InterpolationLinear.instance();

		

}
