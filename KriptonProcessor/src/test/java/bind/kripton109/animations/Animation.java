package bind.kripton109.animations;

import java.util.ArrayList;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindXml;

/**
 * <p>Rappresenta un'animazione, o per meglio dire la sua definizione. Gli oggetti di questo tipo non vengono utilizzati direttamente per animare le entità, quanto per definire come
 * animarli.</p>
 * 
 * @author Francesco Benincasa
 * 
 * @param <K>
 */
public class Animation<K extends KeyFrame> {
	
	/**
	 * Sequenza di frame
	 */	
	@Bind(order=4 )
	@BindXml(elementTag= "frame")
	public ArrayList<K> frames;
	
	/**
	 * loop dell'animazione
	 */
	@Bind(order=1)
	protected boolean loop=false;

	/**
	 * nome dell'animazione
	 */
	@Bind(order=0)
	protected String name;

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
	 * Defines the direction/speed at which the AbstractAnimation is expected to be played. The absolute value of rate indicates the speed which the AbstractAnimation is to be played, while the
	 * sign of rate indicates the direction. A positive value of rate indicates forward play, a negative value indicates backward play and 0.0 to stop a running AbstractAnimation. Rate 1.0
	 * is normal play, 2.0 is 2 time normal, -1.0 is backwards, etc... Inverting the rate of a running AbstractAnimation will cause the AbstractAnimation to reverse direction in place and play
	 * back over the portion of the AbstractAnimation that has already elapsed.
	 * 
	 * <b>DefaultValue: 1.0</b>
	 */
	@Bind(order=3)
	protected float rate=1.0f;

	/**
	 * @return the rate
	 */
	public float getRate() {
		return rate;
	}

	/**
	 * costruttore
	 */
	public Animation() {
		frames = new ArrayList<K>();
		rate = 1.0f;
		loop = false;

		// autoReverse=false;
	}

	/**
	 * <p>
	 * Defines the number of cycles in this animation. The cycleCount may be INDEFINITE for animations that repeat indefinitely, but must otherwise be > 0. It is not possible to
	 * change the cycleCount of a running AbstractAnimation. If the value of cycleCount is changed for a running AbstractAnimation, the animation has to be stopped and started again to pick up the
	 * new value.
	 * </p>
	 * 
	 * <b>DefaultValue: 1.0</b>
	 */
	//public int cycleCount=1;

	/**
	 * costruttore
	 */
	public Animation(ArrayList<K> values) {
		frames = values;
		rate = 1.0f;
		loop = false;

		// autoReverse=false;
	}

	/**
	 * <p>
	 * Aggiunge un keyframe. Se il nome non esiste, viene aggiunto di default con la regola <code>keyframe + i</code> dove i è va da 0 a n (numero di frame).
	 * </p>
	 * 
	 * @param frame
	 *      frame da aggiungere
	 * @return
	 * 		indice del frame appena inserito
	 */
	public int add(K frame) {
		// se non abbiamo un nome, lo aggiungiamo di default
		if (frame.name == null) {
			frame.name = "keyframe" + frames.size();
		}
		frames.add(frame);
	
		return frames.size()-1;
	}

	/**
	 * misura la durata. Da tenere in considerazione che questa non ha alcun senso se il flag loop è impostato a true, dato che l'animazione andrà avanti all'infinito.
	 * 
	 * @return
	 */
	public long duration() {
		long duration = 0;
		int n = frames.size();
		for (int i = 0; i < n; i++) {
			duration += frames.get(i).duration;
		}

		return (long) (duration * rate);
	}
	
	public K getFrame(int index) {		
		return frames.get(index);
	}

	/**
	 * durata dell'animazione. Anche se è infinita, viene restituito il conto dei ms di durata dei vari frame (moltiplicati per il cycleCount). 
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

	/**
	 * <p>
	 * Defines whether this AbstractAnimation reverses direction on alternating cycles. If true, the AbstractAnimation will proceed forward on the first cycle, then reverses on the second cycle,
	 * and so on. Otherwise, animation will loop such that each cycle proceeds forward from the start. It is not possible to change the autoReverse flag of a running AbstractAnimation. If
	 * the value of autoReverse is changed for a running AbstractAnimation, the animation has to be stopped and started again to pick up the new value.
	 * </p>
	 * 
	 * <b>DefaultValue: false</b>
	 */
	//public boolean autoReverse=false;
	
	/**
	 * <p>Definisce due frame: il primo con i valori di default ed il secondo con il valore che viene passato qua. L'intervallo di
	 * tempo viene utilizzato per definire il passaggio tra i due.</p>
	 * 
	 * <p>L'intervallo annulla gli altri eventuali frame già presenti.</p>
	 * 
	 * @param value
	 * @param duration
	 */
	public void setInterval(K firstFrame, K secondFrame)
	{
		frames.clear();
		add(firstFrame);
		add(secondFrame);
	}

	public void setLoop(boolean value) {
		loop=value;
	}

	public void setRate(float value) {
		rate=value;
	}

	/**
	 * numero di frames
	 * 
	 * @return
	 */
	public int size() {
		return frames.size() /* *this.cycleCount */;
	}


}
