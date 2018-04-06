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

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
@BindType
public class User {

	/** The id. */
	@Bind(enabled=false)
	@BindColumn(columnType=ColumnType.PRIMARY_KEY)
	public long id;
	
    /** The uid. */
    @Bind("_id")
    @BindColumn("uid")
    public String uid;

    /** The index. */
    public int index;

    /** The guid. */
    public String guid;
  
    /** The is active. */
    public boolean isActive;

    /** The balance. */
    public String balance;

    /** The picture url. */
    @Bind("picture")
    @BindColumn("picture")
    public String pictureUrl;

    /** The age. */
    public int age;

    /** The name. */
    public Name name;

    /** The company. */
    public String company;

    /** The email. */
    public String email;

    /** The address. */
    public String address;

    /** The about. */
    public String about;

    /** The registered. */
    public String registered;

    /** The latitude. */
    public double latitude;

    /** The longitude. */
    public double longitude;

    /** The tags. */
    public List<String> tags;

    /** The range. */
    public List<Integer> range;

    /** The friends. */
    public List<Friend> friends;

    /** The images. */
    public List<Image> images;

    /** The greeting. */
    public String greeting;

    /** The favorite fruit. */
    public String favoriteFruit;

    /** The eye color. */
    public String eyeColor;

    /** The phone. */
    public String phone;
}