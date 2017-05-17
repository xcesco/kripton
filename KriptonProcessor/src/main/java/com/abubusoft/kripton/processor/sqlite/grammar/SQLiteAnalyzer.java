/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.grammar;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRErrorStrategy;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Bind_dynamic_sqlContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Bind_parameterContext;

public class SQLiteAnalyzer {

	protected static SQLiteAnalyzer instance;

	ParseTreeWalker walker = new ParseTreeWalker();

	private SQLiteAnalyzer() {

	}

	public static final SQLiteAnalyzer getInstance() {
		if (instance == null) {
			instance = new SQLiteAnalyzer();
		}

		return instance;
	}

	public <L extends SQLiteBaseListener> void analyze(String input, L listener) {
		try {
			SQLiteLexer lexer = new SQLiteLexer(CharStreams.fromString(input));

			CommonTokenStream tokens = new CommonTokenStream(lexer);
			SQLiteParser parser = new SQLiteParser(tokens);

			ParserRuleContext context = null;
			context = parser.parse();			

			walker.walk(listener, context);
		} catch (Throwable e) {
			throw new KriptonProcessorException(e.getMessage());			
		}
	}

	public String replaceBindParameters(String input) {		
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();

		SQLiteBaseListener listener = new SQLiteBaseListener() {
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

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SQLiteParser parser = new SQLiteParser(tokens);
		parser.setErrorHandler(new ANTLRErrorStrategy() {
			@Override
			public void reset(Parser recognizer) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Token recoverInline(Parser recognizer) throws RecognitionException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void recover(Parser recognizer, RecognitionException e) throws RecognitionException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void sync(Parser recognizer) throws RecognitionException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean inErrorRecoveryMode(Parser recognizer) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void reportMatch(Parser recognizer) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void reportError(Parser recognizer, RecognitionException e) {
				throw(e);
				
			}
		});
		ParserRuleContext context = parser.parse();

		walker.walk(listener, context);

		TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);

		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}

		return rewriter.getText();
		
	}

}
