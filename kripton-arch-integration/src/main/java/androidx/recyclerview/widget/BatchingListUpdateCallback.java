package androidx.recyclerview.widget;
import androidx.annotation.NonNull;
/**
 * Wraps a {@link ListUpdateCallback} callback and batches operations that can be merged.
 * <p>
 * For instance, when 2 add operations comes that adds 2 consecutive elements,
 * BatchingListUpdateCallback merges them and calls the wrapped callback only once.
 * <p>
 * This is a general purpose class and is also used by
 * {@link DiffUtil.DiffResult DiffResult} and
 * {@link SortedList} to minimize the number of updates that are dispatched.
 * <p>
 * If you use this class to batch updates, you must call {@link #dispatchLastEvent()} when the
 * stream of update events drain.
 */
public class BatchingListUpdateCallback implements ListUpdateCallback {
    private static final int TYPE_NONE = 0;
    private static final int TYPE_ADD = 1;
    private static final int TYPE_REMOVE = 2;
    private static final int TYPE_CHANGE = 3;
    final ListUpdateCallback mWrapped;
    int mLastEventType = TYPE_NONE;
    int mLastEventPosition = -1;
    int mLastEventCount = -1;
    Object mLastEventPayload = null;
    public BatchingListUpdateCallback(@NonNull ListUpdateCallback callback) {
        mWrapped = callback;
    }
    /**
     * BatchingListUpdateCallback holds onto the last event to see if it can be merged with the
     * next one. When stream of events finish, you should call this method to dispatch the last
     * event.
     */
    public void dispatchLastEvent() {
        if (mLastEventType == TYPE_NONE) {
            return;
        }
        switch (mLastEventType) {
            case TYPE_ADD:
                mWrapped.onInserted(mLastEventPosition, mLastEventCount);
                break;
            case TYPE_REMOVE:
                mWrapped.onRemoved(mLastEventPosition, mLastEventCount);
                break;
            case TYPE_CHANGE:
                mWrapped.onChanged(mLastEventPosition, mLastEventCount, mLastEventPayload);
                break;
        }
        mLastEventPayload = null;
        mLastEventType = TYPE_NONE;
    }
    @Override
    public void onInserted(int position, int count) {
        if (mLastEventType == TYPE_ADD && position >= mLastEventPosition
                && position <= mLastEventPosition + mLastEventCount) {
            mLastEventCount += count;
            mLastEventPosition = Math.min(position, mLastEventPosition);
            return;
        }
        dispatchLastEvent();
        mLastEventPosition = position;
        mLastEventCount = count;
        mLastEventType = TYPE_ADD;
    }
    @Override
    public void onRemoved(int position, int count) {
        if (mLastEventType == TYPE_REMOVE && mLastEventPosition >= position &&
                mLastEventPosition <= position + count) {
            mLastEventCount += count;
            mLastEventPosition = position;
            return;
        }
        dispatchLastEvent();
        mLastEventPosition = position;
        mLastEventCount = count;
        mLastEventType = TYPE_REMOVE;
    }
    @Override
    public void onMoved(int fromPosition, int toPosition) {
        dispatchLastEvent(); // moves are not merged
        mWrapped.onMoved(fromPosition, toPosition);
    }
    @Override
    public void onChanged(int position, int count, Object payload) {
        if (mLastEventType == TYPE_CHANGE &&
                !(position > mLastEventPosition + mLastEventCount
                        || position + count < mLastEventPosition || mLastEventPayload != payload)) {
            // take potential overlap into account
            int previousEnd = mLastEventPosition + mLastEventCount;
            mLastEventPosition = Math.min(position, mLastEventPosition);
            mLastEventCount = Math.max(previousEnd, position + count) - mLastEventPosition;
            return;
        }
        dispatchLastEvent();
        mLastEventPosition = position;
        mLastEventCount = count;
        mLastEventPayload = payload;
        mLastEventType = TYPE_CHANGE;
    }
}