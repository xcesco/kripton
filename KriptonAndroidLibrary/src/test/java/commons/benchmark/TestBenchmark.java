/**
 * 
 */
package commons.benchmark;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.abubusoft.kripton.KriptonBinder;

import commons.benchmark.model.Response;

/**
 * @author xcesco
 *
 */
public class TestBenchmark {

	@Test
	public void test() throws IOException
	{
		URL base = getClass().getClassLoader().getResource("benchmark/largesample.json");

		String input = IOUtils.toString(base, Charset.forName("UTF-8"));
		System.out.println(base.getPath());

		long start=System.currentTimeMillis();
		for (int i = 0; i < 20000; i++) {
			Response output = KriptonBinder.jsonBind().parse(input, Response.class);

			if (!"success".equals(output.status)) {
				throw new RuntimeException();
			}
		}
		long end=System.currentTimeMillis();
		
		System.out.println("Time to elaborate "+(end-start));
	}
}
