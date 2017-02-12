/**
 * 
 */
package bind.kripton109.animations;

import java.util.ArrayList;

import com.abubusoft.kripton.annotation.Bind;

/**
 * @author Francesco Benincasa
 * 
 */
public abstract class Parallel2Animation<K0 extends KeyFrame, K1 extends KeyFrame> extends Animation<K0> {
	
	/**
	 * numero di animazioni
	 */
	static final int NUMBER_OF_ANIMATIONS=2;
	
	/**
	 * Sequenza di frame
	 */
	@Bind("frame1")	
	public ArrayList<K1> frames1;

	public Parallel2Animation() {
		frames1=new ArrayList<>();
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
	public int add1(K1 frame) {
		// se non abbiamo un nome, lo aggiungiamo di default
		if (frame.name == null) {
			frame.name = "keyframe1-" + frames.size();
		}
		frames1.add(frame);
	
		return frames1.size()-1;
	}
	
	/**
	 * misura la durata. Da tenere in considerazione che questa non ha alcun senso se il flag loop è impostato a true, dato che l'animazione andrà avanti all'infinito.
	 *   
	 * @return
	 */
	@Override
	public long duration() {
		long maxDuration = 0;
		
		{
			// frame
			long duration = 0;
			int n = frames.size();
			for (int i = 0; i < n; i++) {
				duration += frames.get(i).duration;
			}

			maxDuration=Math.max(maxDuration,(long) (duration * rate));
		}
		
		{
			// frame 1
			long duration = 0;
			int n = frames1.size();
			for (int i = 0; i < n; i++) {
				duration += frames1.get(i).duration;
			}

			maxDuration=Math.max(maxDuration,(long) (duration * rate));
		}
		
		return maxDuration;
	}
	
	public K1 getFrame1(int index) {		
		return frames1.get(index);
	}
	
	public void setAnimation(Animation<K0> value)
	{
		frames=value.frames;
	}
	
	public void setAnimation1(Animation<K1> value)
	{
		frames1=value.frames;
	}


}
