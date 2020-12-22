/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package bind.feature.generichierarchy.kripton109.settings;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;



/**
 * The Class ViewFrustumSettings.
 */
@BindType
public class ViewFrustumSettings {
	
	/** The align. */
	@Bind("viewFrustumAlign")
	public ViewFrustumAlignType align = ViewFrustumAlignType.HEIGHT_ALIGN;

	/** The field of view. */
	@Bind("viewFrustumFieldOfView")
	public float fieldOfView = 30f;

	/** The projection. */
	@Bind("viewFrustumProjection")
	public ProjectionType projection = ProjectionType.PERSPECTIVE;

	/** The z near. */
	@Bind("viewFrustumZNear")
	public float zNear = 1.0f;

	/** The z far. */
	@Bind("viewFrustumZFar")
	public float zFar = 1000f;

	/** The size. */
	@Bind("viewFrustumSize")
	public float size = 1000f;
}
