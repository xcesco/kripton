package com.abubusoft.kripton.android;

public interface PageRequest {
	void setPage(int page);

	int getPage();

	int getPageSize();

	void nextPage();

	void previousPage();

	void firstPage();
}
