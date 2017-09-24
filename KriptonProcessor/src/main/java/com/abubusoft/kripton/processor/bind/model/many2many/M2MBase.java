package com.abubusoft.kripton.processor.bind.model.many2many;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class M2MBase {

	public List<Annotation> annotation=new ArrayList<Annotation>();

	public List<Annotation> getAnnotation() {
		return annotation;
	}	
}
