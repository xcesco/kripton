package base;

import java.io.ByteArrayOutputStream;

import edu.emory.mathcs.backport.java.util.Arrays;

public class MyByteArrayOutputStream extends ByteArrayOutputStream {
	public MyByteArrayOutputStream() {
	}

	public MyByteArrayOutputStream(int size) {
		super(size);
	}

	public int getCount() {
		return count;
	}

	public byte[] getByteBuffer() {
		return buf;
	}
	
	public byte[] getByteBufferCopy() {
		return Arrays.copyOf(buf, buf.length);
	}
}