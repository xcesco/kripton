package bind.kripton109.animations.math;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Vector3 extends Point3 {

	private static final long serialVersionUID = -906580073899163617L;

	public Vector3() {

	}

	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;

	}

	public void add(Vector3 b) {
		this.x += b.x;
		this.y += b.y;
		this.z += b.z;
	}

	public static Vector3 negative(Vector3 a, Vector3 result) {
		result.x = -a.x;
		result.y = -a.y;
		result.z = -a.z;
		return result;
	}

	public static Vector3 add(Vector3 a, Vector3 b, Vector3 result) {
		result.x = a.x + b.x;
		result.y = a.y + b.y;
		result.z = a.z + b.z;
		return result;
	}

	public static Vector3 add(Vector3 a, Vector3 b) {
		Vector3 result = new Vector3(a.x + b.x, a.y + b.y, a.z + b.z);
		return result;
	}

	public static Vector3 add(Vector3 a, float b, Vector3 result) {
		result.x = a.x + b;
		result.y = a.y + b;
		result.z = a.z + b;
		return result;
	}

	public static Vector3 subtract(Vector3 a, Vector3 b, Vector3 result) {
		result.x = a.x - b.x;
		result.y = a.y - b.y;
		result.z = a.z - b.z;
		return result;
	}

	public static Vector3 subtract(Vector3 a, float b, Vector3 result) {
		result.x = a.x - b;
		result.y = a.y - b;
		result.z = a.z - b;
		return result;
	}

	public static Vector3 subtract(Vector3 a, Vector3 b) {
		Vector3 result = new Vector3(a.x - b.x, a.y - b.y, a.z - b.z);
		return result;
	}

	public static Vector3 multiply(Vector3 a, Vector3 b, Vector3 result) {
		result.x = a.x * b.x;
		result.y = a.y * b.y;
		result.z = a.z * b.z;
		return result;
	}

	public static Vector3 multiply(Vector3 a, float b, Vector3 result) {
		result.x = a.x * b;
		result.y = a.y * b;
		result.z = a.z * b;
		return result;
	}

	public static Vector3 divide(Vector3 a, Vector3 b, Vector3 result) {
		result.x = a.x / b.x;
		result.y = a.y / b.y;
		result.z = a.z / b.z;
		return result;
	}

	public static Vector3 divide(Vector3 a, float b, Vector3 result) {
		result.x = a.x / b;
		result.y = a.y / b;
		result.z = a.z / b;
		return result;
	}

	/**
	 * Computes the cross product between two {@link Vector3} objects and and sets a new {@link Vector3} to the result.
	 * 
	 * @param v1
	 *            {@link Vector3} The first {@link Vector3} to cross.
	 * @param v2
	 *            {@link Vector3} The second {@link Vector3} to cross.
	 * @param result
	 *            {@link Vector3} The computed cross product.
	 */
	public static void crossProduct(Vector3 v1, Vector3 v2, Vector3 result) {
		result.x = v1.y * v2.z - v1.z * v2.y;
		result.y = v1.z * v2.x - v1.x * v2.z;
		result.z = v1.x * v2.y - v1.y * v2.x;
	}

	/**
	 * Computes the cross product between two {@link Vector3} objects and and sets a new {@link Vector3} to the result.
	 * 
	 * @param v1
	 *            {@link Vector3} The first {@link Vector3} to cross.
	 * @param v2
	 *            {@link Vector3} The second {@link Vector3} to cross.
	 * @return
	 *            {@link Vector3} The computed cross product.
	 */
	public static Vector3 crossProduct(Vector3 v1, Vector3 v2) {
		Vector3 result = new Vector3(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);

		return result;
	}
	
	/**
	 * Computes the cross product between two {@link Vector3} objects and and sets a new {@link Vector3} to the result.
	 * 
	 * @param v2
	 *            {@link Vector3} The second {@link Vector3} to cross.
	 * @return
	 *            {@link Vector3} The computed cross product.
	 */
	public Vector3 crossProduct(Vector3 v2) {
		Vector3 result = new Vector3(y * v2.z - z * v2.y, z * v2.x - x * v2.z, x * v2.y - y * v2.x);

		return result;
	}

	/**
	 * Return the dot product of this vector with the input vector
	 * 
	 * @param v1
	 * 		vector 1
	 * @param v2
	 * 		vector 2
	 * @return
	 * 	Float value representing the scalar of the dot product operation
	 */
	public static float dotProduct(Vector3 v1, Vector3 v2) {
		return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
	}
	
	/**
	 * Return the dot product of this vector with the input vector
	 * 
	 * @param v2
	 * 		vector 2
	 * @return
	 * 	Float value representing the scalar of the dot product operation
	 */
	public float dotProduct(Vector3 v2) {
		return x * v2.x + y * v2.y + z * v2.z;
	}

	

	/**
	 * linear interpolation tra a e b. Il risultato viene messo in result
	 * 
	 * @param a
	 * @param b
	 * @param fraction
	 * @param result
	 * @return
	 * 		risultato dell'interpolazione lineare
	 */
	public static Vector3 lerp(Vector3 a, Vector3 b, float fraction, Vector3 result) {
		subtract(b, a, result);
		multiply(result, fraction, result);
		add(result, a, result);
		return result;
	}
	
    /**
     * If you need to get the length of a vector then use this function.
     * 
     * @return The length of the vector
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

	/**
	 * Alternativa al new
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return nuovo vector con i valori impostati
	 */
	public static Vector3 set(float x, float y, float z) {
		return new Vector3(x, y, z);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.abubu.elio.util.Copy#copy()
	 */
	@Override
	public Vector3 copy() {
		return new Vector3(x, y, z);
	}

	/*
	 * Vector.randomDirection = function() { return Vector.fromAngles(Math.random() * Math.PI * 2, Math.asin(Math.random() * 2 - 1)); }; Vector.min = function(a, b) { return new Vector(Math.min(a.x, b.x), Math.min(a.y, b.y), Math.min(a.z,
	 * b.z)); }; Vector.max = function(a, b) { return new Vector(Math.max(a.x, b.x), Math.max(a.y, b.y), Math.max(a.z, b.z)); }; Vector.lerp = function(a, b, fraction) { return b.subtract(a).multiply(fraction).add(a); }; Vector.fromArray =
	 * function(a) { return new Vector(a[0], a[1], a[2]); };
	 */

}
