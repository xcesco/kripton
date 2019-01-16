package com.abubusoft.kripton.androidx.livedata;

import java.util.Collections;
import java.util.List;

import android.arch.paging.DataSource;
import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import androidx.lifecycle.Observer;

/**
 * A {@link PositionalDataSource} that loads entities based on an ObjectBox
 * {@link Query} using offset and limit to implement paging support. The data
 * source is invalidated if the query results change.
 */
public class KriptonXDataSource<T> extends PositionalDataSource<T> {

	private final PagedLiveData<T> query;

	private final Observer<T> observer;

	public static class Factory<Item> extends DataSource.Factory<Integer, Item> {

		private final PagedLiveData<Item> query;

		public Factory(PagedLiveData<Item> query) {
			this.query = query;
		}

		@Override
		public KriptonXDataSource<Item> create() {
			return new KriptonXDataSource<>(query);
		}
	}

	public KriptonXDataSource(PagedLiveData<T> query) {
		this.query = query;
		
		observer = new Observer<T>() {

			@Override
			public void onChanged(T t) {
				invalidate();

			}
		};

		query.observeForever(observer);
		// observer will be automatically removed once GC'ed
		// query. subscribe().onlyChanges().weak().observer(observer);
	}

	@Override
	public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<T> callback) {
		// note: limiting to int should be fine for Android apps
		// TODO ripristinare query.count();
		int totalCount = query.getTotalElements();
		if (totalCount == 0) {
			callback.onResult(Collections.<T>emptyList(), 0, 0);
			return;
		}

		int position = computeInitialLoadPosition(params, totalCount);
		int loadSize = computeInitialLoadSize(params, position, totalCount);

		List<T> list = loadRange(position, loadSize);
		if (list.size() == loadSize) {
			callback.onResult(list, position, totalCount);
		} else {
			invalidate(); // size doesn't match request - DB modified between
							// count and load
		}
	}

	@Override
	public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<T> callback) {
		callback.onResult(loadRange(params.startPosition, params.loadSize));
	}

	private List<T> loadRange(int startPosition, int loadCount) {
		/* private void loadRange(int startPosition, int loadCount) { */
		// note: find interprets loadCount 0 as no limit
		//query.moveTo(startPosition, loadCount);
		
		query.createPageRequestBuilder().offset(startPosition).pageSize(loadCount).apply();
		
		return null;
	}

}