/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.grammars.uri;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.UriParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.UriParser.Bind_parameter_numberContext;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.UriParser.Bind_parameter_textContext;

public class UriChecker {

	protected static UriChecker instance;

	public static final UriChecker getInstance() {
		if (instance == null) {
			instance = new UriChecker();
		}

		return instance;
	}

	ParseTreeWalker walker = new ParseTreeWalker();

	private UriChecker() {

	}

	protected <L extends UriBaseListener> void analyzeInternal(final String input, L listener) {
		walker.walk(listener, prepareUri(input).value0);
	}
	
	protected <L extends UriBaseListener> void analyzePathInternal(final String input, L listener) {
		walker.walk(listener, preparePath(input).value0);
	}

	public <L extends UriBaseListener> void analyze(final String input, L listener) {
		analyzeInternal(input, listener);
	}

	protected Pair<ParserRuleContext, CommonTokenStream> prepareUri(final String input) {
		UriLexer lexer = new UriLexer(CharStreams.fromString(input));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		UriParser parser = new UriParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new UriBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
				AssertKripton.assertTrue(false, "unespected char at pos %s of SQL '%s'", charPositionInLine, input);
			}
		});

		ParserRuleContext context = parser.uri();
		return new Pair<>(context, tokens);
	}
	
	protected Pair<ParserRuleContext, CommonTokenStream> preparePath(final String input) {
		UriLexer lexer = new UriLexer(CharStreams.fromString(input));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		UriParser parser = new UriParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new UriBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
				AssertKripton.assertTrue(false, "unespected char at pos %s of SQL '%s'", charPositionInLine, input);
			}
		});

		ParserRuleContext context = parser.path();
		return new Pair<>(context, tokens);
	}

	/**
	 * Retrieve set of projected field.
	 * 
	 * @param input
	 * @return
	 */
	public List<String> extractParams(final String input) {
		final List<String> result = new LinkedList<>();

		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzeInternal(input, new UriBaseListener() {
			
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				result.add(ctx.bind_parameter_name().getText());
			}
			
			@Override
			public void enterBind_parameter_number(Bind_parameter_numberContext ctx) {
				throw(new KriptonProcessorException(String.format("can not be # in uri %s", input)));
			}
			
			@Override
			public void enterBind_parameter_text(Bind_parameter_textContext ctx) {
				throw(new KriptonProcessorException(String.format("can not be * in uri %s", input)));
			}
			
		});
		return result;
	}

	/**
	 * Replace place holder with element passed by listener
	 * 
	 * @param input
	 * @param listener
	 * @return
	 * 		string obtained by replacements
	 */
	public String replacePlaceHolders(String input, final UriPlaceHolderReplacerListener listener) {
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();
				
		UriBaseListener rewriterListener = new UriBaseListener() {
			
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				String value=listener.onParameterName(ctx.bind_parameter_name().getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));								
			}											
		};
		
		return replaceInternal(input, replace, rewriterListener);
	}
	
	public String replace(String input, final UriReplacerListener listener) {
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();
				
		UriBaseListener rewriterListener = new UriBaseListener() {
			
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				String value=listener.onParameterName(ctx.bind_parameter_name().getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));								
			}
					
		
		};
					
		return replaceInternal(input, replace, rewriterListener);

	}

	private String replaceInternal(String input, final List<Triple<Token, Token, String>> replace, UriBaseListener rewriterListener) {
		Pair<ParserRuleContext, CommonTokenStream> parser = prepareUri(input);
		walker.walk(rewriterListener, parser.value0);

		TokenStreamRewriter rewriter = new TokenStreamRewriter(parser.value1);

		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}

		return rewriter.getText();
	}
	
	public interface UriPlaceHolderReplacerListener {
		
		String onParameterName(String name);			
	}
	
	public interface UriReplacerListener {		
		String onParameterName(String name);
	}
	
	public static class UriParameterName {
		private UriParameterName(String value) {
			values=value.split("\\.");
		}
		
		private String[] values;
		
		public String getValue() {
			return values[values.length-1];			
		}
		
		public static UriParameterName parse(String value) {
			return new UriParameterName(value);
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
	public void verify(final String input) {
		this.analyzeInternal(input, new UriBaseListener());
	}

	/**
	 * Extract all bind parameters and dynamic part used in query.
	 * 
	 * @param jql
	 * @return
	 */
	public List<UriPlaceHolder> extractPlaceHoldersAsList(String jql) {
		return extractPlaceHolders(jql, new ArrayList<UriPlaceHolder>());
	}
		
	private <L extends Collection<UriPlaceHolder>> L extractPlaceHolders(String jql, final L result) {		
		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzeInternal(jql, new UriBaseListener() {
			
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				result.add(new UriPlaceHolder(ctx.bind_parameter_name().getText()));				
			}				
					
		});
		return result;
	}

	public List<UriPlaceHolder>  extractPlaceHoldersFromPath(String input) {
		final List<UriPlaceHolder> result=new ArrayList<>();
		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzePathInternal(input, new UriBaseListener() {
			
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				result.add(new UriPlaceHolder(ctx.bind_parameter_name().getText()));				
			}
						
		});
		return result;
	}

	public void verifyPath(String input) {
		this.analyzePathInternal(input, new UriBaseListener());
		
	}
		
}
