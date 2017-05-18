/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.grammar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Bind_dynamic_sqlContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Column_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Result_columnContext;

public class SQLiteAnalyzer {

	protected static SQLiteAnalyzer instance;

	public static final SQLiteAnalyzer getInstance() {
		if (instance == null) {
			instance = new SQLiteAnalyzer();
		}

		return instance;
	}

	ParseTreeWalker walker = new ParseTreeWalker();

	private SQLiteAnalyzer() {

	}

	protected <L extends SQLiteBaseListener> void analyzeInternal(final String input, L listener) {		
		walker.walk(listener, prepareParser(input).value0);
	}
	
	public <L extends SQLiteBaseListener> void analyze(final String input, L listener) {		
		analyzeInternal(input,  listener);
	}
	
	protected Pair<ParserRuleContext, CommonTokenStream> prepareParser(final String sql) {
		SQLiteLexer lexer = new SQLiteLexer(CharStreams.fromString(sql));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SQLiteParser parser = new SQLiteParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new SQLiteBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				AssertKripton.assertTrue(false, "unespected char at pos %s of SQL '%s'", charPositionInLine, sql);
			}
		});

		ParserRuleContext context = parser.parse();		
		return new Pair<>(context, tokens);
	}
	
	/**
	 * Retrieve set of projected field.
	 * 
	 * @param sql
	 * @return
	 */
	public Set<String> analyzeProjectedField(String sql) {
		Set<String> result=new HashSet<String>();
		
		final One<Boolean> valid=new One<>();
		valid.value0=false;
		
		analyzeInternal(sql, new SQLiteBaseListener() {
			@Override
			public void enterResult_column(Result_columnContext ctx) {
				valid.value0=true;
				
				System.out.println(ctx.getChildCount());				
				System.out.println(ctx.getText());
			}
			
			@Override
			public void enterColumn_name(Column_nameContext ctx) {
				if (valid.value0) {
					//result.add(ctx.any_name())
				}
			}
			
			@Override
			public void exitResult_column(Result_columnContext ctx) {
				valid.value0=false;
			}
		});
		return result;
	}

	public String replaceBindParameters(String sql) {
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();

		SQLiteBaseListener listener = new SQLiteBaseListener() {
			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, ":dynamic"));
			}

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, ":bind"));
			}
		};
		Pair<ParserRuleContext, CommonTokenStream> parser = prepareParser(sql);
		walker.walk(listener, parser.value0 );

		TokenStreamRewriter rewriter = new TokenStreamRewriter(parser.value1);

		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}

		return rewriter.getText();

	}

	/**
	 * <p>
	 * Verify sql is syntactally correct, otherwise a KriptonProcessorException
	 * will be thrown.
	 * </p>
	 * 
	 * @param sql
	 */
	public void verify(final String sql) {
		this.analyzeInternal(sql, new SQLiteBaseListener());
	}

}
