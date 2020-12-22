/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package bind.feature.generichierarchy.kripton109.animations.math;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindXml;
import com.abubusoft.kripton.xml.XmlType;


/**
 * The Class Point3.
 */
@BindType
public class Point3 {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4754358686291704165L;

	/**
	 * Instantiates a new point 3.
	 */
	public Point3() {

	}

	/**
	 * Instantiates a new point 3.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Point3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;

	}

	/** The x. */
	@Bind
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public float x;

	/** The y. */
	@Bind
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public float y;

	/** The z. */
	@Bind
	@BindXml(xmlType = XmlType.ATTRIBUTE)
	public float z;

	/**
	 * Adds the.
	 *
	 * @param x1 the x 1
	 * @param y1 the y 1
	 * @param z1 the z 1
	 */
	public void add(float x1, float y1, float z1) {
		x += x1;
		y += y1;
		z += z1;
	}

	/**
	 * Adds the.
	 *
	 * @param value the value
	 */
	public void add(Point3 value) {
		x += value.x;
		y += value.y;
		z += value.z;
	}

	/**
	 * Sets the coords.
	 *
	 * @param newX the new X
	 * @param newY the new Y
	 * @param newZ the new Z
	 */
	public void setCoords(float newX, float newY, float newZ) {
		x = newX;
		y = newY;
		z = newZ;
	}

	/**
	 * Copy into.
	 *
	 * @param dest the dest
	 */
	public void copyInto(Point3 dest) {
		dest.x = x;
		dest.y = y;
		dest.z = z;
	}

	/**
	 * Copy.
	 *
	 * @return the point 3
	 */
	public Point3 copy() {
		return new Point3(x, y, z);
	}

	/**
	 * Sets the.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the point 3
	 */
	public static Point3 set(float x, float y, float z) {
		return new Point3(x, y, z);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		result = prime * result + Float.floatToIntBits(z);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
