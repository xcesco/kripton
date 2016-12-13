package com.abubusoft.kripton.binder.persistence;

import java.io.Closeable;

public interface SerializerWrapper extends Closeable {

	void close();

}
