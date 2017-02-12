package bind.kripton109.animations.events;

import bind.kripton109.animations.KeyFrame;

public interface EventFrameListener<K extends KeyFrame> {

	/**
	 * Evento relativo al cambio del key frame. Siamo all'inizio del frame.  
	 * 
	 * @param currentFrame
	 * 		key frame attualmente usato. 
	 */
	void onFrameBegin(K currentFrame);
	
	/**
	 * fine frame
	 * @param currentFrame 
	 */
	void onFrameEnd(K currentFrame);

}