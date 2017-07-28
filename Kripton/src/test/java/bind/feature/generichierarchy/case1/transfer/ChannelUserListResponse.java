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
/**
 * 
 */
package bind.feature.generichierarchy.case1.transfer;

import com.abubusoft.kripton.annotation.BindType;

import bind.feature.generichierarchy.case1.model.ChannelUser;

@BindType 
public class ChannelUserListResponse extends RestListEntity<ChannelUser> {

	private static final long serialVersionUID = -8852991429584656779L;

}
