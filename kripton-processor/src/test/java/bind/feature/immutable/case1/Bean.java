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
package bind.feature.immutable.case1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Bean {
	
	private SortedSet<String> sortableSet;

	public SortedSet<String> getSortableSet() {
		return sortableSet;
	}
	
	private Map<String, Long> map;
	
	private SortedMap<String, String> mapSorted;

	public Map<String, Long> getMap() {
		return map;
	}

	public SortedMap<String, String> getMapSorted() {
		return mapSorted;
	}

	private String name;
	
	private Date birthDate;
	
	private long age;
	
	private Long numberOfCars;

	public Date getBirthDate() {
		return birthDate;
	}

	public long getAge() {
		return age;
	}

	public Long getNumberOfCars() {
		return numberOfCars;
	}

	public String getName() {
		return name;
	}
	
	private List<Long> items;
	
	private ArrayList<String> itemsString;

	public ArrayList<String> getItemsString() {
		return itemsString;
	}

	public List<Long> getItems() {
		return items;
	}
	
	/**
	 * @param sortableSet
	 * @param map
	 * @param mapSorted
	 * @param name
	 * @param birthDate
	 * @param age
	 * @param numberOfCars
	 * @param items
	 * @param itemsString
	 * @param buffer
	 * @param bufferString
	 */
	public Bean(SortedSet<String> sortableSet, Map<String, Long> map, SortedMap<String, String> mapSorted, String name,
			Date birthDate, long age, Long numberOfCars, List<Long> items, ArrayList<String> itemsString, byte[] buffer,
			String[] bufferString) {
		super();
		this.sortableSet = sortableSet;
		this.map = map;
		this.mapSorted = mapSorted;
		this.name = name;
		this.birthDate = birthDate;
		this.age = age;
		this.numberOfCars = numberOfCars;
		this.items = items;
		this.itemsString = itemsString;
		this.buffer = buffer;
		this.bufferString = bufferString;
	}

	private byte[] buffer;
	
	private String[] bufferString;

	public String[] getBufferString() {
		return bufferString;
	}

	public byte[] getBuffer() {
		return buffer;
	}
	
	
}
