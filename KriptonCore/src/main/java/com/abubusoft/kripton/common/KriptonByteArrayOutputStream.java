package com.abubusoft.kripton.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import com.abubusoft.kripton.exception.KriptonRuntimeException;

/**
 * Acts as a ByteArrayOutputStream, but allow direct access to array of byte
 * inside, without copy it.
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public class KriptonByteArrayOutputStream extends ByteArrayOutputStream {
	public KriptonByteArrayOutputStream() {
	}

	public KriptonByteArrayOutputStream(int size) {
		super(size);
	}

	public int getCount() {
		return count;
	}

	public byte[] getByteBuffer() {
		return buf;
	}

	public byte[] getByteBufferCopy() {
		return Arrays.copyOf(buf, count);
	}

	@Override
	public void close() {
		try {
			super.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw (new KriptonRuntimeException(e));
		}
	}
}