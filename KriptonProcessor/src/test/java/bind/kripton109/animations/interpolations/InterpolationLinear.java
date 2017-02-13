package bind.kripton109.animations.interpolations;

public class InterpolationLinear implements Interpolation {
	private static InterpolationLinear instance;

	private InterpolationLinear() {

	}

	public static InterpolationLinear instance() {
		if (instance == null) {
			instance = new InterpolationLinear();
		}
		return instance;
	}

	@Override
	public float getPercentage(final float pSecondsElapsed, final float pDuration) {
		return pSecondsElapsed / pDuration;
	}

}
