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
package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Given a data-source definition, this annotation allows generating the
 * associated content provider. Basically, this annotation simply specifies the
 * authority to put into `manifest.xml` file.
 * 
 * <h3>Attributes</h3>
 * 
 * <ul>
 * <li><strong>authority</strong>: define the AUTHORITY for the content
 * provider.</li>
 * </ul>
 * 
 * <h3>Usage Given an example datasource definition</h3>
 * 
 * <pre>
 * &#64;BindContentProvider(authority = "com.abubusoft.contentprovidersample.provider")
 * &#64;BindDataSourceOptions(updateTasks = { @BindDataSourceUpdateTask(version = 1, task = SampleUpdate02.class) }, populator = SamplePopulator.class)
 * &#64;BindDataSource(daoSet = { CheeseDao.class }, fileName = "sample.db", version = 1)
 * public interface SampleDataSource {
 * 
 * }
 * </pre>
 * 
 * <p>
 * Kripton generates the data-source implementation and the associated content
 * provider. For the `SampleDataSource`, the content provider generated has name
 * <code>BindSampleContentProvider</code>. Into manifest.xml you need to define
 * the content provider:
 * </p>
 * 
 * <pre>
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.MyApplication"&gt;

   &lt;application
      android:allowBackup="true"
      android:icon="@mipmap/ic_launcher"
      android:label="@string/app_name"
      android:supportsRtl="true"
      android:theme="@style/AppTheme"&gt;
         &lt;activity android:name=".MainActivity"&gt;
            &lt;intent-filter&gt;
               &lt;action android:name="android.intent.action.MAIN" /&gt;
               &lt;category android:name="android.intent.category.LAUNCHER" /&gt;
            &lt;/intent-filter&gt;
         &lt;/activity&gt;
        
      &lt;provider android:name="PersonProvider"
         android:authorities=
"com.example.MyApplication.BindSampleContentProvider"/&gt;
   &lt;/application&gt;
&lt;/manifest&gt;
 * </pre>
 * 
 * <p>
 * The generated content provider is generated with Javadoc, feel free to
 * inspect generated source Javadoc to see managed URL and many other features
 * of the generated content provider.
 * </p>
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @see <a href=
 *      "https://developer.android.com/guide/topics/providers/content-provider-basics.html">content-provider-basics</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BindContentProvider {

	/**
	 * Define the AUTHORITY for content provider.
	 * 
	 * @return content provider authority
	 */
	public String authority();

}
