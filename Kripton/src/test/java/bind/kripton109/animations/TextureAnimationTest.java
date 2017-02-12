/**
 * 
 */
package bind.kripton109.animations;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;

import com.abubusoft.kripton.KriptonBinder;

import bind.AbstractBaseTest;

/**
 * @author Francesco Benincasa
 *
 */
public class TextureAnimationTest extends AbstractBaseTest {

	@Test
	public void test01() throws FileNotFoundException {
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("bind/kripton109/animations/textureAnimation.json");

		TextureAnimation animation0 = KriptonBinder.jsonBind().parse(input, TextureAnimation.class);
		String string0 = KriptonBinder.jsonBind().serialize(animation0);

		TextureAnimation animation1 = KriptonBinder.jsonBind().parse(string0, TextureAnimation.class);
		String string1 = KriptonBinder.jsonBind().serialize(animation1);

		System.out.println(string1);

	}
}
