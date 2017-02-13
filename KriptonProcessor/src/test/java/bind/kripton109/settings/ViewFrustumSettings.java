package bind.kripton109.settings;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class ViewFrustumSettings {
	@Bind("viewFrustumAlign")
	public ViewFrustumAlignType align = ViewFrustumAlignType.HEIGHT_ALIGN;

	@Bind("viewFrustumFieldOfView")
	public float fieldOfView = 30f;

	@Bind("viewFrustumProjection")
	public ProjectionType projection = ProjectionType.PERSPECTIVE;

	@Bind("viewFrustumZNear")
	public float zNear = 1.0f;

	@Bind("viewFrustumZFar")
	public float zFar = 1000f;

	@Bind("viewFrustumSize")
	public float size = 1000f;
}
