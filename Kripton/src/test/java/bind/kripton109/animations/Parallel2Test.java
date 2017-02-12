/**
 * 
 */
package bind.kripton109.animations;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.abubusoft.kripton.KriptonBinder;

import bind.AbstractBaseTest;
import bind.kripton109.animations.events.EventFrameListener;

/**
 * @author Francesco Benincasa
 *
 */
public class Parallel2Test extends AbstractBaseTest {

	static int val0 = 0;
	static int val1 = 0;

	@Test
	public void testSimple() {
		val0 = 0;
		val1 = 0;

		MockAnimation anim0 = new MockAnimation();
		anim0.setInterval(MockKeyFrame.build("a0", 100, 30), MockKeyFrame.build("a0", 200, 30));

		MockAnimation anim1 = new MockAnimation();
		anim1.setInterval(MockKeyFrame.build("b1", 0, 30), MockKeyFrame.build("b2", -100, 30));

		// definiamo animazione
		MockParallel2 parallel = new MockParallel2();
		parallel.setAnimation(anim0);
		parallel.setAnimation1(anim1);

		MockParallel2Handler handler = new MockParallel2Handler();
		// impostiamo handlers e listeners
		handler.setFrameListener(new EventFrameListener<MockKeyFrame>() {

			@Override
			public void onFrameBegin(MockKeyFrame currentFrame) {
				System.out.println(String.format("Entro %s con val = %s + %s", currentFrame.name, val0, currentFrame.val));
				val0 += currentFrame.val;
			}

			@Override
			public void onFrameEnd(MockKeyFrame currentFrame) {
				// TODO Auto-generated method stub

			}

		});

		handler.setFrameListener1(new EventFrameListener<MockKeyFrame>() {

			@Override
			public void onFrameBegin(MockKeyFrame currentFrame) {
				
				log("Entro %s con val = %s + %s", currentFrame.name, val1, currentFrame.val);
				val1 += currentFrame.val;
			}

			@Override
			public void onFrameEnd(MockKeyFrame currentFrame) {
				// TODO Auto-generated method stub

			}

		});
		// impostiamo animazione
		handler.set(parallel);
		handler.play();

		while (!handler.isFinished()) {
			handler.update(10);
			log("global 0:(%s, transition %s) 1: (%s, transition %s)\n", val0, handler.value().val, val1, handler.value().val);
		}

	}

	@Test
	public void testSimple2() {
		val0 = 0;
		val1 = 0;

		MockAnimation anim0 = new MockAnimation();
		anim0.setInterval(MockKeyFrame.build("a0", 0, 10), MockKeyFrame.build("a0", 200, 0));

		MockAnimation anim1 = new MockAnimation();
		anim1.setInterval(MockKeyFrame.build("b1", 0, 30), MockKeyFrame.build("b2", -100, 30));

		// definiamo animazione
		MockParallel2 parallel = new MockParallel2();
		parallel.setAnimation(anim0);
		parallel.setAnimation1(anim1);

		MockParallel2Handler handler = new MockParallel2Handler();
		// impostiamo handlers e listeners
		handler.setFrameListener(new EventFrameListener<MockKeyFrame>() {

			@Override
			public void onFrameBegin(MockKeyFrame currentFrame) {
				log("Entro %s con val = %s + %s", currentFrame.name, val0, currentFrame.val);
				val0 += currentFrame.val;
			}

			@Override
			public void onFrameEnd(MockKeyFrame currentFrame) {
				// TODO Auto-generated method stub

			}

		});

		handler.setFrameListener1(new EventFrameListener<MockKeyFrame>() {

			@Override
			public void onFrameBegin(MockKeyFrame currentFrame) {
				log("Entro %s con val = %s + %s", currentFrame.name, val1, currentFrame.val);
				val1 += currentFrame.val;
			}

			@Override
			public void onFrameEnd(MockKeyFrame currentFrame) {
				// TODO Auto-generated method stub

			}

		});
		// impostiamo animazione
		handler.set(parallel);

		handler.play();

		while (!handler.isFinished()) {
			handler.update(10);
			// print("value %s %s\n", ((MockKeyValue)values.values[0]).val, ((MockKeyValue)values.values[1]).val);
			log("global 0:(%s, transition %s) 1: (%s, transition %s)\n", val0, handler.value().val, val1, handler.value1().val);
		}

	}

	@Test
	public void testPersists() {
		File file = (new File("parallel.json")).getAbsoluteFile();
		log("Scrivo su %s", file.getAbsolutePath());

		MockAnimation anim0 = new MockAnimation();
		MockAnimation anim1 = new MockAnimation();

		anim0.setInterval(MockKeyFrame.build("a0", 100, 15), MockKeyFrame.build("a1", 200, 15));
		anim1.setInterval(MockKeyFrame.build("b0", 0, 30), MockKeyFrame.build("b1", 100, 30));

		MockParallel2 parallel = new MockParallel2();
		parallel.name = "ciao";

		parallel.setAnimation(anim0);
		parallel.setAnimation1(anim1);

		String outputString =KriptonBinder.jsonBind().serialize(parallel);
		
		// leggiamo
		MockParallel2 test = KriptonBinder.jsonBind().parse(outputString, MockParallel2.class);

		print(test);
		String inputString =KriptonBinder.jsonBind().serialize(parallel);
		

		Assert.assertEquals("", inputString, outputString);

		log("test %s", test.name);

	}

	public void print(MockParallel2 anim) {
		log(KriptonBinder.jsonBind().serialize(anim) + "\n\n");
	}
}
