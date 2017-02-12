package bind.kripton109;

import java.io.IOException;

import org.junit.Test;

import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;

import bind.AbstractBindTypeProcessorTest;
import bind.kripton109.animations.Animation;
import bind.kripton109.animations.Err1TiledMapAnimation;
import bind.kripton109.animations.Err2TiledMapAnimation;
import bind.kripton109.animations.KeyFrame;
import bind.kripton109.animations.Parallel2Animation;
import bind.kripton109.animations.TextureAnimation;
import bind.kripton109.animations.TextureKeyFrame;
import bind.kripton109.animations.TiledMapAnimation;
import bind.kripton109.animations.Translation;
import bind.kripton109.animations.TranslationFrame;
import bind.kripton109.animations.math.Point3;
import bind.kripton109.animations.math.Vector3;
import bind.kripton109.animations.texture.TextureRegion;
import bind.kripton109.settings.ApplicationSettings;
import bind.kripton109.settings.ArgonSettings;
import bind.kripton109.settings.LoggerAppenderSettings;
import bind.kripton109.settings.LoggerSettings;
import bind.kripton109.settings.ModeType;
import bind.kripton109.settings.OpenGLSettings;
import bind.kripton109.settings.ProjectionType;
import bind.kripton109.settings.TouchSupportType;
import bind.kripton109.settings.ViewFrustumAlignType;
import bind.kripton109.settings.ViewFrustumSettings;

public class TestCompile109 extends AbstractBindTypeProcessorTest {

	@Test
	public void testCompileSettings() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(ApplicationSettings.class, ArgonSettings.class, LoggerAppenderSettings.class, LoggerSettings.class, ModeType.class, OpenGLSettings.class, ProjectionType.class, TouchSupportType.class, ViewFrustumAlignType.class, ViewFrustumSettings.class);
	}
	
	@Test
	public void testCompileAnimations() throws IOException, InstantiationException, IllegalAccessException {
		buildBindProcessorTest(Animation.class, KeyFrame.class,TiledMapAnimation.class, Parallel2Animation.class, TextureAnimation.class, TextureKeyFrame.class, Translation.class, TranslationFrame.class, Point3.class, Vector3.class, TextureRegion.class);
	}
	
	@Test
	public void testErr1CompileAnimations() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(KriptonProcessorException.class);
		buildBindProcessorTest(Animation.class, KeyFrame.class,Err1TiledMapAnimation.class, Parallel2Animation.class, TextureAnimation.class, TextureKeyFrame.class, Translation.class, TranslationFrame.class, Point3.class, Vector3.class, TextureRegion.class);
	}
	
	@Test
	public void testErr2CompileAnimations() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(KriptonProcessorException.class);
		buildBindProcessorTest(Animation.class, KeyFrame.class,Err2TiledMapAnimation.class, Parallel2Animation.class, TextureAnimation.class, TextureKeyFrame.class, Translation.class, TranslationFrame.class, Point3.class, Vector3.class, TextureRegion.class);
	}


}
