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
package bind.rss;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.abubusoft.kripton.BinderContext;
import com.abubusoft.kripton.KriptonBinder;

import bind.AbstractBaseTest;

/**
 * The Class TestRuntime70A.
 */
public class TestRuntimeRSS extends AbstractBaseTest {

	@Test
	public void testRuntimeVogellaRSS() throws Exception {
		File asset=new File("src/test/resources/bind/rss/vogella.rss");
		log(asset.getAbsolutePath());
		
		BinderContext context = KriptonBinder.xmlBind();
		RSSFeed result = context.parse(asset, RSSFeed.class);
		
		assertTrue(result.version.equals("2.0"));
		assertTrue(result.channels.size()>0);
		log(result.version+" "+result.channels.size());
	}
	
	@Test
	public void testRuntimeBBCRSS() throws Exception {
		File asset=new File("src/test/resources/bind/rss/bbc.rss");
		log(asset.getAbsolutePath());
		
		BinderContext context = KriptonBinder.xmlBind();
		RSSFeed result = context.parse(asset, RSSFeed.class);
		
		assertTrue(result.version.equals("2.0"));
		assertTrue(result.channels.size()>0);
		log(result.version+" "+result.channels.size());
	}


}
