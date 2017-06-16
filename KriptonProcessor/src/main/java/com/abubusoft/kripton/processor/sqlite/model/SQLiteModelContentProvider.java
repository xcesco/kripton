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
package com.abubusoft.kripton.processor.sqlite.model;

/**
 * <p>Representation of a content provider</p>  
 *
 */
public class SQLiteModelContentProvider {
	
//	final static Converter<String, String> convert = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);
//	
//	public enum SupportedOperation {
//		INSERT,
//		UPDATE,
//		DELETE,
//		SELECT
//	}
// 
//	public List<Triple<String, SQLDaoDefinition, Map<SupportedOperation, SQLiteModelMethod>>> uris=new ArrayList<>();
//	
	//protected String name;

	public String authority;
	
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	private Triple<String, SQLDaoDefinition, Map<SupportedOperation, SQLiteModelMethod>> findOrCreateByUri(String uri,  SQLiteModelMethod daoMethod, SupportedOperation operation) {
//		ModelAnnotation annotation=daoMethod.getAnnotation(BindContentProviderEntry.class);
//		
//		if (annotation!=null)
//		{
//			String path=annotation.getAttribute(AnnotationAttributeType.PATH);
//			if (StringUtils.hasText(path))
//			{
//				uri += "/"+path;
//			}
//			
//			uri=uri.replace("//", "/");
//		}
//		
//		Triple<String, SQLDaoDefinition, Map<SupportedOperation, SQLiteModelMethod>> result=null;
//		for (Triple<String, SQLDaoDefinition, Map<SupportedOperation, SQLiteModelMethod>> item: uris) {
//			if (item.value0.equals(uri)) {
//				result=item;
//				break;
//			}
//		}
//		
//		if (result==null) {
//			result=new Triple<String, SQLDaoDefinition, Map<SupportedOperation, SQLiteModelMethod>>();
//			result.value0=uri;
//			result.value1=daoMethod.getParent();
//			result.value2=new HashMap<>();
//			
//			uris.add(result);
//		}		
//		
//		//TODO add test for: a triple must be managed by unique dao
//		AssertKripton.assertTrue(result.value1.equals(daoMethod.getParent()), "URI '%s' used in content provider '%s' is defined in both DAOs %s and %s. It can be used only in one of them. ", uri, daoMethod.getParent().getParent().contentProvider.getName(), daoMethod.getParent().getName());
//		//TODO add test for: in a triple no operation can be override by URL
//		if (result.value2.containsKey(operation)) {
//			SQLiteModelMethod presentMethod=result.value2.get(operation);
//			AssertKripton.fail(result.value2.containsKey(operation), "URI '%s' used in content provider '%s' is defined both in %s#%s and %s#%s methods. It can be used only in one of them. ", uri, daoMethod.getParent().getParent().contentProvider.getName(), presentMethod.getParent().getName(), presentMethod.getName(), daoMethod.getParent().getName(), daoMethod.getName());
//		} else {
//			result.value2.put(operation, daoMethod);
//		}
//		
//		
//		return result;
//	}
//	
//	private int findIndexByUri(String uri) {
//		int i=0;		
//		for (Triple<String, SQLDaoDefinition, Map<SupportedOperation, SQLiteModelMethod>> item: uris) {
//			if (item.value0.equals(uri)) {
//				return i; 				
//			}
//			i++;
//		}
//		
//		return -1;
//	}
//	
//	private Triple<String, SQLDaoDefinition, Map<SupportedOperation, SQLiteModelMethod>> findByUri(String uri) {
//		for (Triple<String, SQLDaoDefinition, Map<SupportedOperation, SQLiteModelMethod>> item: uris) {
//			if (item.value0.equals(uri)) {
//				return item; 				
//			}
//		}
//		
//		return null;
//	}
//	
//	public Triple<String, SQLDaoDefinition, Map<SupportedOperation, SQLiteModelMethod>> addOperation(String uri, SQLiteModelMethod daoMethod, SupportedOperation operation) {
//		Triple<String, SQLDaoDefinition, Map<SupportedOperation, SQLiteModelMethod>> entity = findOrCreateByUri(uri, daoMethod, operation);
//		
//		return entity;
//	}
//		
//	public String getConstant(String uri)
//	{
//		return "URI_"+convert.convert(findByUri(uri).value1.contentProviderTypeName)+"_"+findIndexByUri(uri);
//	}
//	
//	public String getName() {
//		return name;
//	}

	public String getUri() {
		return "content://"+authority;
	}
}
