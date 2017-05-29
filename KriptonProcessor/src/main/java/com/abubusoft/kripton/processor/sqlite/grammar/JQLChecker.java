/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.grammar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
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
import com.abubusoft.kripton.processor.sqlite.grammar.JQLPlaceHolder.JQLPlaceHolderType;
import com.abubusoft.kripton.processor.sqlite.grammar.JQLProjection.ProjectionBuilder;
import com.abubusoft.kripton.processor.sqlite.grammar.JQLProjection.ProjectionType;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Bind_dynamic_sqlContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Column_nameContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Result_columnContext;
import com.abubusoft.kripton.processor.sqlite.grammar.SQLiteParser.Table_nameContext;

public class JQLChecker {

	protected static JQLChecker instance;

	public static final JQLChecker getInstance() {
		if (instance == null) {
			instance = new JQLChecker();
		}

		return instance;
	}

	ParseTreeWalker walker = new ParseTreeWalker();

	private JQLChecker() {

	}

	protected <L extends SQLiteBaseListener> void analyzeInternal(final JQL jql, L listener) {
		walker.walk(listener, prepareParser(jql).value0);
	}

	public <L extends SQLiteBaseListener> void analyze(final JQL jql, L listener) {
		analyzeInternal(jql, listener);
	}

	protected Pair<ParserRuleContext, CommonTokenStream> prepareParser(final JQL jql) {
		SQLiteLexer lexer = new SQLiteLexer(CharStreams.fromString(jql.value));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SQLiteParser parser = new SQLiteParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new SQLiteBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
				AssertKripton.assertTrue(false, "unespected char at pos %s of SQL '%s'", charPositionInLine, jql);
			}
		});

		ParserRuleContext context = parser.parse();
		return new Pair<>(context, tokens);
	}

	/**
	 * Retrieve set of projected field.
	 * 
	 * @param jql
	 * @return
	 */
	public Set<JQLProjection> extractProjections(JQL jql) {
		final Set<JQLProjection> result = new LinkedHashSet<JQLProjection>();

		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzeInternal(jql, new SQLiteBaseListener() {
			@Override
			public void enterResult_column(Result_columnContext ctx) {
				valid.value0 = true;				
				ProjectionBuilder builder = ProjectionBuilder.create();
				if (ctx.expr().column_name() != null) {					
					if (ctx.expr().table_name() != null) {
						builder.table(ctx.expr().table_name().getText());						
					}					
					builder.column(ctx.expr().column_name().getText());
					builder.type(ProjectionType.COLUMN);
				} else {					
					builder.type(ProjectionType.COMPLEX);				
					builder.expression(ctx.expr().getText());
				}
				
				if (ctx.column_alias() != null) {					
					builder.alias(ctx.column_alias().getText());
				} 		
				result.add(builder.build());

			}
	
			@Override
			public void exitResult_column(Result_columnContext ctx) {
				valid.value0 = false;
			}
		});
		return result;
	}

	/**
	 * Replace place holder with element passed by listener
	 * 
	 * @param jql
	 * @param listener
	 * @return
	 * 		string obtained by replacements
	 */
	public String replacePlaceHolders(JQL jql, final JSQLPlaceHolderReplacerListener listener) {
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();
				
		SQLiteBaseListener rewriterListener = new SQLiteBaseListener() {
									
			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				String value=listener.onDynamicSQL(ctx.bind_parameter_name().getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
			}

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				String value=listener.onParameter(ctx.bind_parameter_name().getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
			}
		};
		
//		this.analyzeInternal(jql, new SQLiteBaseListener() {
//			
//		});
		
		return replaceInternal(jql, replace, rewriterListener);
	}
	
	/**
	 * Replace place holder with element passed by listener
	 * 
	 * @param jql
	 * @param listener
	 * @return
	 * 		string obtained by replacements
	 */
	public String replaceInsert(JQL jql, final JSQLInsertReplacerListener listener) {
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();
				
		SQLiteBaseListener rewriterListener = new SQLiteBaseListener() {
			
			@Override
			public void enterTable_name(Table_nameContext ctx) {
				String value=listener.onTableName(ctx.getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
			}

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				String value=listener.onColumnValue(ctx.bind_parameter_name().getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
			}

			@Override
			public void enterColumn_name(Column_nameContext ctx) {
				String value=listener.onColumnName(ctx.getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
			}
						
		
		};
					
		return replaceInternal(jql, replace, rewriterListener);

	}

	private String replaceInternal(JQL jql, final List<Triple<Token, Token, String>> replace, SQLiteBaseListener rewriterListener) {
		Pair<ParserRuleContext, CommonTokenStream> parser = prepareParser(jql);
		walker.walk(rewriterListener, parser.value0);

		TokenStreamRewriter rewriter = new TokenStreamRewriter(parser.value1);

		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}

		return rewriter.getText();
	}
	
	public interface JSQLPlaceHolderReplacerListener {
		
		/**
		 * Event fired when we found a parameter: either it is a parameter, either it is dynamic sql
		 * 
		 * @param string
		 * @return
		 */
		String onParameter(String placeHolder);

		String onDynamicSQL(String placeHolder);		
	}
	
	public interface JSQLInsertReplacerListener {
		
		String onTableName(String tableName);
		
		String onColumnName(String columnName);

		String onColumnValue(String columnValue);		
	}
	
	public static class JQLParameterName {
		private JQLParameterName(String value) {
			values=value.split("\\.");
		}
		
		private String[] values;
		
		public String getValue() {
			return values[values.length-1];			
		}
		
		public static JQLParameterName parse(String value) {
			return new JQLParameterName(value);
		}
	}

	/**
	 * <p>
	 * Verify sql is syntactally correct, otherwise a KriptonProcessorException
	 * will be thrown.
	 * </p>
	 * 
	 * @param sql
	 */
	public void verify(final JQL jql) {
		this.analyzeInternal(jql, new SQLiteBaseListener());
	}

	/**
	 * Extract all bind parameters and dynamic part used in query.
	 * 
	 * @param jql
	 * @return
	 */
	public List<JQLPlaceHolder> extractPlaceHoldersAsList(JQL jql) {
		return extractPlaceHolders(jql, new ArrayList<JQLPlaceHolder>());
	}
	
	/**
	 * Extract all bind parameters and dynamic part used in query.
	 * 
	 * @param jql
	 * @return
	 */
	public Set<JQLPlaceHolder> extractPlaceHoldersAsSet(JQL jql) {
		return extractPlaceHolders(jql, new LinkedHashSet<JQLPlaceHolder>());
	}
	
	private <L extends Collection<JQLPlaceHolder>> L extractPlaceHolders(JQL jql, final L result) {		
		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzeInternal(jql, new SQLiteBaseListener() {
			
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				result.add(new JQLPlaceHolder(JQLPlaceHolderType.PARAMETER, ctx.bind_parameter_name().getText()));				
			}
			
			@Override
			public void enterBind_dynamic_sql(Bind_dynamic_sqlContext ctx) {
				result.add(new JQLPlaceHolder(JQLPlaceHolderType.DYNAMIC_SQL, ctx.bind_parameter_name().getText()));				
			}						
					
		});
		return result;
	}
		
}
