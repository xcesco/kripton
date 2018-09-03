package com.abubusoft.kripton.android;

public interface PageRequest {
	void firstPage();

	int getOffset();

	int getPage();
	
	int getPageSize();
	
	void nextPage();

	void previousPage();

	void setOffset(int value);

	void setPage(int page);
	
	void setPageSize(int pageSize);
}
