package com.abubusoft.kripton.processor.sqlite.transform;

import javax.xml.namespace.QName;

import com.abubusoft.kripton.binder.transform.Transform;
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
