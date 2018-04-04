/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package commons.benchmark.model;

import java.util.List;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class User {

	@Bind(enabled=false)
	@BindColumn(columnType=ColumnType.PRIMARY_KEY)
	public long id;
	
    @Bind("_id")
    @BindColumn("uid")
    public String uid;

    public int index;

    public String guid;
  
    public boolean isActive;

    public String balance;

    @Bind("picture")
    @BindColumn("picture")
    public String pictureUrl;

    public int age;

    public Name name;

    public String company;

    public String email;

    public String address;

    public String about;

    public String registered;

    public double latitude;

    public double longitude;

    public List<String> tags;

    public List<Integer> range;

    public List<Friend> friends;

    public List<Image> images;

    public String greeting;

    public String favoriteFruit;

    public String eyeColor;

    public String phone;
}