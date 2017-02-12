package bind.kripton109.animations;


public class MockHandler extends AnimationHandler<MockKeyFrame> {

	@Override
	protected MockKeyFrame value(MockKeyFrame current, long enlapsedTime, MockKeyFrame next) {
		if (next != null) {
			float perc = current.interpolation.getPercentage(enlapsedTime, current.duration * this.rate);

			// temp viene sempre scritto, non importa cosa c'è prima
			temp.val=current.val+(int)(next.val*perc);
			//Vector3.multiply(next.translation, perc, temp.translation);
			return temp;
		} else {
			// siamo sull'ultimo frame, dopo non c'è niente. La traslazione è 0
			//temp.translation.setCoords(0, 0, 0);
			
			temp.val=0;
			return temp;
		}
		
	}

	@Override
	protected MockKeyFrame buildFrame() {
		return new MockKeyFrame();
	}

}
