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
package com.abubusoft.kripton.binder.transform;

import javax.xml.namespace.QName;

import com.abubusoft.kripton.common.StringUtil;


public class QNameTransform implements Transform<QName> {

	@Override
	public QName read(String value) throws Exception {
		String[] parts = value.split(":");
		if (parts.length == 2) {
			return new QName(null, parts[1], parts[0]);
		} else if (parts.length == 1) {
			return new QName(parts[0]);
		}
		return null;
	}

	@Override
	public String write(QName value) throws Exception {
		String localName = value.getLocalPart();
		String prefix = value.getPrefix();
		if (!StringUtil.isEmpty(localName)) {
			if (!StringUtil.isEmpty(prefix)) {
				return prefix + ":" + localName;
			} else {
				return localName;
			}
		} else {
			return null;
		}
	}

}
