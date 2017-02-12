/**
 * 
 */
package bind.kripton109.animations.math;

import java.io.Serializable;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;

/**
 * Punto in uno spazio cartesiano tridimensionale, con la caratteristica di essere persistente mediante Kripton.
 * 
 * @author Francesco Benincasa
 *
 */
@BindType
public class Point3 {

	private static final long serialVersionUID = 4754358686291704165L;


	public Point3()
	{
		
	}
	
	public Point3(float x, float y, float z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		
	}
	
	@Bind
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public float x;
	
	@Bind
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public float y;
	
	@Bind
	@BindXml(xmlType=XmlType.ATTRIBUTE)
	public float z;
		
	
	public void add(float x1, float y1, float z1)
	{
		x+=x1;
		y+=y1;
		z+=z1;
	}
	
	public void add(Point3 value)
	{
		x+=value.x;
		y+=value.y;
		z+=value.z;
	}
		
	/**
	 * Definisce le coordinate di un punto
	 * @param newX
	 * @param newY
	 * @param newZ
	 */
	public void setCoords(float newX, float newY, float newZ)
	{
		x=newX;
		y=newY;
		z=newZ;
	}
	

	/* (non-Javadoc)
	 * @see org.abubu.elio.util.Copy#copyInto(java.lang.Object)
	 */
	public void copyInto(Point3 dest)
	{
		dest.x=x;
		dest.y=y;
		dest.z=z;
	}
	
	public Point3 copy()
	{
		return new Point3(x,y,z);
	}

	public static Point3 set(float x, float y, float z) {
		return new Point3(x, y, z);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3 other = (Point3) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}

}
