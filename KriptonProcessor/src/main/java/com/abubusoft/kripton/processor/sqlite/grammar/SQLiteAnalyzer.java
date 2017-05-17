/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.grammar;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.grammar.SQLiteBaseListener;
import com.abubusoft.kripton.processor.grammar.SQLiteLexer;
import com.abubusoft.kripton.processor.grammar.SQLiteParser;
import com.abubusoft.kripton.processor.grammar.SQLiteParser.Bind_dynamic_sqlContext;
import com.abubusoft.kripton.processor.grammar.SQLiteParser.Bind_parameterContext;

public class SQLiteAnalyzer {
	
	protected static SQLiteAnalyzer instance;
	
	ParseTreeWalker walker = new ParseTreeWalker();	
	
	private SQLiteAnalyzer() {
		
	}
	
	public static final SQLiteAnalyzer getInstance() {
		if (instance==null)
		{
			instance=new SQLiteAnalyzer();
		}
		
		return instance;
	}
	
	public enum SQLType {
		SELECT,
		UPDATE,
		INSERT,
		DELETE
	}
	
	public <L extends SQLiteBaseListener> void analyze(SQLType type, String input, L listener) {		
		SQLiteLexer lexer = new SQLiteLexer(CharStreams.fromString(input));
		
		CommonTokenStream tokens=new CommonTokenStream(lexer);
		SQLiteParser parser=new SQLiteParser(tokens);
		
		ParserRuleContext context = null;
		
		switch(type) {
		case SELECT:
			context=parser.select_stmt();
			break;
		case INSERT:
			context=parser.insert_stmt();
			break;
		case DELETE:
			context=parser.delete_stmt();
			break;
		case UPDATE:
			context=parser.update_stmt();
			break;
		default:
			AssertKripton.fail(true, "Unsupported SQL type");
		}
		
					
		walker.walk(listener, context);	
	}
	
	public String replaceBindParameters(SQLType type, String input) {
		final List<Triple<Token, Token, String>> replace=new ArrayList<>();
		
		SQLiteBaseListener listener=new SQLiteBaseListener() {
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {			
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, ":bind"));				
			}
			
			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, ":dynamic"));		
			}
		};
		SQLiteLexer lexer = new SQLiteLexer(CharStreams.fromString(input));
		
		CommonTokenStream tokens=new CommonTokenStream(lexer);
		SQLiteParser parser=new SQLiteParser(tokens);		
						
		ParserRuleContext context = null;
		
		switch(type) {
		case SELECT:
			context=parser.select_stmt();
			break;
		case INSERT:
			context=parser.insert_stmt();
			break;
		case DELETE:
			context=parser.delete_stmt();
			break;
		case UPDATE:
			context=parser.update_stmt();
			break;
		default:
			AssertKripton.fail(true, "Unsupported SQL type");
		}
		
					
		walker.walk(listener, context);	
		
		TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
		
		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}
		
		return rewriter.getText();
	}

}
