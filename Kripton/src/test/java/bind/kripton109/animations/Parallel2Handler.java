package bind.kripton109.animations;

import java.util.ArrayList;

import bind.kripton109.animations.events.EventFrameListener;

/**
 * <p>
 * Gestisce più animazioni contemporaneamente.
 * </p>
 * 
 * <p>Gli handler devono venire definiti a livello di costruttore!.</p>
 * 
 * <ul>
 * <li>aggiunge gli handler</li>
 * <li>imposta l'animazione</li>
 * </ul>
 * 
 * <p>
 * </p>
 * 
 * @author Francesco Benincasa
 * 
 */
public abstract class Parallel2Handler<K0 extends KeyFrame, K1 extends KeyFrame> extends AnimationHandler<K0> {

	/**
	 * Sequenza di frame
	 */
	protected ArrayList<K1> frames1;

	protected AnimationHandler<K0> handler0;

	protected AnimationHandler<K1> handler1;

	public void setFrameListener(EventFrameListener<K0> listener) {
		handler0.setFrameListener(listener);
	}
	
	public void setFrameListener1(EventFrameListener<K1> listener) {
		handler1.setFrameListener(listener);
	}

	public Parallel2Handler() {
	}

	@Override
	public K0 update(long enlapsedTimeValue) {
		boolean allStopped = true;

		// calcoliamo i valori e li mettiamo in temp
		handler0.update(enlapsedTimeValue);
		handler1.update(enlapsedTimeValue);

		allStopped = (handler0.status == StatusType.STOPPED) && (handler1.status == StatusType.STOPPED);

		// se tutti sono terminati, allora è terminato
		if (allStopped)
			status = StatusType.STOPPED;

		return handler0.value();
	}

	/**
	 * Restituisce il valore 1
	 * 
	 * @return
	 */
	public K0 value() {

		return handler0.value();
	}

	/**
	 * Restituisce il valore 1
	 * 
	 * @return
	 */
	public K1 value1() {

		return handler1.value();
	}

	@Override
	public void reset() {
		handler0.reset();
		handler1.reset();

		status = StatusType.STOPPED;
	}

	@Override
	public void playFromStart() {
		handler0.playFromStart();
		handler1.playFromStart();

		status = StatusType.RUNNING;
	}

	@Override
	public void play() {
		handler0.play();
		handler1.play();

		status = StatusType.RUNNING;
	}

	@Override
	public void stop() {
		handler0.stop();
		handler1.stop();

		status = StatusType.STOPPED;
	}

	@Override
	public void oneMoreTime() {
		handler0.oneMoreTime();
		handler1.oneMoreTime();
	}

	@Override
	public void pause() {
		handler0.pause();
		handler1.pause();

		status = StatusType.PAUSED;
	}

	@Override
	public boolean isFinished() {
		return status == StatusType.STOPPED;
	}

	@Override
	public long duration() {
		long duration = 0;

		duration = Math.max(duration, handler0.duration());
		duration = Math.max(duration, handler1.duration());

		return duration;
	}

	@Override
	protected K0 value(K0 current, long enlapsedTime, K0 next) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected K0 buildFrame() {
		// non serve
		return null;
	}

	@Override
	public void set(Animation<K0> value) {
		@SuppressWarnings("unchecked")
		Parallel2Animation<K0, K1> valueA=(Parallel2Animation<K0, K1>) value;
		
		animation = null;
		copyFrom(value);
		inc = 1;// animation.rate > 0 ? 1 : -1;

		status = StatusType.STOPPED;

		enlapsedTime = 0;
		cycleCount = 0;

		// frame di appoggio per il calcolo del frame corrente
		temp = null;		
		
		Animation<K0> anim=new Animation<>(valueA.frames);
		handler0.set(anim);
		
		Animation<K1> anim1=new Animation<>(valueA.frames1);
		handler1.set(anim1);
		
	}

}
