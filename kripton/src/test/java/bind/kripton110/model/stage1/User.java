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

@BindType
public class User {

	public String about;

	public String address;

	public int age;

	public String balance;

	public String company;

	public String email;

	public String eyeColor;

	public String favoriteFruit;

	public List<Friend> friends;

	public String greeting;

	public String guid;

	@Bind("_id")
	public String id;

	public List<Image> images;

	public int index;

	public boolean isActive;

	public double latitude;

	public double longitude;

	public Name name;

	public String phone;

	@Bind("picture")
	public String pictureUrl;

	public List<Integer> range;

	public String registered;

	public List<String> tags;
}