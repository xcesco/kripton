package base;

import java.io.ByteArrayOutputStream;

public class MyByteArrayOutputStream extends ByteArrayOutputStream {
	public MyByteArrayOutputStream() {
	}

	public MyByteArrayOutputStream(int size) {
		super(size);
	}

	public int getCount() {
		return count;
	}

	public byte[] getBuf() {
		return buf;
	}
}