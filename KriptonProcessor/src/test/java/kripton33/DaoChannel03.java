/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package kripton33;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlUpdate;

@BindDao(Channel.class)
public interface DaoChannel03 {
	@BindSqlUpdate(where = "updateTime=${updateTimeA} and updateTime=${updateTimeB}")
	int update(long updateTime, long updateTimeA);
	
	/*
	 * @BindSelect(where="updateTime=${channel.updateTimeB}") List<Channel> select(Channel channel);
	 */

	/*
	 * @BindInsert(value="updateTimeB") long insertBean(Channel bean);
	 */

	/*
	 * @SQLUpdateBean(where = "id=:id") long updateContact(ChannelMessage bean, long id);
	 * 
	 * @SQLSelectBean(where = "") ChannelMessage selectBean();
	 */

}
