package com.abubusoft.kripton.persistence;

import java.io.Closeable;

public interface SerializerWrapper extends Closeable {

	void close();

}
