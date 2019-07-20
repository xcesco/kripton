package com.abubusoft.kripton.android;


import java.util.List;

public class PageChunk<E> implements Comparable<PageChunk<E>> {
	private int pageNumber;

	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pageNumber;
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageChunk other = (PageChunk) obj;
		if (pageNumber != other.pageNumber)
			return false;
		return true;
	}

	public List<E> getData() {
		return data;
	}

	private List<E> data;

	public PageChunk(int pageNumber, List<E> data) {
		this.pageNumber = pageNumber;
		this.data = data;
	}

	@Override
	public int compareTo(PageChunk<E> o) {
		return this.pageNumber - o.pageNumber;
	}
}
