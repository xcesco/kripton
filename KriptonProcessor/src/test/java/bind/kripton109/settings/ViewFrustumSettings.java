package bind.kripton109.settings;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

/**
 * <p>Impostazioni del frustum.</p>
 * 
 * <p>In order to visualize a scene from different angles a virtual camera is often
 * used. The virtual camera setup, commonly done with gluPerspective and
 * gluLookAt functions, determines what is visible on screen.</p>
 * 
 * <p>The view frustum is the volume that contains everything that is potentially
 * (there may be occlusions) visible on the screen. This volume is defined
 * according to the camera’s settings, and when using a perspective projection
 * takes the shape of a truncated pyramid.</p>
 * 
 * <img src="doc-files/vf.gif"/>
 * 
 * <p></p>
 * 
 * @author Francesco Benincasa
 * 
 */
@BindType
public class ViewFrustumSettings {
	/**
	 * Definisce quale lato della view la camera deve ricoprire: altezza o
	 * larghezza.
	 */
	@Bind("viewFrustumAlign")
	public ViewFrustumAlignType align = ViewFrustumAlignType.HEIGHT_ALIGN;

	/**
	 * field of view
	 */
	@Bind("viewFrustumFieldOfView")
	public float fieldOfView = 30f;

	@Bind("viewFrustumProjection")
	public ProjectionType projection = ProjectionType.PERSPECTIVE;

	/**
	 * distanza del piano più vicino rispetto alla camera del frustum
	 */
	@Bind("viewFrustumZNear")
	public float zNear = 1.0f;

	/**
	 * distanza del piano più lontano del frustum rispetto alla camera
	 */
	@Bind("viewFrustumZFar")
	public float zFar = 1000f;

	/**
	 * dimensione della camera. Utile solo nel caso di proiezione ortogonale
	 */
	@Bind("viewFrustumSize")
	public float size = 1000f;
}
