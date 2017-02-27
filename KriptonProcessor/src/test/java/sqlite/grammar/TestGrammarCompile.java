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
package sqlite.grammar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.CircularRelationshipException;
import com.abubusoft.kripton.processor.exceptions.InvalidBeanTypeException;
import com.abubusoft.kripton.processor.sqlite.SQLiteBaseListener;
import com.abubusoft.kripton.processor.sqlite.SQLiteLexer;
import com.abubusoft.kripton.processor.sqlite.SQLiteParser;
import com.abubusoft.kripton.processor.sqlite.SQLiteParser.Table_nameContext;

import sqlite.AbstractBindSQLiteProcessorTest;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
@RunWith(JUnit4.class)
public class TestGrammarCompile extends AbstractBindSQLiteProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		

        // The list that will hold our function names.
        final List<String> functionNames = new ArrayList<String>();

        // The select-statement to be parsed.
        String sql = "SELECT log AS x FROM t1 \n" +
                "GROUP BY x                   \n" +
                "HAVING count(*) >= 4         \n" +
                "ORDER BY max(n) + 0          \n";

        // Create a lexer and parser for the input.
        SQLiteLexer lexer = new SQLiteLexer(new ANTLRInputStream(sql));
        SQLiteParser parser = new SQLiteParser(new CommonTokenStream(lexer));

        // Invoke the `select_stmt` production.
        ParseTree tree = parser.select_stmt();

        // Walk the `select_stmt` production and listen when the parser
        // enters the `expr` production.
        ParseTreeWalker.DEFAULT.walk(new SQLiteBaseListener(){

        	
            @Override
			public void enterTable_name(Table_nameContext ctx) {
				
				System.out.println("table "+ctx.getText());
			}

			@Override
			public void visitTerminal(TerminalNode node) {
				// TODO Auto-generated method stub
            	
				super.visitTerminal(node);
			}

			@Override
            public void enterExpr(SQLiteParser.ExprContext ctx) {
                // Check if the expression is a function call.
                if (ctx.function_name() != null) {
                    // Yes, it was a function call: add the name of the function
                    // to out list.
                    functionNames.add(ctx.function_name().getText());
                }
                              
            }
        }, tree);

        // Print the parsed functions.
        System.out.println("functionNames=" + functionNames);
	}
	
}
