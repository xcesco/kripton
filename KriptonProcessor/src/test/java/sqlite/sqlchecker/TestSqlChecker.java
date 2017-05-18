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
package sqlite.sqlchecker;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.reflectionassert.ReflectionComparatorMode;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.sqlite.SqlAnalyzer;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteChecker;
import com.abubusoft.kripton.processor.sqlite.grammar.Parameter;
import com.abubusoft.kripton.processor.sqlite.grammar.Parameter.ParameterType;
import com.abubusoft.kripton.processor.sqlite.grammar.Projection;
import com.abubusoft.kripton.processor.sqlite.grammar.Projection.ProjectionType;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteLexer;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteListener;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Bind_dynamic_sqlContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Bind_parameter_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.ErrorContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Error_messageContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.ParseContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Raise_functionContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Update_stmtContext;
import com.google.common.collect.Sets;

import base.BaseProcessorTest;

@RunWith(JUnit4.class)
public class TestSqlChecker extends BaseProcessorTest {

	/**
	 * <p>
	 * OK
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testOK() throws Throwable {
		String sql = "SELECT id, action, number, countryCode, contactName, contactId FROM phone_number WHERE number = ${bean.number} and number = ${bean.number} and #{dynamicWhere} and #{dynamicWhere}";

		SQLiteChecker.getInstance().analyze(sql, new SQLiteBaseListener() {

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				TestSqlChecker.this.log("xx parameter name %s", ctx.bind_parameter_name().getText());
			}

			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				TestSqlChecker.this.log("xx dynamic %s", ctx.bind_parameter_name().getText());
			}

		});

		log(SQLiteChecker.getInstance().replaceBindParameters(sql));

		log("aa");
	}

	@Test
	public void testProjectColumn() {
		String sql = "select count(*) as pippo ,fieldName1, composed.fieldName2 from table where id = ${bean.id}";
		SQLiteChecker.getInstance().extractProjectedFields(sql);
	}

	@Test
	public void testDelet1() {
		String sql = "DELETE channel WHERE ownerUid=${field1} and ownerUid=${bean.field2} and ownerUid=${bean.field3} and ownerUid=${field5}";

		SQLiteChecker checker = SQLiteChecker.getInstance();

		// verify sql
		checker.verify(sql);
	
		// check bind parameters
		{
			List<Parameter> aspected = new ArrayList<>();
			aspected.add(new Parameter(ParameterType.PARAMETER_SIMPLE, "field1"));
			aspected.add(new Parameter(ParameterType.PARAMETER_INNER_FIELD, "bean.field2"));
			aspected.add(new Parameter(ParameterType.PARAMETER_INNER_FIELD, "bean.field3"));
			aspected.add(new Parameter(ParameterType.PARAMETER_SIMPLE, "field5"));						
			List<Parameter> actual = checker.extractBindParameters(sql);
			
			checkCollectionExactly(actual, aspected);
		}

	}

	/**
	 * extract projections from a select
	 */
	@Test
	public void testSelect01() {
		// String sql="SELECT count(*) FROM channel WHERE
		// updateTime=${bean.updateTime}";
		String sql = "SELECT count(*) as alias1, field2, field3 as alias3, table1.field3 as alias3, table2.field4 as alias4 FROM channel WHERE updateTime=${ bean.field1 } and field=${ field2  } and #{dynamicWhere1}";

		String logSql = "SELECT count(*) FROM channel WHERE updateTime='?'";
		String usedSql = "SELECT count(*) FROM channel WHERE updateTime=${bean.updateTime}";

		SQLiteChecker checker = SQLiteChecker.getInstance();

		// verify sql
		checker.verify(sql);

		// check projections
		Set<Projection> projections = checker.extractProjectedFields(sql);
		{
			LinkedHashSet<Projection> aspected = new LinkedHashSet<>();
			aspected.add(Projection.ProjectionBuilder.create().type(ProjectionType.COMPLEX).expression("count(*)").alias("alias1").build());
			aspected.add(Projection.ProjectionBuilder.create().type(ProjectionType.COLUMN).column("field2").build());
			aspected.add(Projection.ProjectionBuilder.create().type(ProjectionType.COLUMN).column("field3").alias("alias3").build());
			aspected.add(Projection.ProjectionBuilder.create().type(ProjectionType.COLUMN).table("table1").column("field3").alias("alias3").build());
			aspected.add(Projection.ProjectionBuilder.create().type(ProjectionType.COLUMN).table("table2").column("field4").alias("alias4").build());
			checkCollectionExactly(projections, aspected);
		}

		// check bind parameters
		{
			List<Parameter> aspected = new ArrayList<>();
			aspected.add(new Parameter(ParameterType.PARAMETER_INNER_FIELD, "bean.field1"));
			aspected.add(new Parameter(ParameterType.PARAMETER_SIMPLE, "field2"));
			aspected.add(new Parameter(ParameterType.DYNAMIC_SQL_SIMPLE, "dynamicWhere1"));
			List<Parameter> actual = checker.extractBindParameters(sql);
			
			checkCollectionExactly(actual, aspected);
		}

	}
	
	
	@Test
	public void testInsert01() {
		String sql = "INSERT INTO channel (uid, owner_uid, update_time, name, field) VALUES (${bean.field1}, ${bean.field2}, ${bean.field3}, ${bean.field4}, ${field5})";

		SQLiteChecker checker = SQLiteChecker.getInstance();

		// verify sql
		checker.verify(sql);
	
		// check bind parameters
		{
			List<Parameter> aspected = new ArrayList<>();
			aspected.add(new Parameter(ParameterType.PARAMETER_INNER_FIELD, "bean.field1"));
			aspected.add(new Parameter(ParameterType.PARAMETER_INNER_FIELD, "bean.field2"));
			aspected.add(new Parameter(ParameterType.PARAMETER_INNER_FIELD, "bean.field3"));
			aspected.add(new Parameter(ParameterType.PARAMETER_INNER_FIELD, "bean.field4"));			
			aspected.add(new Parameter(ParameterType.PARAMETER_SIMPLE, "field5"));
			List<Parameter> actual = checker.extractBindParameters(sql);
			
			checkCollectionExactly(actual, aspected);
		}

	}
	
	@Test
	public void testUpdate01() {
		String sql = "UPDATE channel SET uid=${ bean.field1}, owner_uid=${bean.field2}, update_time=${bean.field3}, name=${field4} WHERE id=${bean.field1}";

		SQLiteChecker checker = SQLiteChecker.getInstance();

		// verify sql
		checker.verify(sql);
	
		// check bind parameters
		{
			List<Parameter> aspected = new ArrayList<>();
			aspected.add(new Parameter(ParameterType.PARAMETER_INNER_FIELD, "bean.field1"));
			aspected.add(new Parameter(ParameterType.PARAMETER_INNER_FIELD, "bean.field2"));
			aspected.add(new Parameter(ParameterType.PARAMETER_INNER_FIELD, "bean.field3"));
			aspected.add(new Parameter(ParameterType.PARAMETER_SIMPLE, "field4"));			
			aspected.add(new Parameter(ParameterType.PARAMETER_INNER_FIELD, "bean.field1"));
			List<Parameter> actual = checker.extractBindParameters(sql);
			
			checkCollectionExactly(actual, aspected);
		}

	}
	
	

}
