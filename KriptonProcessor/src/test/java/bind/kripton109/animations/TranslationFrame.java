package bind.kripton109.animations;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

import bind.kripton109.animations.math.Vector3;

@BindType
public class TranslationFrame extends KeyFrame {

	public static TranslationFrame build(long duration) {
		return build(0f, 0f, 0f, duration);
	}

	public static TranslationFrame build(float x, float y, float z, long duration) {
		TranslationFrame frame = new TranslationFrame();

		frame.translation.setCoords(x, y, z);
		frame.duration = duration;

		return frame;
	}

	@Bind
	public Vector3 translation = new Vector3();

	public TranslationFrame() {
	}

}
