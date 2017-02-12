package bind.kripton109.animations;

public class MockParallel2Handler extends Parallel2Handler<MockKeyFrame, MockKeyFrame> {
	
	public MockParallel2Handler()
	{
		handler0=new MockHandler();
		handler1=new MockHandler();
	}
	
}
