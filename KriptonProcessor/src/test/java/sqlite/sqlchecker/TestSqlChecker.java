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

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.sqlite.SqlAnalyzer;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteAnalyzer;
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

import base.BaseProcessorTest;


@RunWith(JUnit4.class)
public class TestSqlChecker extends BaseProcessorTest {

	/**
	 * <p>OK</p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testOK() throws Throwable {		
		String sql="SELECT id, action, number, countryCode, contactName, contactId FROM phone_number WHERE number = ${bean.number} and number = ${bean.number} and #{dynamicWhere} and #{dynamicWhere}";
		
				
		SQLiteAnalyzer.getInstance().analyze(sql, new SQLiteBaseListener() {
									
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {								
				TestSqlChecker.this.log("xx parameter name %s", ctx.bind_parameter_name().getText());								
			}
			
			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				TestSqlChecker.this.log("xx dynamic %s", ctx.bind_parameter_name().getText());
			}
					
		});
		
		log(SQLiteAnalyzer.getInstance().replaceBindParameters(sql));
		
		
		log("aa");
	}
	
	@Test
	public void testProjectColumn()
	{
		String sql="select count(*) as pippo ,fieldName1, composed.fieldName2 from table where id = ${bean.id}";
		SQLiteAnalyzer.getInstance().analyzeProjectedField(sql);
	}
	
	/**
	 * <p>OK</p>
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testUPDATE() throws Throwable {		
		String sql="UPDATE message SET ownerType=${ownerType} WHERE  id = ${id}";
		SQLiteLexer lexer = new SQLiteLexer(CharStreams.fromString(sql));
		
		CommonTokenStream tokens=new CommonTokenStream(lexer);
		SQLiteParser parser=new SQLiteParser(tokens);
		Update_stmtContext context = parser.update_stmt();
		
		ParseTreeWalker walker = new ParseTreeWalker();
		SQLiteListener listener=new SQLiteBaseListener() {

			@Override
			public void visitTerminal(TerminalNode node) {				
				//super.visitTerminal(node);
				
				log("TERMONAT");
			}

			@Override
			public void visitErrorNode(ErrorNode node) {
				//super.visitErrorNode(node);
				
				log("ERRORE");
			}
			
		};
		
		walker.walk(listener, context);
		
		
		log("aa");
	}
	
	


}
