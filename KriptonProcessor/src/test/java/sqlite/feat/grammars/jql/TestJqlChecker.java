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
package sqlite.feat.grammars.jql;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.processor.core.Finder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQL.JQLDynamicStatementType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLPlaceHolder;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLPlaceHolder.JQLPlaceHolderType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection.ProjectionType;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplaceVariableStatementListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLReplacerListenerImpl;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlBaseListener;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_dynamic_sqlContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammars.jsql.JqlParser.Column_nameContext;
import com.abubusoft.kripton.processor.sqlite.model.SQLEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;

import base.BaseProcessorTest;

@RunWith(JUnit4.class)
public class TestJqlChecker extends BaseProcessorTest {

	/**
	 * <p>
	 * OK
	 * </p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testOK() throws Throwable {				
		String sql = "SELECT id, action, number, countryCode, contactName, contactId FROM phone_number WHERE number = ${bean.number} and number like ${bean.number} || '%' and #{"+JQLDynamicStatementType.DYNAMIC_WHERE+"}";
		log(sql);
		JQL jql=new JQL();
		jql.value=sql;

		JQLChecker jsqChecker = JQLChecker.getInstance();
		jsqChecker.analyze(jql, new JqlBaseListener() {

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				TestJqlChecker.this.log("xx parameter name %s", ctx.bind_parameter_name().getText());
			}

			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				TestJqlChecker.this.log("xx dynamic %s", ctx.bind_parameter_name().getText());
			}
			
			@Override
			public void enterColumn_name(Column_nameContext ctx) {					
				super.enterColumn_name(ctx);
				log("column "+ctx.getText());
			}

		});

		jsqChecker.extractPlaceHoldersAsList(jql.value);
		log("replaced " + jsqChecker.replace(jql, new JQLReplacerListenerImpl() {

			@Override
			public String onDynamicSQL(JQLDynamicStatementType dynamicStatement) {
				return String.format("\"+%s+\"", dynamicStatement);
			}

			@Override
			public String onBindParameter(String bindParameterName) {
				return "?";
			}
		}));

		log("aa");
	}

	@Test
	public void testProjectColumn() {
		//SQLEntity entity=new SQLEntity(elementUtils, model, element);
		
		String sql = "select count(*) as pippo ,fieldName1, composed.fieldName2 from table where id = ${bean.id}";
		JQL jql=new JQL();
		jql.value=sql;

		JQLChecker.getInstance().extractProjections(jql.value, new Finder<SQLProperty>() {
			
			@Override
			public String getSimpleName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<SQLProperty> getCollection() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public SQLProperty findByName(String name) {
				// TODO Auto-generated method stub
				return null;
			}
		} );
	}

	@Test
	public void testDelet1() {
		String sql = "DELETE FROM channel WHERE ownerUid=${field1} and ownerUid=${bean.field2} and ownerUid=${bean.field3} and ownerUid=${field5}";
		JQL jql=new JQL();
		jql.value=sql;
		
		
		JQLChecker checker = JQLChecker.getInstance();

		// verify sql
		checker.verify(jql);

		// check bind parameters
		{
			List<JQLPlaceHolder> aspected = new ArrayList<>();
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "field1"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "bean.field2"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "bean.field3"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "field5"));
			List<JQLPlaceHolder> actual = checker.extractPlaceHoldersAsList(jql.value);

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
		String sql = "SELECT count(*) as alias1, field2, field3 as alias3, table1.field3 as alias3, table2.field4 as alias4 FROM channel WHERE updateTime=${ bean.field1 } and field=${ field2  } and #{"+JQLDynamicStatementType.DYNAMIC_WHERE+"}";

		String logSql = "SELECT count(*) as alias1, field2, field3 as alias3, table1.field3 as alias3, table2.field4 as alias4 FROM channel WHERE updateTime=? and field=? and \"+DYNAMIC_WHERE+\"";
		// String usedSql = "SELECT count(*) FROM channel WHERE
		// updateTime=${bean.updateTime}";
		JQL jql=new JQL();
		jql.value=sql;


		JQLChecker checker = JQLChecker.getInstance();

		// verify sql
		checker.verify(jql);

		Finder<SQLProperty> entityMock=new Finder<SQLProperty>() {
			
			@Override
			public String getSimpleName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<SQLProperty> getCollection() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public SQLProperty findByName(String name) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		// check projections
		Set<JQLProjection> projections = checker.extractProjections(jql.value, entityMock);
		{
			LinkedHashSet<JQLProjection> aspected = new LinkedHashSet<>();
			aspected.add(JQLProjection.ProjectionBuilder.create().type(ProjectionType.COMPLEX).expression("count(*)")
					.alias("alias1").build());
			aspected.add(JQLProjection.ProjectionBuilder.create().type(ProjectionType.COLUMN).column("field2").build());
			aspected.add(JQLProjection.ProjectionBuilder.create().type(ProjectionType.COLUMN).column("field3")
					.alias("alias3").build());
			aspected.add(JQLProjection.ProjectionBuilder.create().type(ProjectionType.COLUMN).table("table1")
					.column("field3").alias("alias3").build());
			aspected.add(JQLProjection.ProjectionBuilder.create().type(ProjectionType.COLUMN).table("table2")
					.column("field4").alias("alias4").build());
			checkCollectionExactly(projections, aspected);
		}

		// check bind parameters
		{
			List<JQLPlaceHolder> aspected = new ArrayList<>();
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "bean.field1"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "field2"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.DYNAMIC_SQL, JQLDynamicStatementType.DYNAMIC_WHERE.toString()));
			List<JQLPlaceHolder> actual = checker.extractPlaceHoldersAsList(jql.value);

			checkCollectionExactly(actual, aspected);
		}

		// prepare for log
		String sqlLogResult = checker.replace(jql, new JQLReplacerListenerImpl() {

			@Override
			public String onDynamicSQL(JQLDynamicStatementType dynamicStatement) {
				return String.format("\"+%s+\"", dynamicStatement);
			}

			@Override
			public String onBindParameter(String bindParameterName) {
				return "?";
			}
		});
		assertEquals("sql for log generation failed", logSql, sqlLogResult);
	}

	@Test
	public void testInsert01() {
		String sql = "INSERT INTO channel (uid, owner_uid, update_time, name, field) VALUES (${bean.field1}, ${bean.field2}, ${bean.field3}, ${bean.field4}, ${field5})";
		JQL jql=new JQL();
		jql.value=sql;

		JQLChecker checker = JQLChecker.getInstance();

		// verify sql
		checker.verify(jql);

		// check bind parameters
		{
			List<JQLPlaceHolder> aspected = new ArrayList<>();
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "bean.field1"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "bean.field2"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "bean.field3"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "bean.field4"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "field5"));
			List<JQLPlaceHolder> actual = checker.extractPlaceHoldersAsList(jql.value);

			checkCollectionExactly(actual, aspected);
		}

	}

	@Test
	public void testUpdate01() {
		String sql = "UPDATE channel SET uid=${ bean.field1}, owner_uid=${bean.field2}, update_time=${bean.field3}, name=${field4} WHERE id=${bean.field1}";
		String sqlForLog = "UPDATE channel SET uid=?, owner_uid=?, update_time=?, name=? WHERE id=?";
		
		JQL jql=new JQL();
		jql.value=sql;


		JQLChecker checker = JQLChecker.getInstance();

		// verify sql
		checker.verify(jql);

		// check bind parameters
		{
			List<JQLPlaceHolder> aspected = new ArrayList<>();
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "bean.field1"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "bean.field2"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "bean.field3"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "field4"));
			aspected.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, "bean.field1"));
			List<JQLPlaceHolder> actual = checker.extractPlaceHoldersAsList(jql.value);

			checkCollectionExactly(actual, aspected);
		}

		// prepare for log
		String sqlLogResult = checker.replace(jql, new JQLReplacerListenerImpl() {

			@Override
			public String onDynamicSQL(JQLDynamicStatementType dynamicStatement) {
				return String.format("\"+%s+\"", dynamicStatement);
			}

			@Override
			public String onBindParameter(String bindParameterName) {
				return "?";
			}
		});
		assertEquals("sql for log generation failed", sqlForLog, sqlLogResult);

	}
	
	
	@Test
	public void testBindParametersFromVariableStatements() {
		final One<String> where=new One<>();
		final One<String> orderBy=new One<>();
		final One<String> limit=new One<>();
		final One<String> offset=new One<>();
		final One<String> group=new One<>();
		final One<String> having=new One<>();
		
		String sql = "SELECT id, parentId, birthCity, birthDay, value, name, surname FROM Person WHERE name like ${nameTemp} || '%' GROUP BY id HAVING id=2 ORDER BY id,  #{"+JQLDynamicStatementType.DYNAMIC_ORDER_BY+"} LIMIT #{"+JQLDynamicStatementType.DYNAMIC_PAGE_SIZE+"} OFFSET #{"+JQLDynamicStatementType.DYNAMIC_PAGE_OFFSET+"}";		
		
		JQL jql=new JQL();
		jql.value=sql;


		JQLChecker checker = JQLChecker.getInstance();

		// verify sql
		checker.verify(jql);

		
		String finalSql=checker.replaceVariableStatements(jql.value, new JQLReplaceVariableStatementListenerImpl() {

			@Override
			public String onWhere(String whereStatement) {
				where.value0=whereStatement;
				return "";
			}

			@Override
			public String onOrderBy(String orderByStatement) {
				orderBy.value0=orderByStatement;
				return "";
			}

			@Override
			public String onLimit(String statement) {
				limit.value0=statement;
				return "";
			}

			@Override
			public String onOffset(String statement) {
				offset.value0=statement;
				return "";
			}

			@Override
			public String onGroup(String statement) {
				group.value0=statement;
				return "";
			}

			@Override
			public String onHaving(String statement) {
				having.value0=statement;
				return "";
			}

			@Override
			public String onProjectedColumns(String statement) {
				return null;
			}
			
		});
		
		log(where.value0);
		log(orderBy.value0);
		log(group.value0);
		log(having.value0);
		log(offset.value0);
		log(limit.value0);
		
		log(finalSql);
		//assertEquals(where, " id = ${dummy} and a=${dummy2}");
		
		{
			List<JQLPlaceHolder> list = checker.extractFromVariableStatement(where.value0);
			
			for (JQLPlaceHolder item : list) {
				log(item.value);
			}
			
			String replacedSql=checker.replaceFromVariableStatement(where.value0, new JQLReplacerListenerImpl() {
				
				@Override
				public String onDynamicSQL(JQLDynamicStatementType dynamicStatement) {
					return "*";
				}

				@Override
				public String onBindParameter(String bindParameterName) {
					return "?";
				}
				
		
			});
			
			log(replacedSql);
		}
	}
	
	

}
