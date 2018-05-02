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
package com.abubusoft.kripton.android;

/**
 * <P>
 * Allows to specify how a BindAsyncTask works with the data source for which it
 * is generated. In a generated async task a data source can be:
 * </p>
 * 
 * <ul>
 * <li><strong>opened in read mode</strong>: data source is opened in read mode
 * and only SELECT operation are admitted. Async task will manage open/close
 * connetion operations.</li>
 * <li><strong>opened in read/write mode</strong>: every operation on data
 * source are admitted. Async task will manage open/close connetion
 * operations.</li>
 * <li><strong>unmanaged mode</strong>: async task will NOT manage open/close
 * connetion operations. You can to manage it as you want</li>
 * </ul>
 * 
 * @author xcesco
 *
 */
public enum BindAsyncTaskType {
	/**
	 * data source is opened in read mode and only SELECT operation are
	 * admitted. Async task will manage open/close connetion operations.
	 */
	READ,

	/**
	 * every operation on data source are admitted. Async task will manage
	 * open/close connetion operations.
	 */
	READ_WRITE,

	/**
	 * async task will NOT manage open/close connetion operations. You can
	 * manage it as you want
	 */
	UNMANAGE
}