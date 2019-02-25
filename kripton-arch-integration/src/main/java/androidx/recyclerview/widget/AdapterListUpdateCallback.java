package androidx.recyclerview.widget;

import androidx.annotation.NonNull;

/**
 * ListUpdateCallback that dispatches update events to the given adapter.
 *
 * @see DiffUtil.DiffResult#dispatchUpdatesTo(RecyclerView.Adapter)
 */
public final class AdapterListUpdateCallback implements ListUpdateCallback {
	@NonNull
	private final RecyclerView.Adapter mAdapter;

	/**
	 * Creates an AdapterListUpdateCallback that will dispatch update events to the given adapter.
	 *
	 * @param adapter
	 *            The Adapter to send updates to.
	 */
	public AdapterListUpdateCallback(@NonNull RecyclerView.Adapter adapter) {
		mAdapter = adapter;
	}

	/** {@inheritDoc} */
	@Override
	public void onInserted(int position, int count) {
		mAdapter.notifyItemRangeInserted(position, count);
	}

	/** {@inheritDoc} */
	@Override
	public void onRemoved(int position, int count) {
		mAdapter.notifyItemRangeRemoved(position, count);
	}

	/** {@inheritDoc} */
	@Override
	public void onMoved(int fromPosition, int toPosition) {
		mAdapter.notifyItemMoved(fromPosition, toPosition);
	}

	/** {@inheritDoc} */
	@Override
	public void onChanged(int position, int count, Object payload) {
		mAdapter.notifyItemRangeChanged(position, count, payload);
	}
}