package bind.kripton109.animations;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

import bind.kripton109.animations.texture.TextureRegion;

@BindType
public class TextureKeyFrame extends KeyFrame {

	public TextureKeyFrame() {
	}
	
	/*public static TextureKeyFrame build(Texture texture, TextureRegion textureRegion, long duration)
	{
		TextureKeyFrame frame = new TextureKeyFrame();
		
		frame.texture=texture;
		frame.textureRegion=textureRegion;	
		
		return frame;
	}*/
	
	/**
	 * regione della texture
	 */
	@Bind
	public TextureRegion textureRegion;

	/**
	 * texture da usare. Non può essere reso persistente
	 */
	//TODO 
	//public Texture texture;


}
