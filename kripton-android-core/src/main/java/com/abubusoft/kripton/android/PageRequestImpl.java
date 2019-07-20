package com.abubusoft.kripton.android;

public class PageRequestImpl implements PageRequest {

	private int pageNumber;
	private int pageSize;

	PageRequestImpl(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	@Override
	public int getOffset() {
		return pageNumber * pageSize;
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

}