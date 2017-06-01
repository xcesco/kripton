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

	public static void crossProduct(Vector3 v1, Vector3 v2, Vector3 result) {
		result.x = v1.y * v2.z - v1.z * v2.y;
		result.y = v1.z * v2.x - v1.x * v2.z;
		result.z = v1.x * v2.y - v1.y * v2.x;
	}

	public static Vector3 crossProduct(Vector3 v1, Vector3 v2) {
		Vector3 result = new Vector3(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);

		return result;
	}

	public Vector3 crossProduct(Vector3 v2) {
		Vector3 result = new Vector3(y * v2.z - z * v2.y, z * v2.x - x * v2.z, x * v2.y - y * v2.x);

		return result;
	}

	public static float dotProduct(Vector3 v1, Vector3 v2) {
		return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
	}

	public float dotProduct(Vector3 v2) {
		return x * v2.x + y * v2.y + z * v2.z;
	}

	public static Vector3 lerp(Vector3 a, Vector3 b, float fraction, Vector3 result) {
		subtract(b, a, result);
		multiply(result, fraction, result);
		add(result, a, result);
		return result;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public static Vector3 set(float x, float y, float z) {
		return new Vector3(x, y, z);
	}

	@Override
	public Vector3 copy() {
		return new Vector3(x, y, z);
	}

}
