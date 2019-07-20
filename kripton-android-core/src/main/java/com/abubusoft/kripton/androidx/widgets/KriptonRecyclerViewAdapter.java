package com.abubusoft.kripton.androidx.widgets;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.PageChunk;
import com.abubusoft.kripton.androidx.livedata.PagedLiveData;

import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class KriptonRecyclerViewAdapter<T, VH extends KriptonViewHolder<T>> extends Adapter<VH> {

	public interface OnLoadingListener {
		void onChangeStatus(boolean loading, int position, int lenght, int total);
	}

	private OnLoadingListener loadingListener;

	// protected PagedLiveData<List<T>> pagedResult;

	public KriptonRecyclerViewAdapter(LifecycleOwner context, PagedLiveData<List<T>> pagedResult, CustomDiffCallback<T> diff, OnLoadingListener loadingListener) {
		// this.pagedResult = pagedResult;
		this.viewBuffer = new ViewBuffer<T>(context, pagedResult, diff);
		this.loadingListener = loadingListener;
		// this.registerAdapterDataObserver(ca);
	}

	public class ViewBuffer<E> {
		private Map<Integer, PageChunk<E>> chunks;

		private int pageSize;

		private final int lowerLimit;
		private final int upperLimit;

		private PublishSubject<Integer> subject;
		private int positionMinToNotify;
		private int positionMaxToNotify;

		private AtomicBoolean loading = new AtomicBoolean(false);

		// private ReentrantLock lock = new ReentrantLock();
		private PagedLiveData<List<E>> pagedResult;

		private int lastPositionInvoked;

		public ViewBuffer(LifecycleOwner context, PagedLiveData<List<E>> pagedResult, CustomDiffCallback<E> diff) {
			this.pagedResult = pagedResult;
			this.chunks = new MaxSizeHashMap<>(4);
			this.pageSize = pagedResult.getPageSize();
			this.loading.set(true);
			lowerLimit = Math.round(pageSize * 0.3333f);
			upperLimit = Math.round(pageSize * 0.6666f);

			//@formatter:off
			this.subject = PublishSubject.create();
			subject
				.observeOn(Schedulers.from(KriptonLibrary.getExecutorService()))
				.doOnNext(System.out::println)
				.filter(page -> {
					boolean f=!chunks.containsKey(page);
					//System.out.println("================= "+f);
					return f;
				})
				.map(page -> new PageChunk<E>(page, pagedResult.getExecutor().execute(page, pagedResult.getPageSize())))
				.subscribe(chunk -> {
					chunks.put(chunk.getPageNumber(), chunk);
					loading.set(false);
					
					//notifyItemRangeChanged(chunk.getPageNumber() * pageSize, chunk.getData().size());
					if (positionMinToNotify<=positionMaxToNotify) {
						notifyItemRangeChanged(positionMinToNotify, positionMaxToNotify-positionMinToNotify+1);
					}
					positionMinToNotify=pagedResult.getTotalElements();
					positionMaxToNotify=0;
					
					if (loadingListener != null) {
						loadingListener.onChangeStatus(false, chunk.getPageNumber(), chunk.getData().size(), pagedResult.getTotalElements());
					}
				});
			//@formatter:on

			if (context == null) {
				pagedResult.observeForever(data -> {
					chunks.clear();
					chunks.put(0, new PageChunk<E>(0, data));
					notifyItemRangeChanged(0, data.size());
					if (loadingListener != null) {
						loadingListener.onChangeStatus(false, 0, data.size(), pagedResult.getTotalElements());
					}

					System.out.println(String.format(" ::::::::: observable %s total %s", data.size(), pagedResult.getTotalElements()));
					this.loading.set(false);
					if (lastPositionInvoked != 0) {
						loadAround(lastPositionInvoked);
					}
				});
			} else {
				pagedResult.observe(context, data -> {
					chunks.clear();
					chunks.put(0, new PageChunk<E>(0, data));
					notifyItemRangeChanged(0, data.size());
					if (loadingListener != null) {
						loadingListener.onChangeStatus(false, 0, data.size(), pagedResult.getTotalElements());
					}

					System.out.println(String.format(" ::::::::: observable %s total %s", data.size(), pagedResult.getTotalElements()));
					this.loading.set(false);
					if (lastPositionInvoked != 0) {
						loadAround(lastPositionInvoked);
					}
				});
			}

		}

		int getPage(int index) {
			return index / pageSize;
		}

		int nextPage(int index) {
			return normalizePage(getPage(index) + 1);
		}

		int previousPage(int index) {
			return normalizePage(getPage(index) - 1);
		}

		int normalizePage(int pageIndex) {
			if (pageIndex < 0)
				return 0;
			if (pageIndex >= pagedResult.getTotalPages())
				return pagedResult.getTotalPages() - 1;

			return pageIndex;
		}

		public E get(int position) {
			return loadAround(position);
		}

		public int getTotalSize() {
			return pagedResult.getTotalElements();
		}

		protected E loadAround(int position) {
			lastPositionInvoked = position;
			if (!loading.get()) {
				int startPosition = position - (position % pageSize);
				int lower = startPosition + lowerLimit;
				int upper = startPosition + upperLimit;
				System.out.println(String.format("Position %s, lower: %s, upper: %s", position, lower, upper));
				if (position >= upper) {
					int page = nextPage(position);
					loadPage(position, page);
				} else if (position <= lower) {
					int page = previousPage(position);
					loadPage(position, page);
				} else {
					System.out.println(String.format("Nothing to load for position: %s", position));
				}
			}

			int page = getPage(position);
			PageChunk<E> currentChunk = chunks.get(page);
			System.out.println(String.format("Position: %s, Index: %s, Chunk: %s", position, position - page * pageSize, page));

			if (currentChunk != null) {
				return currentChunk.getData().get(position - page * pageSize);
			} else {
				positionMinToNotify = Math.min(positionMinToNotify, position);
				positionMaxToNotify = Math.max(positionMaxToNotify, position);
				return null;
			}

		}

		protected void loadPage(int position, int page) {
			// load only different page
			if (getPage(position) != page && !this.chunks.containsKey(page)) {
				// we need to load it
				loading.set(true);

				if (loadingListener != null) {
					loadingListener.onChangeStatus(true, page, -1, pagedResult.getTotalElements());
				}

				System.out.println("==> preload " + page);
				subject.onNext(page);
			}
		}
	}

	final ListUpdateCallback ca = new ListUpdateCallback() {

		@Override
		public void onChanged(int position, int count, Object payload) {
			Logger.info("@@@@@ onChanged  " + position + " count " + count);
		}

		@Override
		public void onInserted(int position, int count) {
			Logger.info("@@@@@ onInserted " + position + " count " + count);
		}

		@Override
		public void onMoved(int fromPosition, int toPosition) {
			Logger.info("@@@@@ onMoved " + fromPosition + " to " + toPosition);
		}

		@Override
		public void onRemoved(int position, int count) {
			Logger.info("@@@@@ onRemoved " + position + " count " + count);
		}

	};

	protected ViewBuffer<T> viewBuffer;

	@Override
	public int getItemCount() {
		return viewBuffer.getTotalSize();
	}

	public T getItem(int position) {
		return viewBuffer.get(position);
	}

	@Override
	public void onBindViewHolder(VH holder, int position) {

	}

	@Override
	public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		return null;
	}

}
