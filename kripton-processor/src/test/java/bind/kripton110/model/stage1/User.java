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
package bind.kripton110.model.stage1;

import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@BindType
public class User {

	/** The about. */
	public String about;

	/** The address. */
	public String address;

	/** The age. */
	public int age;

	/** The balance. */
	public String balance;

	/** The company. */
	public String company;

	/** The email. */
	public String email;

	/** The eye color. */
	public String eyeColor;

	/** The favorite fruit. */
	public String favoriteFruit;

	/** The friends. */
	public List<Friend> friends;

	/** The greeting. */
	public String greeting;

	/** The guid. */
	public String guid;

	/** The id. */
	@Bind("_id")
	public String id;

	/** The images. */
	public List<Image> images;

	/** The index. */
	public int index;

	/** The is active. */
	public boolean isActive;

	/** The latitude. */
	public double latitude;

	/** The longitude. */
	public double longitude;

	/** The name. */
	public Name name;

	/** The phone. */
	public String phone;

	/** The picture url. */
	@Bind("picture")
	public String pictureUrl;

	/** The range. */
	public List<Integer> range;

	/** The registered. */
	public String registered;

	/** The tags. */
	public List<String> tags;
}