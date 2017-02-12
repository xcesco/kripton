package bind.kripton109.animations.interpolations;

/**
 * Funzione atta a calcolare la percentuale
 * @author Francesco Benincasa
 *
 */
public interface Interpolation {
	/**
	 * Restituisce la percentuale di tempo trascorso in base alla funzione implementata.
	 * 
	 * @param enlapsedTime
	 * 		tempo trascorso in secondi
	 * @param duration
	 * 		tempo massimo in secondi
	 * @return
	 * 		percentuale da 0 a 1
	 */
	float getPercentage(final float enlapsedTime, final float duration);
}
