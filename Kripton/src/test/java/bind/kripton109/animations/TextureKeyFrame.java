package bind.kripton109.animations;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

import bind.kripton109.animations.texture.TextureRegion;

@BindType
public class TextureKeyFrame extends KeyFrame {

	public TextureKeyFrame() {
	}
			
	@Bind
	public TextureRegion textureRegion;

}
