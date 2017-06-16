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
/**
 * 
 */
package com.abubusoft.kripton.processor.sqlite.grammars.uri;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.processor.core.AssertKripton;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.UriParser.Bind_parameterContext;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.UriParser.Bind_parameter_numberContext;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.UriParser.Bind_parameter_textContext;
import com.abubusoft.kripton.processor.sqlite.grammars.uri.UriParser.Path_segmentContext;

/**
 * @author Francesco Benincasa (abubusoft@gmail.com)
 *
 */
public class ContentUriChecker {

	public interface UriPlaceHolderReplacerListener {

		String onParameterName(int pathSegmentIndex, String name);

	}

	protected static ContentUriChecker instance;

	public static final ContentUriChecker getInstance() {
		if (instance == null) {
			instance = new ContentUriChecker();
		}

		return instance;
	}

	public int pathSegmentIndex = -1;

	ParseTreeWalker walker = new ParseTreeWalker();

	private ContentUriChecker() {

	}

	private <L extends UriBaseListener> void analyzeInternal(final String input, L listener) {
		pathSegmentIndex = -1;
		walker.walk(listener, prepareUri(input).value0);
	}

	private <L extends UriBaseListener> void analyzePathInternal(final String input, L listener) {
		pathSegmentIndex = -1;
		walker.walk(listener, preparePath(input).value0);
	}

	/**
	 * <p>Extract all parameter present in path element of a content URI.</p>
	 * 
	 * <p>For example:</p>
	 * 
	 * <pre>
	 * content://androi.authority/test/${input1 }/${input2}
	 * </pre>
	 * 
	 * <p>Has two parameters: <code>input1</code> and <code>input2</code>. 
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
				throw (new KriptonProcessorException(String.format("can not be # in uri %s", input)));
			}

			@Override
			public void enterBind_parameter_text(Bind_parameter_textContext ctx) {
				throw (new KriptonProcessorException(String.format("can not be * in uri %s", input)));
			}

		});
		return result;
	}

	/**
	 * Extract all parameters from URI.
	 * 
	 * @param input
	 * @return
	 */
	public List<ContentUriPlaceHolder> extract(String input) {
		return extractPlaceHoldersFromURI(input, new ArrayList<ContentUriPlaceHolder>());
	}
	
	/**
	 * Extract all parameters from URI as a map.
	 * 
	 * @param input
	 * @return
	 */
	public Map<String, ContentUriPlaceHolder> extractAsMap(String input) {		
		HashMap<String, ContentUriPlaceHolder> result=new HashMap<>();
		ArrayList<ContentUriPlaceHolder> list = extractPlaceHoldersFromURI(input, new ArrayList<ContentUriPlaceHolder>());
		
		for (ContentUriPlaceHolder item: list) {
			result.put(item.value, item);
		}
		
		return result;
	}

	/**
	 * @param input
	 * @return
	 */
	public List<ContentUriPlaceHolder> extractFromPath(String input) {
		final List<ContentUriPlaceHolder> result = new ArrayList<>();
		final One<Boolean> valid = new One<>();
		valid.value0 = false;

		analyzePathInternal(input, new UriBaseListener() {
			
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				result.add(new ContentUriPlaceHolder(pathSegmentIndex, ctx.bind_parameter_name().getText()));
			}

			@Override
			public void enterPath_segment(Path_segmentContext ctx) {
				pathSegmentIndex++;
			}

		});
		return result;
	}

	private <L extends Collection<ContentUriPlaceHolder>> L extractPlaceHoldersFromURI(String uri, final L result) {
		final One<Boolean> valid = new One<>();
		valid.value0 = false;			

		analyzeInternal(uri, new UriBaseListener() {

			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				result.add(new ContentUriPlaceHolder(pathSegmentIndex, ctx.bind_parameter_name().getText()));
			}

			@Override
			public void enterPath_segment(Path_segmentContext ctx) {
				pathSegmentIndex++;
			}

		});
		return result;
	}

	private Pair<ParserRuleContext, CommonTokenStream> preparePath(final String input) {
		UriLexer lexer = new UriLexer(CharStreams.fromString(input));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		UriParser parser = new UriParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new ContentUriBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				AssertKripton.assertTrue(false, "unespected char at pos %s of SQL '%s'", charPositionInLine, input);
			}
		});

		ParserRuleContext context = parser.path();
		return new Pair<>(context, tokens);
	}

	private Pair<ParserRuleContext, CommonTokenStream> prepareUri(final String input) {
		UriLexer lexer = new UriLexer(CharStreams.fromString(input));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		UriParser parser = new UriParser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(new ContentUriBaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
					int charPositionInLine, String msg, RecognitionException e) {
				AssertKripton.assertTrue(false, "unespected char at pos %s of URI '%s'", charPositionInLine, input);
			}

			@Override
			public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact,
					BitSet ambigAlts, ATNConfigSet configs) {
				AssertKripton.assertTrue(false, "ambiguity syntax at pos %s of URI '%s'", startIndex, input);
			}

			@Override
			public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
					BitSet conflictingAlts, ATNConfigSet configs) {
				AssertKripton.assertTrue(false, "error at pos %s of URI '%s'", startIndex, input);
			}

			@Override
			public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
					int prediction, ATNConfigSet configs) {
				AssertKripton.assertTrue(false, "context eror at pos %s of URI '%s'", startIndex, input);
			}					
			
		});		

		ParserRuleContext context = parser.uri();
		return new Pair<>(context, tokens);
	}

	private String replaceInternalFromPath(String input, final List<Triple<Token, Token, String>> replace,
			UriBaseListener rewriterListener) {
		Pair<ParserRuleContext, CommonTokenStream> parser = preparePath(input);
		pathSegmentIndex=-1;
		walker.walk(rewriterListener, parser.value0);

		TokenStreamRewriter rewriter = new TokenStreamRewriter(parser.value1);

		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}

		return rewriter.getText();
	}
	
	private String replaceInternalFromUri(String input, final List<Triple<Token, Token, String>> replace,
			UriBaseListener rewriterListener) {
		Pair<ParserRuleContext, CommonTokenStream> parser = prepareUri(input);
		pathSegmentIndex=-1;
		walker.walk(rewriterListener, parser.value0);

		TokenStreamRewriter rewriter = new TokenStreamRewriter(parser.value1);

		for (Triple<Token, Token, String> item : replace) {
			rewriter.replace(item.value0, item.value1, item.value2);
		}

		return rewriter.getText();
	}

	/**
	 * <p>
	 * Replace place holders from URI string
	 * </p>
	 * 
	 * @param input
	 * @param listener
	 * 
	 * @return string obtained by replacements
	 */
	public String replace(String input, final UriPlaceHolderReplacerListener listener) {
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();

		UriBaseListener rewriterListener = new UriBaseListener() {
			
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				String value = listener.onParameterName(pathSegmentIndex, ctx.bind_parameter_name().getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
			}

			@Override
			public void enterPath_segment(Path_segmentContext ctx) {
				pathSegmentIndex++;
			}
		};

		return replaceInternalFromUri(input, replace, rewriterListener);
	}
	
	
	/**
	 * <p>
	 * Replace place holders from PATH string
	 * </p>
	 * 
	 * @param input
	 * @param listener
	 * @return
	 */
	public String replaceFromPath(String input, final UriPlaceHolderReplacerListener listener) {
		final List<Triple<Token, Token, String>> replace = new ArrayList<>();

		UriBaseListener rewriterListener = new UriBaseListener() {
			
			@Override
			public void enterBind_parameter(Bind_parameterContext ctx) {
				String value = listener.onParameterName(pathSegmentIndex, ctx.bind_parameter_name().getText());
				replace.add(new Triple<Token, Token, String>(ctx.start, ctx.stop, value));
			}

			@Override
			public void enterPath_segment(Path_segmentContext ctx) {
				pathSegmentIndex++;
			}
		};

		return replaceInternalFromPath(input, replace, rewriterListener);
	}

	/**
	 * <p>
	 * Verify content URI is syntactally correct, otherwise a KriptonProcessorException
	 * will be thrown.
	 * </p>
	 * 
	 * @param input
	 */
	public void verify(final String input) {
		this.analyzeInternal(input, new UriBaseListener());
	}

	/**
	 * <p>
	 * Verify content URI path is syntactally correct, otherwise a KriptonProcessorException
	 * will be thrown.
	 * </p>
	 * 
	 * @param input
	 */
	public void verifyPath(String input) {
		this.analyzePathInternal(input, new UriBaseListener());

	}

}
