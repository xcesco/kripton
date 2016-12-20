package com.abubusoft.kripton;

public class NoneAdapter implements BindTypeAdapter<Void, Void> {

	@Override
	public Void java2data(Void java) {
		return null;
	}

	@Override
	public Void data2java(Void data) {
		return null;
	}

	@Override
	public Class<Void> getBindType() {
		return null;
	}

	@Override
	public Class<Void> getJavaType() {
		return null;
	}
	

}
