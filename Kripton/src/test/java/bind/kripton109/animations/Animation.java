package bind.kripton109.animations;

import java.util.ArrayList;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindXml;

public class Animation<K extends KeyFrame> {

	@Bind(order = 4)
	@BindXml(elementTag = "frame")
	public ArrayList<K> frames;

	@Bind(order = 1)
	protected boolean loop = false;

	@Bind(order = 0)
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Bind(order = 3)
	protected float rate = 1.0f;

	public float getRate() {
		return rate;
	}

	public Animation() {
		frames = new ArrayList<K>();
		rate = 1.0f;
		loop = false;
	}

	public Animation(ArrayList<K> values) {
		frames = values;
		rate = 1.0f;
		loop = false;
	}

	public int add(K frame) {
		// se non abbiamo un nome, lo aggiungiamo di default
		if (frame.name == null) {
			frame.name = "keyframe" + frames.size();
		}
		frames.add(frame);

		return frames.size() - 1;
	}

	public long duration() {
		long duration = 0;
		int n = frames.size();
		for (int i = 0; i < n; i++) {
			duration += frames.get(i).duration;
		}

		return (long) (duration * rate);
	}

	public K getFrame(int index) {
		return frames.get(index);
	}

	public boolean isLoop() {
		return loop;
	}

	public void setInterval(K firstFrame, K secondFrame) {
		frames.clear();
		add(firstFrame);
		add(secondFrame);
	}

	public void setLoop(boolean value) {
		loop = value;
	}

	public void setRate(float value) {
		rate = value;
	}

	public int size() {
		return frames.size();
	}

}
