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
package sqlite.contenturichecker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLPlaceHolder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker.JQLPlaceHolderReplacerListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLPlaceHolder.JQLPlaceHolderType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection.ProjectionType;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_dynamic_sqlContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.UriBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.UriChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.UriChecker.UriPlaceHolderReplacerListener;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.UriParser.Bind_parameter_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.UriPlaceHolder;

import base.BaseProcessorTest;

@RunWith(JUnit4.class)
public class TestUriChecker extends BaseProcessorTest {

	/**
	 * <p>
	 * OK
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testOK() throws Throwable {
		String input = "content://androi.authority/test/${ input }";

		UriChecker checker = UriChecker.getInstance();
		checker.analyze(input, new UriBaseListener() {

			@Override
			public void enterBind_parameter(com.abubusoft.kripton.processor.sqlite.grammars.uri.UriParser.Bind_parameterContext ctx) {
				TestUriChecker.this.log("parameter name [%s]", ctx.bind_parameter_name().getText());
			}

		});

		checker.extractPlaceHoldersAsList(input);
		log("replaced " + checker.replacePlaceHolders(input, new UriPlaceHolderReplacerListener() {

			@Override
			public String onParameterName(String name) {
				return "?";
			}
		}));
		log("aa");
	}

	@Test
	public void testProjectColumn() {
		String sql = "select count(*) as pippo ,fieldName1, composed.fieldName2 from table where id = ${bean.id}";
		JQL jql = new JQL();
		jql.value = sql;

		JQLChecker.getInstance().extractProjections(jql);
	}

	@Test
	public void testAuthorityWithVariableInPath() {
		String input = "content://androi.authority/test/${ input0 }/${input1   }";

		UriChecker checker = UriChecker.getInstance();

		// verify sql
		checker.verify(input);

		// check bind parameters
		{
			List<UriPlaceHolder> aspected = new ArrayList<>();
			aspected.add(new UriPlaceHolder("input0"));
			aspected.add(new UriPlaceHolder("input1"));
			List<UriPlaceHolder> actual = checker.extractPlaceHoldersAsList(input);

			checkCollectionExactly(actual, aspected);
		}

	}

	@Test(expected=KriptonProcessorException.class)
	public void testAuthorityWithVariableInPathError() {
		String input = "content://androi.authority/test/${ input0 }/";

		UriChecker checker = UriChecker.getInstance();

		// verify sql
		checker.verify(input);

		// check bind parameters
		{
			List<UriPlaceHolder> aspected = new ArrayList<>();
			aspected.add(new UriPlaceHolder("input0"));
			aspected.add(new UriPlaceHolder("input1"));
			List<UriPlaceHolder> actual = checker.extractPlaceHoldersAsList(input);

			checkCollectionExactly(actual, aspected);
		}
	}
	
	@Test
	public void testExtractPlaceHolderFromPath() {
		String input = "test/${input0}";

		UriChecker checker = UriChecker.getInstance();

		// verify sql
		checker.verifyPath(input);

		// check bind parameters
		{
			List<UriPlaceHolder> aspected = new ArrayList<>();
			aspected.add(new UriPlaceHolder("input0"));			
			List<UriPlaceHolder> actual = checker.extractPlaceHoldersFromPath(input);

			checkCollectionExactly(actual, aspected);
		}
	}

	@Test(expected=AssertionError.class)
	public void testAuthorityWithVariableInPathError2() {
		String input = "content://androi.authority/test/#";

		UriChecker checker = UriChecker.getInstance();

		// verify sql
		checker.verify(input);

		// check bind parameters
		{
			List<UriPlaceHolder> aspected = new ArrayList<>();
			aspected.add(new UriPlaceHolder("input0"));
			aspected.add(new UriPlaceHolder("input1"));
			List<UriPlaceHolder> actual = checker.extractPlaceHoldersAsList(input);

			checkCollectionExactly(actual, aspected);
		}
	}

	@Test
	public void testReplaceAuthorityWithVariable() {
		String input = "content://androi.authority/test/${field0}/${field1}";
		String expected = "content://androi.authority/test/#/*";

		UriChecker checker = UriChecker.getInstance();

		// verify sql
		checker.verify(input);

		// check bind parameters
		{

			String actual = checker.replacePlaceHolders(input, new UriPlaceHolderReplacerListener() {

				@Override
				public String onParameterName(String name) {
					if (name.endsWith("0")) {
						return "#";
					}
					;

					return "*";
				}
			});
			assertEquals(actual, expected);

			{
				List<UriPlaceHolder> aspectedHolders = new ArrayList<>();
				aspectedHolders.add(new UriPlaceHolder("field0"));
				aspectedHolders.add(new UriPlaceHolder("field1"));
				List<UriPlaceHolder> actualHolders = checker.extractPlaceHoldersAsList(input);
				
				checkCollectionExactly(aspectedHolders, actualHolders);
			}

			
		}
	}


}
