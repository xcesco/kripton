/**
 * 
 */
package bind.kripton109.animations;

import bind.kripton109.animations.events.EventAnimationListener;
import bind.kripton109.animations.events.EventFrameListener;
import bind.kripton109.animations.interpolations.Interpolation;
import bind.kripton109.animations.interpolations.InterpolationLinear;

/**
 * 
 * @author Francesco Benincasa
 * @param <K>
 * 
 */

public abstract class AnimationHandler<K extends KeyFrame> {
	
	/**
	 * <p>
	 * Restituisce il valore dell'animazione inserendo il frame corrente, quello successivo (che può anche essere nullo) ed il tempo trascorso da quando siamo sul frame corrente.
	 * </p>
	 * 
	 * <p>
	 * <strong>Il valore deve essere consumato prima di invocare lo stesso metodo sulla stessa animazione. Questo è dovuto al fatto che si usa una variabile di appoggio che rende
	 * il sistema non THREAD-SAFE.</strong>
	 * </p>
	 * 
	 * @param current
	 * @param enlapsedTime
	 * @param next
	 * @return
	 */
	protected abstract K value(K current, long enlapsedTime, K next);
	
	protected abstract K buildFrame();		

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the cycleCount
	 */
	public int getCycleCount() {
		return cycleCount;
	}

	/**
	 * @param cycleCount the cycleCount to set
	 */
	public void setCycleCount(int cycleCount) {
		this.cycleCount = cycleCount;
	}

	/**
	 * @param loop the loop to set
	 */
	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public AnimationHandler(Animation<K> animation) {
		set(animation);
	}

	public AnimationHandler() {
	}

	protected K temp;

	/**
	 * nome dell'animazione
	 */
	protected String name;

	/**
	 * loop dell'animazione
	 */
	protected boolean loop = false;

	/**
	 * Defines the direction/speed at which the AbstractAnimation is expected to be played. The absolute value of rate indicates the speed which the AbstractAnimation is to be
	 * played, while the sign of rate indicates the direction. A positive value of rate indicates forward play, a negative value indicates backward play and 0.0 to stop a running
	 * AbstractAnimation. Rate 1.0 is normal play, 2.0 is 2 time normal, -1.0 is backwards, etc... Inverting the rate of a running AbstractAnimation will cause the
	 * AbstractAnimation to reverse direction in place and play back over the portion of the AbstractAnimation that has already elapsed.
	 * 
	 * <b>DefaultValue: 1.0</b>
	 */
	protected float rate = 1.0f;

	/**
	 * <p>
	 * Defines the number of cycles in this animation. The cycleCount may be INDEFINITE for animations that repeat indefinitely, but must otherwise be > 0. It is not possible to
	 * change the cycleCount of a running AbstractAnimation. If the value of cycleCount is changed for a running AbstractAnimation, the animation has to be stopped and started
	 * again to pick up the new value.
	 * </p>
	 * 
	 * <b>DefaultValue: 1.0</b>
	 */
	// public int cycleCount=1;

	/**
	 * <p>
	 * Defines whether this AbstractAnimation reverses direction on alternating cycles. If true, the AbstractAnimation will proceed forward on the first cycle, then reverses on the
	 * second cycle, and so on. Otherwise, animation will loop such that each cycle proceeds forward from the start. It is not possible to change the autoReverse flag of a running
	 * AbstractAnimation. If the value of autoReverse is changed for a running AbstractAnimation, the animation has to be stopped and started again to pick up the new value.
	 * </p>
	 * 
	 * <b>DefaultValue: false</b>
	 */
	// public boolean autoReverse=false;

	/**
	 * Copia da input tutte le caratteristiche dell'animazione.
	 * 
	 * @param input
	 */
	void copyFrom(Animation<K> input) {
		// autoReverse = input.autoReverse;
		// cycleCount = input.cycleCount;
		loop = input.isLoop();
		rate = input.getRate();
	}

	public String name() {
		return name;
	}

	/**
	 * durata dell'animazione. Anche se è infinita, viene restituito il conto dei ms di durata dei vari frame (moltiplicati per il cycleCount).
	 * 
	 * @return
	 */
	/**
	 * Se <code>true indica
	 * 
	 * @return
	 */
	public boolean isLoop() {
		return loop;
	}

	public float getRate() {
		return this.rate;
	}

	protected static final Interpolation DEFAULT_INTERPOLATOR = InterpolationLinear.instance();

	public Interpolation interpolation = DEFAULT_INTERPOLATOR;

	/**
	 * indice corrente del frame dell'animazione
	 */
	public int currentFrameIndex;

	/**
	 * key frame corrente
	 */
	public K currentFrame;

	/**
	 * key frame successivo
	 */
	public K nextFrame;

	/**
	 * tempo trascorso dall'inizio dell'animazione
	 */
	public long enlapsedTime;

	/**
	 * una volta terminata l'animazione, indica quanti ms sono avanzati rispetto al termine dell'animazione
	 */
	public long remaingTime;

	/**
	 * se true indica che l'animazione è stata avviata
	 */
	public StatusType status = StatusType.STOPPED;

	/**
	 * The possible states for handler.
	 */
	public enum StatusType {
		PAUSED,
		/** The running state. */
		RUNNING,
		/** The stopped state. */
		STOPPED

	}

	/**
	 * variabile temporanea usata nel metodo {@link AnimationHandler#update(long)}
	 */
	long currentFrameDuration;

	/**
	 * listener per gli eventi legati all'animazione (che include anche i frame)
	 */
	protected EventAnimationListener<K> animationListener;

	/**
	 * listener per gli eventi legati esclusivamente ai frame
	 */
	protected EventFrameListener<K> frameListener;

	/**
	 * Imposta l'event listener
	 * 
	 * @param listener
	 */
	public void setAnimationListener(EventAnimationListener<K> listener) {
		this.animationListener = listener;
	}

	public void setFrameListener(EventFrameListener<K> listener) {
		this.frameListener = listener;
	}

	/**
	 * <p>
	 * Aggiorna lo stato dell'animazione agganciata all'animator.
	 * </p>
	 * 
	 * @param animation
	 *            animazione corrente
	 * @param enlapsedTimeValue
	 *            tempo trascorso dall'ultima volta che abbiamo fatto refresh
	 * @param channel
	 * @return frame corrente
	 */
	public K update(long enlapsedTimeValue) {
		if (status != StatusType.RUNNING) {
			return value(currentFrame, enlapsedTime, nextFrame);
		}

		enlapsedTime += enlapsedTimeValue;
		currentFrameDuration = (long) (currentFrame.duration * rate);

		while (enlapsedTime >= currentFrameDuration) {
			enlapsedTime -= currentFrameDuration;
			if (enlapsedTimeValue >= 0) {
				if (currentFrameIndex + 1 >= animation.size()) {
					if (!loop && cycleCount == 0) {
						status = StatusType.STOPPED;
					}
				}

				if (frameListener != null)
					frameListener.onFrameEnd(currentFrame);

				if (status == StatusType.STOPPED) {
					remaingTime = enlapsedTime;
					enlapsedTime = currentFrameDuration;
					if (animationListener != null)
						animationListener.onAnimationStop(currentFrame);
					break;
				}
			}

			currentFrameIndex++;
			if (currentFrameIndex >= animation.size()) {
				if (!loop) {
					if (cycleCount == 0) {
						status = StatusType.STOPPED;
						currentFrameIndex = animation.size() - 1;
					} else {
						cycleCount--;
						currentFrameIndex = 0;
					}
				} else {
					currentFrameIndex = 0;
				}
			}

			// impostiamo il frame corrente e quello successivo
			currentFrame = animation.getFrame(currentFrameIndex);
			nextFrame = currentFrameIndex < animation.size() - 1 ? animation.getFrame(currentFrameIndex + 1) : null;

			if (frameListener != null) {
				frameListener.onFrameBegin(currentFrame);
			}

			currentFrameDuration = (long) (animation.getFrame(currentFrameIndex).duration * rate);
		}

		temp = value(currentFrame, enlapsedTime, nextFrame);

		return temp;
	}

	/**
	 * <p>
	 * Rieseguiamo ancora una volta l'animazione. Se è in esecuzione, aggiungiamo un ciclo in più. Se è già fermo, viene rieseguito.
	 * </p>
	 */
	public void oneMoreTime() {
		switch (status) {
		case RUNNING:
		case PAUSED:
			cycleCount++;
			break;
		case STOPPED:
			play();
			break;
		default:
			break;
		}
	}

	/**
	 * animazione di riferimento
	 */
	protected Animation<K> animation;

	/**
	 * variabile da usare per selezionare il prossimo keyframe
	 */
	protected int inc;

	/**
	 * contatore di cicli (da 0 a n). 0 indica che non ci sono cicli, viene fatto una volta sola e basta.
	 */
	protected int cycleCount;

	/**
	 * riporta tutto ai valori presi dall'animazione
	 */
	public void reset() {
		set(animation);
		cycleCount = 0;
	}

	/**
	 * Imposta l'animazione. Questo comporta il reset di tutte le variabili
	 * 
	 * @param value
	 */
	public void set(Animation<K> value) {
		animation = value;
		copyFrom(animation);
		inc = 1;// animation.rate > 0 ? 1 : -1;

		status = StatusType.STOPPED;

		currentFrameIndex = 0;
		currentFrame = animation.getFrame(currentFrameIndex);
		nextFrame = currentFrameIndex < animation.size() - 1 ? animation.getFrame(currentFrameIndex + 1) : null;
		enlapsedTime = 0;
		cycleCount = 0;

		// frame di appoggio per il calcolo del frame corrente
		temp = buildFrame();
		temp = value(currentFrame, enlapsedTime, nextFrame);
	}

	/**
	 * Ricominciamo dall'inizio
	 */
	public void playFromStart() {
		status = StatusType.STOPPED;
		play();
	}

	/**
	 * Avvia l'animazione e ci posizioniamo sul frame corretto.
	 * 
	 * @param handler
	 * @param enlapsedTimeValue
	 */
	public void play() {
		if (animation == null)
			throw (new RuntimeException("No animation associated!"));

		switch (status) {
		case STOPPED:
			// ripartiamo da 0
			// set(animation);
			currentFrameIndex = 0;

			currentFrame = animation.getFrame(currentFrameIndex);
			nextFrame = currentFrameIndex < animation.size() - 1 ? animation.getFrame(currentFrameIndex + 1) : null;

			enlapsedTime = 0;

			status = StatusType.RUNNING;

			if (animationListener != null)
				animationListener.onAnimationStart();
			if (frameListener != null)
				frameListener.onFrameBegin(currentFrame);
			break;
		case PAUSED:
			// continuiamo da dove eravamo
			status = StatusType.RUNNING;

			if (animationListener != null) {
				animationListener.onAnimationResume(currentFrame, enlapsedTime);
			}

			break;
		}
	}

	/**
	 * blocca l'animazione
	 */
	public void stop() {
		status = StatusType.STOPPED;

		if (animationListener != null)
			animationListener.onAnimationStop(currentFrame);

		enlapsedTime = 0;
		currentFrameIndex = 0;
	}

	public String getAnimationName() {
		return animation.getName();
	}

	/**
	 * metto solo lo stato
	 */
	public void pause() {
		status = StatusType.PAUSED;
		if (animationListener != null)
			animationListener.onAnimationPause(currentFrame, enlapsedTime);
	}

	/**
	 * Inidica se l'animator ha lanciato l'animazione corrente o no. Se è in loop infinito ed è stata lanciata, ovviamente non finirà mai.
	 * 
	 * @return
	 */
	public boolean isFinished() {
		// se non è in loop e non è iniziato, ovviamente è finito (o non ancora
		// iniziato)
		// se è in loop, non finirà mai, ma è iniziato stopped=true
		return status == StatusType.STOPPED;
	}
	
	/**
	 * indica se è in stato di running
	 * @return
	 */
	public boolean isPlaying()
	{
		return status == StatusType.RUNNING;
	}

	public long duration() {
		return animation.duration() * (cycleCount + 1);
	}

	/**
	 * Recupera il valore corrente.
	 * 
	 * @return
	 */
	public K value() {
		return temp;
	}

	/**
	 * Percentuale di avanzamento all'interno del frame
	 * 
	 * @return
	 */
	/*
	 * public float percentage() { return (1f*enlapsedTime)/currentFrameDuration; }
	 */

}
