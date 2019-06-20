package sqlite.feature.pagedadapter;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.livedata.PagedLiveData;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class KriptonRecyclerViewAdapter { } /*<T, VH extends KriptonViewHolder> extends Adapter<VH> {
	
	public static class KriptonViewHolder<E> extends ViewHolder {

		public KriptonViewHolder(View itemView) {
			super(itemView);
		}
		
	}
	
	public static class ViewBuffer<E> {
		private List<E> buffer;
		private int databaseSize;

		private boolean loading = false;

		private ReentrantLock lock = new ReentrantLock();
		private PagedLiveData<List<E>> pagedResult;
		private LiveData<List<E>> result;
		
		private int size;
		private int startPosition;
		public ViewBuffer(PagedLiveData<List<E>> pagedResult, int viewPageSize, CustomDiffCallback<E> diff) {
			this.pagedResult = pagedResult;
			this.databaseSize = viewPageSize * 3;			

			this.result = Transformations.map(pagedResult, result -> {
				lock.lock();
				loading = false;
				if (startPosition == pagedResult.getOffset()) {
					System.out.println("==$$$ Value changes at " + pagedResult.getOffset());
				} else {
					System.out.println("==$$$ Load at " + pagedResult.getOffset());
				}

				diff.setOldList(this.buffer);
				diff.setIncomingList(result);
				DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diff);
				//diffResult.dispatchUpdatesTo(ca);

				startPosition = pagedResult.getOffset();
				size = pagedResult.getPageSize();
				buffer = result;

				lock.unlock();
				return result;
			});

			this.pagedResult.createPageRequestBuilder().pageSize(databaseSize).apply();
		}

		public E get(int index) {
			System.out.println(String.format("Real Index: %s, Index: %s, StartPosition: %s, Size: %s",
					index - startPosition, index, startPosition, size));

			return loadAround(index);
		}

		public LiveData<List<E>> getResult() {
			return result;
		}

		public int getSize() {
			return pagedResult.getTotalElements();
		}

		public int getTotalSize() {
			// TODO Auto-generated method stub
			return 0;
		}

		public E loadAround(int position) {
			if (position >= startPosition + (size * 2 / 3)) {
				if (!loading) {
					lock.lock();
					loading = true;
					System.out.println("==> Load " + (position - size / 3));
					pagedResult.createPageRequestBuilder().offset(position - size / 3).apply();
					lock.unlock();
				}
				return buffer.get(position - startPosition);
			} else if ((position - startPosition) <= 0) {
				int loadPos = (startPosition - (size / 3));
				if (loadPos < 0) {
					System.out.println("==> No-Load " + loadPos);
				} else {
					if (!loading) {
						System.out.println("==> Load " + loadPos);
						lock.lock();
						loading = true;
						pagedResult.createPageRequestBuilder().offset(loadPos).apply();
						lock.unlock();
					}

				}
				return buffer.get(position - startPosition);
			} else if (position - startPosition >= 0 && position - startPosition < buffer.size()) {
				return buffer.get(position - startPosition);
			} else {
				return null;
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
	
	public KriptonRecyclerViewAdapter(PagedLiveData<List<T>> pagedResult, int viewPageSize, CustomDiffCallback<T> diff) {
		this.viewBuffer = new ViewBuffer<T>(pagedResult, viewPageSize, diff);
	}

	@Override
	public int getItemCount() {
		return viewBuffer.getTotalSize();
	}

	@Override
	public void onBindViewHolder(VH holder, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VH onCreateViewHolder(ViewGroup parent, int viewType) {
		// TODO Auto-generated method stub
		return null;
	}

}**/
