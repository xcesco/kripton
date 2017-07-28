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
package bind.feature.generichierarchy.kripton109;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;

import bind.AbstractBindTypeProcessorTest;
import bind.feature.generichierarchy.kripton109.animations.Animation;
import bind.feature.generichierarchy.kripton109.animations.Err1TiledMapAnimation;
import bind.feature.generichierarchy.kripton109.animations.Err2TiledMapAnimation;
import bind.feature.generichierarchy.kripton109.animations.KeyFrame;
import bind.feature.generichierarchy.kripton109.animations.MockAnimation;
import bind.feature.generichierarchy.kripton109.animations.MockKeyFrame;
import bind.feature.generichierarchy.kripton109.animations.MockParallel2;
import bind.feature.generichierarchy.kripton109.animations.Parallel2Animation;
import bind.feature.generichierarchy.kripton109.animations.TextureAnimation;
import bind.feature.generichierarchy.kripton109.animations.TextureKeyFrame;
import bind.feature.generichierarchy.kripton109.animations.TiledMapAnimation;
import bind.feature.generichierarchy.kripton109.animations.Translation;
import bind.feature.generichierarchy.kripton109.animations.TranslationFrame;
import bind.feature.generichierarchy.kripton109.animations.math.Point3;
import bind.feature.generichierarchy.kripton109.animations.math.Vector3;
import bind.feature.generichierarchy.kripton109.animations.texture.TextureRegion;
import bind.feature.generichierarchy.kripton109.settings.ApplicationSettings;
import bind.feature.generichierarchy.kripton109.settings.ArgonSettings;
import bind.feature.generichierarchy.kripton109.settings.LoggerAppenderSettings;
import bind.feature.generichierarchy.kripton109.settings.LoggerSettings;
import bind.feature.generichierarchy.kripton109.settings.ModeType;
import bind.feature.generichierarchy.kripton109.settings.OpenGLSettings;
import bind.feature.generichierarchy.kripton109.settings.ProjectionType;
import bind.feature.generichierarchy.kripton109.settings.TouchSupportType;
import bind.feature.generichierarchy.kripton109.settings.ViewFrustumAlignType;
import bind.feature.generichierarchy.kripton109.settings.ViewFrustumSettings;

public class TestCompile109 extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompileSettings() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(ApplicationSettings.class, ArgonSettings.class, LoggerAppenderSettings.class, LoggerSettings.class, ModeType.class, OpenGLSettings.class, ProjectionType.class,
				TouchSupportType.class, ViewFrustumAlignType.class, ViewFrustumSettings.class);
	}

	@Test
	public void testCompileAnimations() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Animation.class, MockAnimation.class, MockKeyFrame.class, MockParallel2.class, KeyFrame.class, TiledMapAnimation.class, Parallel2Animation.class, TextureAnimation.class,
				TextureKeyFrame.class, Translation.class, TranslationFrame.class, Point3.class, Vector3.class, TextureRegion.class);
	}

	@Test
	public void testErr1CompileAnimations() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(KriptonProcessorException.class);
		buildBindProcessorTest(Animation.class, KeyFrame.class, Err1TiledMapAnimation.class, Parallel2Animation.class, TextureAnimation.class, TextureKeyFrame.class, Translation.class,
				TranslationFrame.class, Point3.class, Vector3.class, TextureRegion.class);
	}

	@Test
	public void testErr2CompileAnimations() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(KriptonProcessorException.class);
		buildBindProcessorTest(Animation.class, KeyFrame.class, Err2TiledMapAnimation.class, Parallel2Animation.class, TextureAnimation.class, TextureKeyFrame.class, Translation.class,
				TranslationFrame.class, Point3.class, Vector3.class, TextureRegion.class);
	}

}
