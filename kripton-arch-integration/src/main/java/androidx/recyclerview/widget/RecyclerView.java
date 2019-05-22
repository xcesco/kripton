package androidx.recyclerview.widget;

/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.FocusFinder;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * A flexible view for providing a limited window into a large data set.
 *
 * <h3>Glossary of terms:</h3>
 *
 * <ul>
 *     <li><em>Adapter:</em> A subclass of {@link Adapter} responsible for providing views
 *     that represent items in a data set.</li>
 *     <li><em>Position:</em> The position of a data item within an <em>Adapter</em>.</li>
 *     <li><em>Index:</em> The index of an attached child view as used in a call to
 *     {@link ViewGroup#getChildAt}. Contrast with <em>Position.</em></li>
 *     <li><em>Binding:</em> The process of preparing a child view to display data corresponding
 *     to a <em>position</em> within the adapter.</li>
 *     <li><em>Recycle (view):</em> A view previously used to display data for a specific adapter
 *     position may be placed in a cache for later reuse to display the same type of data again
 *     later. This can drastically improve performance by skipping initial layout inflation
 *     or construction.</li>
 *     <li><em>Scrap (view):</em> A child view that has entered into a temporarily detached
 *     state during layout. Scrap views may be reused without becoming fully detached
 *     from the parent RecyclerView, either unmodified if no rebinding is required or modified
 *     by the adapter if the view was considered <em>dirty</em>.</li>
 *     <li><em>Dirty (view):</em> A child view that must be rebound by the adapter before
 *     being displayed.</li>
 * </ul>
 *
 * <h3>Positions in RecyclerView:</h3>
 * <p>
 * RecyclerView introduces an additional level of abstraction between the {@link Adapter} and
 * {@link LayoutManager} to be able to detect data set changes in batches during a layout
 * calculation. This saves LayoutManager from tracking adapter changes to calculate animations.
 * It also helps with performance because all view bindings happen at the same time and unnecessary
 * bindings are avoided.
 * <p>
 * For this reason, there are two types of <code>position</code> related methods in RecyclerView:
 * <ul>
 *     <li>layout position: Position of an item in the latest layout calculation. This is the
 *     position from the LayoutManager's perspective.</li>
 *     <li>adapter position: Position of an item in the adapter. This is the position from
 *     the Adapter's perspective.</li>
 * </ul>
 * <p>
 * These two positions are the same except the time between dispatching <code>adapter.notify*
 * </code> events and calculating the updated layout.
 * <p>
 * Methods that return or receive <code>*LayoutPosition*</code> use position as of the latest
 * layout calculation (e.g. {@link ViewHolder#getLayoutPosition()},
 * {@link #findViewHolderForLayoutPosition(int)}). These positions include all changes until the
 * last layout calculation. You can rely on these positions to be consistent with what user is
 * currently seeing on the screen. For example, if you have a list of items on the screen and user
 * asks for the 5<sup>th</sup> element, you should use these methods as they'll match what user
 * is seeing.
 * <p>
 * The other set of position related methods are in the form of
 * <code>*AdapterPosition*</code>. (e.g. {@link ViewHolder#getAdapterPosition()},
 * {@link #findViewHolderForAdapterPosition(int)}) You should use these methods when you need to
 * work with up-to-date adapter positions even if they may not have been reflected to layout yet.
 * For example, if you want to access the item in the adapter on a ViewHolder click, you should use
 * {@link ViewHolder#getAdapterPosition()}. Beware that these methods may not be able to calculate
 * adapter positions if {@link Adapter#notifyDataSetChanged()} has been called and new layout has
 * not yet been calculated. For this reasons, you should carefully handle {@link #NO_POSITION} or
 * <code>null</code> results from these methods.
 * <p>
 * When writing a {@link LayoutManager} you almost always want to use layout positions whereas when
 * writing an {@link Adapter}, you probably want to use adapter positions.
 * <p>
 * <h3>Presenting Dynamic Data</h3>
 * To display updatable data in a RecyclerView, your adapter needs to signal inserts, moves, and
 * deletions to RecyclerView. You can build this yourself by manually calling
 * {@code adapter.notify*} methods when content changes, or you can use one of the easier solutions
 * RecyclerView provides:
 * <p>
 * <h4>List diffing with DiffUtil</h4>
 * If your RecyclerView is displaying a list that is re-fetched from scratch for each update (e.g.
 * from the network, or from a database), {@link DiffUtil} can calculate the difference between
 * versions of the list. {@code DiffUtil} takes both lists as input and computes the difference,
 * which can be passed to RecyclerView to trigger minimal animations and updates to keep your UI
 * performant, and animations meaningful. This approach requires that each list is represented in
 * memory with immutable content, and relies on receiving updates as new instances of lists. This
 * approach is also ideal if your UI layer doesn't implement sorting, it just presents the data in
 * the order it's given.
 * <p>
 * The best part of this approach is that it extends to any arbitrary changes - item updates,
 * moves, addition and removal can all be computed and handled the same way. Though you do have
 * to keep two copies of the list in memory while diffing, and must avoid mutating them, it's
 * possible to share unmodified elements between list versions.
 * <p>
 * There are three primary ways to do this for RecyclerView. We recommend you start with
 * {@link ListAdapter}, the higher-level API that builds in {@link List} diffing on a background
 * thread, with minimal code. {@link AsyncListDiffer} also provides this behavior, but without
 * defining an Adapter to subclass. If you want more control, {@link DiffUtil} is the lower-level
 * API you can use to compute the diffs yourself. Each approach allows you to specify how diffs
 * should be computed based on item data.
 * <p>
 * <h4>List mutation with SortedList</h4>
 * If your RecyclerView receives updates incrementally, e.g. item X is inserted, or item Y is
 * removed, you can use {@link SortedList} to manage your list. You define how to order items,
 * and it will automatically trigger update signals that RecyclerView can use. SortedList works
 * if you only need to handle insert and remove events, and has the benefit that you only ever
 * need to have a single copy of the list in memory. It can also compute differences with
 * {@link SortedList#replaceAll(Object[])}, but this method is more limited than the list diffing
 * behavior above.
 * <p>
 * <h4>Paging Library</h4>
 * The <a href="https://developer.android.com/topic/libraries/architecture/paging/">Paging
 * library</a> extends the diff-based approach to additionally support paged loading. It provides
 * the {@link androidx.paging.PagedList} class that operates as a self-loading list, provided a
 * source of data like a database, or paginated network API. It provides convenient list diffing
 * support out of the box, similar to {@code ListAdapter} and {@code AsyncListDiffer}. For more
 * information about the Paging library, see the
 * <a href="https://developer.android.com/topic/libraries/architecture/paging/">library
 * documentation</a>.
 *
 * {@link androidx.recyclerview.R.attr#layoutManager}
 */
public class RecyclerView extends ViewGroup  {
    static final String TAG = "RecyclerView";
    static final boolean DEBUG = false;
    static final boolean VERBOSE_TRACING = false;
    private static final int[]  NESTED_SCROLLING_ATTRS =
            {16843830 /* android.R.attr.nestedScrollingEnabled */};
    private static final int[] CLIP_TO_PADDING_ATTR = {android.R.attr.clipToPadding};
    /**
     * On Kitkat and JB MR2, there is a bug which prevents DisplayList from being invalidated if
     * a View is two levels deep(wrt to ViewHolder.itemView). DisplayList can be invalidated by
     * setting View's visibility to INVISIBLE when View is detached. On Kitkat and JB MR2, Recycler
     * recursively traverses itemView and invalidates display list for each ViewGroup that matches
     * this criteria.
     */
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST = Build.VERSION.SDK_INT == 18
            || Build.VERSION.SDK_INT == 19 || Build.VERSION.SDK_INT == 20;
    /**
     * On M+, an unspecified measure spec may include a hint which we can use. On older platforms,
     * this value might be garbage. To save LayoutManagers from it, RecyclerView sets the size to
     * 0 when mode is unspecified.
     */
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = Build.VERSION.SDK_INT >= 23;
    static final boolean POST_UPDATES_ON_ANIMATION = Build.VERSION.SDK_INT >= 16;
    /**
     * On L+, with RenderThread, the UI thread has idle time after it has passed a frame off to
     * RenderThread but before the next frame begins. We schedule prefetch work in this window.
     */
    static final boolean ALLOW_THREAD_GAP_WORK = Build.VERSION.SDK_INT >= 21;
    /**
     * FocusFinder#findNextFocus is broken on ICS MR1 and older for View.FOCUS_BACKWARD direction.
     * We convert it to an absolute direction such as FOCUS_DOWN or FOCUS_LEFT.
     */
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION = Build.VERSION.SDK_INT <= 15;
    /**
     * on API 15-, a focused child can still be considered a focused child of RV even after
     * it's being removed or its focusable flag is set to false. This is because when this focused
     * child is detached, the reference to this child is not removed in clearFocus. API 16 and above
     * properly handle this case by calling ensureInputFocusOnFirstFocusable or rootViewRequestFocus
     * to request focus on a new child, which will clear the focus on the old (detached) child as a
     * side-effect.
     */
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD = Build.VERSION.SDK_INT <= 15;
   
    public @interface Orientation {}
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;
    static final int DEFAULT_ORIENTATION = VERTICAL;
    public static final int NO_POSITION = -1;
    public static final long NO_ID = -1;
    public static final int INVALID_TYPE = -1;
    /**
     * Constant for use with {@link #setScrollingTouchSlop(int)}. Indicates
     * that the RecyclerView should use the standard touch slop for smooth,
     * continuous scrolling.
     */
    public static final int TOUCH_SLOP_DEFAULT = 0;
    /**
     * Constant for use with {@link #setScrollingTouchSlop(int)}. Indicates
     * that the RecyclerView should use the standard touch slop for scrolling
     * widgets that snap to a page or other coarse-grained barrier.
     */
    public static final int TOUCH_SLOP_PAGING = 1;
    static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
    static final int MAX_SCROLL_DURATION = 2000;
    /**
     * RecyclerView is calculating a scroll.
     * If there are too many of these in Systrace, some Views inside RecyclerView might be causing
     * it. Try to avoid using EditText, focusable views or handle them with care.
     */
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    /**
     * OnLayout has been called by the View system.
     * If this shows up too many times in Systrace, make sure the children of RecyclerView do not
     * update themselves directly. This will cause a full re-layout but when it happens via the
     * Adapter notifyItemChanged, RecyclerView can avoid full layout calculation.
     */
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    /**
     * NotifyDataSetChanged or equal has been called.
     * If this is taking a long time, try sending granular notify adapter changes instead of just
     * calling notifyDataSetChanged or setAdapter / swapAdapter. Adding stable ids to your adapter
     * might help.
     */
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    /**
     * RecyclerView is doing a layout for partial adapter updates (we know what has changed)
     * If this is taking a long time, you may have dispatched too many Adapter updates causing too
     * many Views being rebind. Make sure all are necessary and also prefer using notify*Range
     * methods.
     */
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    /**
     * RecyclerView is rebinding a View.
     * If this is taking a lot of time, consider optimizing your layout or make sure you are not
     * doing extra operations in onBindViewHolder call.
     */
    static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    /**
     * RecyclerView is attempting to pre-populate off screen views.
     */
    static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    /**
     * RecyclerView is attempting to pre-populate off screen itemviews within an off screen
     * RecyclerView.
     */
    static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
    /**
     * RecyclerView is creating a new View.
     * If too many of these present in Systrace:
     * - There might be a problem in Recycling (e.g. custom Animations that set transient state and
     * prevent recycling or ItemAnimator not implementing the contract properly. ({@link
     * > Adapter#onFailedToRecycleView(ViewHolder)})
     *
     * - There might be too many item view types.
     * > Try merging them
     *
     * - There might be too many itemChange animations and not enough space in RecyclerPool.
     * >Try increasing your pool size and item cache size.
     */
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE =
            new Class[]{Context.class, AttributeSet.class, int.class, int.class};
    private final RecyclerViewDataObserver mObserver = new RecyclerViewDataObserver();
    final Recycler mRecycler = new Recycler();
 
    /**
     * Prior to L, there is no way to query this variable which is why we override the setter and
     * track it here.
     */
    boolean mClipToPadding;
    /**
     * Note: this Runnable is only ever posted if:
     * 1) We've been through first layout
     * 2) We know we have a fixed size (mHasFixedSize)
     * 3) We're attached
     */
    final Runnable mUpdateChildViewsRunnable = new Runnable() {
        @Override
        public void run() {
            if (!mFirstLayoutComplete || isLayoutRequested()) {
                // a layout request will happen, we should not do layout here.
                return;
            }
            if (!mIsAttached) {
                requestLayout();
                // if we are not attached yet, mark us as requiring layout and skip
                return;
            }
            if (mLayoutSuppressed) {
                mLayoutWasDefered = true;
                return; //we'll process updates when ice age ends.
            }
            consumePendingUpdateOperations();
        }
    };
    final Rect mTempRect = new Rect();
    private final Rect mTempRect2 = new Rect();
    final RectF mTempRectF = new RectF();
    Adapter mAdapter;
    RecyclerListener mRecyclerListener;
    final ArrayList<ItemDecoration> mItemDecorations = new ArrayList<>();
    private final ArrayList<OnItemTouchListener> mOnItemTouchListeners =
            new ArrayList<>();
    private OnItemTouchListener mInterceptingOnItemTouchListener;
    boolean mIsAttached;
    boolean mHasFixedSize;
    boolean mEnableFastScroller;
    boolean mFirstLayoutComplete;
    /**
     * The current depth of nested calls to {@link #startInterceptRequestLayout()} (number of
     * calls to {@link #startInterceptRequestLayout()} - number of calls to
     * {@link #stopInterceptRequestLayout(boolean)} .  This is used to signal whether we
     * should defer layout operations caused by layout requests from children of
     * {@link RecyclerView}.
     */
    private int mInterceptRequestLayoutDepth = 0;
    /**
     * True if a call to requestLayout was intercepted and prevented from executing like normal and
     * we plan on continuing with normal execution later.
     */
    boolean mLayoutWasDefered;
    boolean mLayoutSuppressed;
    private boolean mIgnoreMotionEventTillDown;
    // binary OR of change events that were eaten during a layout or scroll.
    private int mEatenAccessibilityChangeFlags;
    boolean mAdapterUpdateDuringMeasure;
    private final AccessibilityManager mAccessibilityManager;
    private List<OnChildAttachStateChangeListener> mOnChildAttachStateListeners;
    /**
     * True after an event occurs that signals that the entire data set has changed. In that case,
     * we cannot run any animations since we don't know what happened until layout.
     *
     * Attached items are invalid until next layout, at which point layout will animate/replace
     * items as necessary, building up content from the (effectively) new adapter from scratch.
     *
     * Cached items must be discarded when setting this to true, so that the cache may be freely
     * used by prefetching until the next layout occurs.
     *
     * @see #processDataSetCompletelyChanged(boolean)
     */
    boolean mDataSetHasChangedAfterLayout = false;
    /**
     * True after the data set has completely changed and
     * {@link LayoutManager#onItemsChanged(RecyclerView)} should be called during the subsequent
     * measure/layout.
     *
     * @see #processDataSetCompletelyChanged(boolean)
     */
    boolean mDispatchItemsChangedEvent = false;
    /**
     * This variable is incremented during a dispatchLayout and/or scroll.
     * Some methods should not be called during these periods (e.g. adapter data change).
     * Doing so will create hard to find bugs so we better check it and throw an exception.
     *
     * @see #assertInLayoutOrScroll(String)
     * @see #assertNotInLayoutOrScroll(String)
     */
    private int mLayoutOrScrollCounter = 0;
    /**
     * Similar to mLayoutOrScrollCounter but logs a warning instead of throwing an exception
     * (for API compatibility).
     * <p>
     * It is a bad practice for a developer to update the data in a scroll callback since it is
     * potentially called during a layout.
     */
    private int mDispatchScrollCounter = 0;
    
    private EdgeEffect mLeftGlow, mTopGlow, mRightGlow, mBottomGlow;
    
    private static final int INVALID_POINTER = -1;
    /**
     * The RecyclerView is not currently scrolling.
     * @see #getScrollState()
     */
    public static final int SCROLL_STATE_IDLE = 0;
    /**
     * The RecyclerView is currently being dragged by outside input such as user touch input.
     * @see #getScrollState()
     */
    public static final int SCROLL_STATE_DRAGGING = 1;
    /**
     * The RecyclerView is currently animating to a final position while not under
     * outside control.
     * @see #getScrollState()
     */
    public static final int SCROLL_STATE_SETTLING = 2;
    static final long FOREVER_NS = Long.MAX_VALUE;
    // Touch/scrolling handling
    private int mScrollState = SCROLL_STATE_IDLE;
    private int mScrollPointerId = INVALID_POINTER;
    private VelocityTracker mVelocityTracker;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mLastTouchX;
    private int mLastTouchY;
    private int mTouchSlop;
    private OnFlingListener mOnFlingListener;
    private final int mMinFlingVelocity;
    private final int mMaxFlingVelocity;
    // This value is used when handling rotary encoder generic motion events.
    private float mScaledHorizontalScrollFactor = Float.MIN_VALUE;
    private float mScaledVerticalScrollFactor = Float.MIN_VALUE;
    private boolean mPreserveFocusAfterLayout = true;
    final State mState = new State();
    private OnScrollListener mScrollListener;
    private List<OnScrollListener> mScrollListeners;
    // For use in item animations
    boolean mItemsAddedOrRemoved = false;
    boolean mItemsChanged = false;
    boolean mPostedAnimatorRunner = false;
    
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    // simple array to keep min and max child position during a layout calculation
    // preserved not to create a new one in each layout pass
    private final int[] mMinMaxLayoutPositions = new int[2];
    
    private final int[] mScrollOffset = new int[2];
    private final int[] mNestedOffsets = new int[2];
    // Reusable int array to be passed to method calls that mutate it in order to "return" two ints.
    final int[] mReusableIntPair = new int[2];
    static final Interpolator sQuinticInterpolator = new Interpolator() {
        @Override
        public float getInterpolation(float t) {
            t -= 1.0f;
            return t * t * t * t * t + 1.0f;
        }
    };
    public RecyclerView( Context context) {
        this(context, null);
    }
    public RecyclerView( Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public RecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, CLIP_TO_PADDING_ATTR, defStyle, 0);
            mClipToPadding = a.getBoolean(0, true);
            a.recycle();
        } else {
            mClipToPadding = true;
        }
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        final ViewConfiguration vc = ViewConfiguration.get(context);
        mTouchSlop = vc.getScaledTouchSlop();
        mMinFlingVelocity = vc.getScaledMinimumFlingVelocity();
        mMaxFlingVelocity = vc.getScaledMaximumFlingVelocity();
        setWillNotDraw(getOverScrollMode() == View.OVER_SCROLL_NEVER);
        
        initAdapterManager();
        initChildrenHelper();
        initAutofill();
        // If not explicitly specified this view is important for accessibility.
        mAccessibilityManager = (AccessibilityManager) getContext()
                .getSystemService(Context.ACCESSIBILITY_SERVICE);
        // Create the layoutManager if specified.
        boolean nestedScrollingEnabled = true;
        // Re-set whether nested scrolling is enabled so that it is set on all API levels
        setNestedScrollingEnabled(nestedScrollingEnabled);
    }
    /**
     * Label appended to all public exception strings, used to help find which RV in an app is
     * hitting an exception.
     */
    String exceptionLabel() {
        return " " + super.toString()
                + ", adapter:" + mAdapter
                + ", context:" + getContext();
    }
    /**
     * If not explicitly specified, this view and its children don't support autofill.
     * <p>
     * This is done because autofill's means of uniquely identifying views doesn't work out of the
     * box with View recycling.
     */
    @SuppressLint("InlinedApi")
    private void initAutofill() {
        
    }
    /**
     * Instantiate and set a LayoutManager, if specified in the attributes.
     */
    private void createLayoutManager(Context context, String className, AttributeSet attrs,
            int defStyleAttr, int defStyleRes) {
        if (className != null) {
            className = className.trim();
            if (!className.isEmpty()) {
                className = getFullClassName(context, className);
                
            }
        }
    }
    private String getFullClassName(Context context, String className) {
        if (className.charAt(0) == '.') {
            return context.getPackageName() + className;
        }
        if (className.contains(".")) {
            return className;
        }
        return RecyclerView.class.getPackage().getName() + '.' + className;
    }
    private void initChildrenHelper() {
       
    }
    void initAdapterManager() {
       
    }
    /**
     * RecyclerView can perform several optimizations if it can know in advance that RecyclerView's
     * size is not affected by the adapter contents. RecyclerView can still change its size based
     * on other factors (e.g. its parent's size) but this size calculation cannot depend on the
     * size of its children or contents of its adapter (except the number of items in the adapter).
     * <p>
     * If your use of RecyclerView falls into this category, set this to {@code true}. It will allow
     * RecyclerView to avoid invalidating the whole layout when its adapter contents change.
     *
     * @param hasFixedSize true if adapter changes cannot affect the size of the RecyclerView.
     */
    public void setHasFixedSize(boolean hasFixedSize) {
        mHasFixedSize = hasFixedSize;
    }
    /**
     * @return true if the app has specified that changes in adapter content cannot change
     * the size of the RecyclerView itself.
     */
    public boolean hasFixedSize() {
        return mHasFixedSize;
    }
    @Override
    public void setClipToPadding(boolean clipToPadding) {
        if (clipToPadding != mClipToPadding) {
            invalidateGlows();
        }
        mClipToPadding = clipToPadding;
        super.setClipToPadding(clipToPadding);
        if (mFirstLayoutComplete) {
            requestLayout();
        }
    }
    /**
     * Returns whether this RecyclerView will clip its children to its padding, and resize (but
     * not clip) any EdgeEffect to the padded region, if padding is present.
     * <p>
     * By default, children are clipped to the padding of their parent
     * RecyclerView. This clipping behavior is only enabled if padding is non-zero.
     *
     * @return true if this RecyclerView clips children to its padding and resizes (but doesn't
     *         clip) any EdgeEffect to the padded region, false otherwise.
     *
     * @attr name android:clipToPadding
     */
    @Override
    public boolean getClipToPadding() {
        return mClipToPadding;
    }
    /**
     * Configure the scrolling touch slop for a specific use case.
     *
     * Set up the RecyclerView's scrolling motion threshold based on common usages.
     * Valid arguments are {@link #TOUCH_SLOP_DEFAULT} and {@link #TOUCH_SLOP_PAGING}.
     *
     * @param slopConstant One of the <code>TOUCH_SLOP_</code> constants representing
     *                     the intended usage of this RecyclerView
     */
    public void setScrollingTouchSlop(int slopConstant) {
        final ViewConfiguration vc = ViewConfiguration.get(getContext());
        switch (slopConstant) {
            default:
                Log.w(TAG, "setScrollingTouchSlop(): bad argument constant "
                        + slopConstant + "; using default value");
                // fall-through
            case TOUCH_SLOP_DEFAULT:
                mTouchSlop = vc.getScaledTouchSlop();
                break;
            case TOUCH_SLOP_PAGING:
                mTouchSlop = vc.getScaledPagingTouchSlop();
                break;
        }
    }
    /**
     * Swaps the current adapter with the provided one. It is similar to
     * {@link #setAdapter(Adapter)} but assumes existing adapter and the new adapter uses the same
     * {@link ViewHolder} and does not clear the RecycledViewPool.
     * <p>
     * Note that it still calls onAdapterChanged callbacks.
     *
     * @param adapter The new adapter to set, or null to set no adapter.
     * @param removeAndRecycleExistingViews If set to true, RecyclerView will recycle all existing
     *                                      Views. If adapters have stable ids and/or you want to
     *                                      animate the disappearing views, you may prefer to set
     *                                      this to false.
     * @see #setAdapter(Adapter)
     */
    public void swapAdapter( Adapter adapter, boolean removeAndRecycleExistingViews) {
        // bail out if layout is frozen
        setLayoutFrozen(false);
        setAdapterInternal(adapter, true, removeAndRecycleExistingViews);
        processDataSetCompletelyChanged(true);
        requestLayout();
    }
    /**
     * Set a new adapter to provide child views on demand.
     * <p>
     * When adapter is changed, all existing views are recycled back to the pool. If the pool has
     * only one adapter, it will be cleared.
     *
     * @param adapter The new adapter to set, or null to set no adapter.
     * @see #swapAdapter(Adapter, boolean)
     */
    public void setAdapter( Adapter adapter) {
        // bail out if layout is frozen
        setLayoutFrozen(false);
        setAdapterInternal(adapter, false, true);
        processDataSetCompletelyChanged(false);
        requestLayout();
    }
    /**
     * Removes and recycles all views - both those currently attached, and those in the Recycler.
     */
    void removeAndRecycleViews() {
        // end all running animations
        
        // Since animations are ended, mLayout.children should be equal to
        // recyclerView.children. This may not be true if item animator's end does not work as
        // expected. (e.g. not release children instantly). It is safer to use mLayout's child
        // count.
        
        // we should clear it here before adapters are swapped to ensure correct callbacks.
        mRecycler.clear();
    }
    /**
     * Replaces the current adapter with the new one and triggers listeners.
     * @param adapter The new adapter
     * @param compatibleWithPrevious If true, the new adapter is using the same View Holders and
     *                               item types with the current adapter (helps us avoid cache
     *                               invalidation).
     * @param removeAndRecycleViews  If true, we'll remove and recycle all existing views. If
     *                               compatibleWithPrevious is false, this parameter is ignored.
     */
    private void setAdapterInternal( Adapter adapter, boolean compatibleWithPrevious,
            boolean removeAndRecycleViews) {
       
    }
    /**
     * Retrieves the previously set adapter or null if no adapter is set.
     *
     * @return The previously set adapter
     * @see #setAdapter(Adapter)
     */
    
    public Adapter getAdapter() {
        return mAdapter;
    }
    /**
     * Register a listener that will be notified whenever a child view is recycled.
     *
     * <p>This listener will be called when a LayoutManager or the RecyclerView decides
     * that a child view is no longer needed. If an application associates expensive
     * or heavyweight data with item views, this may be a good place to release
     * or free those resources.</p>
     *
     * @param listener Listener to register, or null to clear
     */
    public void setRecyclerListener( RecyclerListener listener) {
        mRecyclerListener = listener;
    }
    /**
     * <p>Return the offset of the RecyclerView's text baseline from the its top
     * boundary. If the LayoutManager of this RecyclerView does not support baseline alignment,
     * this method returns -1.</p>
     *
     * @return the offset of the baseline within the RecyclerView's bounds or -1
     *         if baseline alignment is not supported
     */
    @Override
    public int getBaseline() {
      return 0;
    }
    /**
     * Register a listener that will be notified whenever a child view is attached to or detached
     * from RecyclerView.
     *
     * <p>This listener will be called when a LayoutManager or the RecyclerView decides
     * that a child view is no longer needed. If an application associates expensive
     * or heavyweight data with item views, this may be a good place to release
     * or free those resources.</p>
     *
     * @param listener Listener to register
     */
    public void addOnChildAttachStateChangeListener(
             OnChildAttachStateChangeListener listener) {
        if (mOnChildAttachStateListeners == null) {
            mOnChildAttachStateListeners = new ArrayList<>();
        }
        mOnChildAttachStateListeners.add(listener);
    }
    /**
     * Removes the provided listener from child attached state listeners list.
     *
     * @param listener Listener to unregister
     */
    public void removeOnChildAttachStateChangeListener(
             OnChildAttachStateChangeListener listener) {
        if (mOnChildAttachStateListeners == null) {
            return;
        }
        mOnChildAttachStateListeners.remove(listener);
    }
    /**
     * Removes all listeners that were added via
     * {@link #addOnChildAttachStateChangeListener(OnChildAttachStateChangeListener)}.
     */
    public void clearOnChildAttachStateChangeListeners() {
        if (mOnChildAttachStateListeners != null) {
            mOnChildAttachStateListeners.clear();
        }
    }
   
    /**
     * Set a {@link OnFlingListener} for this {@link RecyclerView}.
     * <p>
     * If the {@link OnFlingListener} is set then it will receive
     * calls to {@link #fling(int,int)} and will be able to intercept them.
     *
     * @param onFlingListener The {@link OnFlingListener} instance.
     */
    public void setOnFlingListener(OnFlingListener onFlingListener) {
        mOnFlingListener = onFlingListener;
    }
    /**
     * Get the current {@link OnFlingListener} from this {@link RecyclerView}.
     *
     * @return The {@link OnFlingListener} instance currently set (can be null).
     */
    public OnFlingListener getOnFlingListener() {
        return mOnFlingListener;
    }
    @Override
    protected Parcelable onSaveInstanceState() {
        return null;
    }
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        
    }
    /**
     * Override to prevent freezing of any views created by the adapter.
     */
    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }
    /**
     * Override to prevent thawing of any views created by the adapter.
     */
    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }
    /**
     * Adds a view to the animatingViews list.
     * mAnimatingViews holds the child views that are currently being kept around
     * purely for the purpose of being animated out of view. They are drawn as a regular
     * part of the child list of the RecyclerView, but they are invisible to the LayoutManager
     * as they are managed separately from the regular child views.
     * @param viewHolder The ViewHolder to be removed
     */
    private void addAnimatingView(ViewHolder viewHolder) {
       
    }
    /**
     * Removes a view from the animatingViews list.
     * @param view The view to be removed
     * @see #addAnimatingView(RecyclerView.ViewHolder)
     * @return true if an animating view is removed
     */
    boolean removeAnimatingView(View view) {
       return false;
    }
    /**
     * Retrieve this RecyclerView's {@link RecycledViewPool}. This method will never return null;
     * if no pool is set for this view a new one will be created. See
     * {@link #setRecycledViewPool(RecycledViewPool) setRecycledViewPool} for more information.
     *
     * @return The pool used to store recycled item views for reuse.
     * @see #setRecycledViewPool(RecycledViewPool)
     */
    
    public RecycledViewPool getRecycledViewPool() {
    	return null;
    }
    /**
     * Recycled view pools allow multiple RecyclerViews to share a common pool of scrap views.
     * This can be useful if you have multiple RecyclerViews with adapters that use the same
     * view types, for example if you have several data sets with the same kinds of item views
     * displayed by a {@link androidx.viewpager.widget.ViewPager}.
     *
     * @param pool Pool to set. If this parameter is null a new pool will be created and used.
     */
    public void setRecycledViewPool( RecycledViewPool pool) {
    }
    /**
     * Sets a new {@link ViewCacheExtension} to be used by the Recycler.
     *
     * @param extension ViewCacheExtension to be used or null if you want to clear the existing one.
     *
     * @see ViewCacheExtension#getViewForPositionAndType(Recycler, int, int)
     */
    public void setViewCacheExtension( ViewCacheExtension extension) {
    }
    /**
     * Set the number of offscreen views to retain before adding them to the potentially shared
     * {@link #getRecycledViewPool() recycled view pool}.
     *
     * <p>The offscreen view cache stays aware of changes in the attached adapter, allowing
     * a LayoutManager to reuse those views unmodified without needing to return to the adapter
     * to rebind them.</p>
     *
     * @param size Number of views to cache offscreen before returning them to the general
     *             recycled view pool
     */
    public void setItemViewCacheSize(int size) {
    }
    /**
     * Return the current scrolling state of the RecyclerView.
     *
     * @return {@link #SCROLL_STATE_IDLE}, {@link #SCROLL_STATE_DRAGGING} or
     * {@link #SCROLL_STATE_SETTLING}
     */
    public int getScrollState() {
        return mScrollState;
    }
    /**
     * Add an {@link ItemDecoration} to this RecyclerView. Item decorations can
     * affect both measurement and drawing of individual item views.
     *
     * <p>Item decorations are ordered. Decorations placed earlier in the list will
     * be run/queried/drawn first for their effects on item views. Padding added to views
     * will be nested; a padding added by an earlier decoration will mean further
     * item decorations in the list will be asked to draw/pad within the previous decoration's
     * given area.</p>
     *
     * @param decor Decoration to add
     * @param index Position in the decoration chain to insert this decoration at. If this value
     *              is negative the decoration will be added at the end.
     */
    public void addItemDecoration( ItemDecoration decor, int index) {
    }
    /**
     * Add an {@link ItemDecoration} to this RecyclerView. Item decorations can
     * affect both measurement and drawing of individual item views.
     *
     * <p>Item decorations are ordered. Decorations placed earlier in the list will
     * be run/queried/drawn first for their effects on item views. Padding added to views
     * will be nested; a padding added by an earlier decoration will mean further
     * item decorations in the list will be asked to draw/pad within the previous decoration's
     * given area.</p>
     *
     * @param decor Decoration to add
     */
    public void addItemDecoration( ItemDecoration decor) {
        addItemDecoration(decor, -1);
    }
    /**
     * Returns an {@link ItemDecoration} previously added to this RecyclerView.
     *
     * @param index The index position of the desired ItemDecoration.
     * @return the ItemDecoration at index position
     * @throws IndexOutOfBoundsException on invalid index
     */
    
    public ItemDecoration getItemDecorationAt(int index) {
        final int size = getItemDecorationCount();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index + " is an invalid index for size " + size);
        }
        return mItemDecorations.get(index);
    }
    /**
     * Returns the number of {@link ItemDecoration} currently added to this RecyclerView.
     *
     * @return number of ItemDecorations currently added added to this RecyclerView.
     */
    public int getItemDecorationCount() {
        return mItemDecorations.size();
    }
    /**
     * Removes the {@link ItemDecoration} associated with the supplied index position.
     *
     * @param index The index position of the ItemDecoration to be removed.
     */
    public void removeItemDecorationAt(int index) {
        final int size = getItemDecorationCount();
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index + " is an invalid index for size " + size);
        }
        removeItemDecoration(getItemDecorationAt(index));
    }
    /**
     * Remove an {@link ItemDecoration} from this RecyclerView.
     *
     * <p>The given decoration will no longer impact the measurement and drawing of
     * item views.</p>
     *
     * @param decor Decoration to remove
     * @see #addItemDecoration(ItemDecoration)
     */
    public void removeItemDecoration( ItemDecoration decor) {
    }
    /**
     * Sets the {@link ChildDrawingOrderCallback} to be used for drawing children.
     * <p>
     * See {@link ViewGroup#getChildDrawingOrder(int, int)} for details. Calling this method will
     * always call {@link ViewGroup#setChildrenDrawingOrderEnabled(boolean)}. The parameter will be
     * true if childDrawingOrderCallback is not null, false otherwise.
     * <p>
     * Note that child drawing order may be overridden by View's elevation.
     *
     * @param childDrawingOrderCallback The ChildDrawingOrderCallback to be used by the drawing
     *                                  system.
     */
    public void setChildDrawingOrderCallback(
             ChildDrawingOrderCallback childDrawingOrderCallback) {
        if (childDrawingOrderCallback == mChildDrawingOrderCallback) {
            return;
        }
        mChildDrawingOrderCallback = childDrawingOrderCallback;
        setChildrenDrawingOrderEnabled(mChildDrawingOrderCallback != null);
    }
    /**
     * Set a listener that will be notified of any changes in scroll state or position.
     *
     * @param listener Listener to set or null to clear
     *
     * @deprecated Use {@link #addOnScrollListener(OnScrollListener)} and
     *             {@link #removeOnScrollListener(OnScrollListener)}
     */
    @Deprecated
    public void setOnScrollListener( OnScrollListener listener) {
        mScrollListener = listener;
    }
    /**
     * Add a listener that will be notified of any changes in scroll state or position.
     *
     * <p>Components that add a listener should take care to remove it when finished.
     * Other components that take ownership of a view may call {@link #clearOnScrollListeners()}
     * to remove all attached listeners.</p>
     *
     * @param listener listener to set
     */
    public void addOnScrollListener( OnScrollListener listener) {
        if (mScrollListeners == null) {
            mScrollListeners = new ArrayList<>();
        }
        mScrollListeners.add(listener);
    }
    /**
     * Remove a listener that was notified of any changes in scroll state or position.
     *
     * @param listener listener to set or null to clear
     */
    public void removeOnScrollListener( OnScrollListener listener) {
        if (mScrollListeners != null) {
            mScrollListeners.remove(listener);
        }
    }
    /**
     * Remove all secondary listener that were notified of any changes in scroll state or position.
     */
    public void clearOnScrollListeners() {
        if (mScrollListeners != null) {
            mScrollListeners.clear();
        }
    }
    /**
     * Convenience method to scroll to a certain position.
     *
     * RecyclerView does not implement scrolling logic, rather forwards the call to
     * {@link RecyclerView.LayoutManager#scrollToPosition(int)}
     * @param position Scroll to this adapter position
     * @see RecyclerView.LayoutManager#scrollToPosition(int)
     */
    public void scrollToPosition(int position) {
        
    }
    void jumpToPositionForSmoothScroller(int position) {
        
    }
    /**
     * Starts a smooth scroll to an adapter position.
     * <p>
     * To support smooth scrolling, you must override
     * {@link LayoutManager#smoothScrollToPosition(RecyclerView, State, int)} and create a
     * {@link SmoothScroller}.
     * <p>
     * {@link LayoutManager} is responsible for creating the actual scroll action. If you want to
     * provide a custom smooth scroll logic, override
     * {@link LayoutManager#smoothScrollToPosition(RecyclerView, State, int)} in your
     * LayoutManager.
     *
     * @param position The adapter position to scroll to
     * @see LayoutManager#smoothScrollToPosition(RecyclerView, State, int)
     */
    public void smoothScrollToPosition(int position) {
        
    }
    @Override
    public void scrollTo(int x, int y) {
        Log.w(TAG, "RecyclerView does not support scrolling to an absolute position. "
                + "Use scrollToPosition instead");
    }
    @Override
    public void scrollBy(int x, int y) {
        
    }
    /**
     * Scrolls the RV by 'dx' and 'dy' via calls to
     * {@link LayoutManager#scrollHorizontallyBy(int, Recycler, State)} and
     * {@link LayoutManager#scrollVerticallyBy(int, Recycler, State)}.
     *
     * Also sets how much of the scroll was actually consumed in 'consumed' parameter (indexes 0 and
     * 1 for the x axis and y axis, respectively).
     *
     * This method should only be called in the context of an existing scroll operation such that
     * any other necessary operations (such as a call to {@link #consumePendingUpdateOperations()})
     * is already handled.
     */
    void scrollStep(int dx, int dy,  int[] consumed) {
        
    }
    /**
     * Helper method reflect data changes to the state.
     * <p>
     * Adapter changes during a scroll may trigger a crash because scroll assumes no data change
     * but data actually changed.
     * <p>
     * This method consumes all deferred changes to avoid that case.
     */
    void consumePendingUpdateOperations() {
        
    }
    /**
     * @return True if an existing view holder needs to be updated
     */
    private boolean hasUpdatedView() {
        
        return false;
    }
    /**
     * Does not perform bounds checking. Used by internal methods that have already validated input.
     * <p>
     * It also reports any unused scroll request to the related EdgeEffect.
     *
     * @param x The amount of horizontal scroll request
     * @param y The amount of vertical scroll request
     * @param ev The originating MotionEvent, or null if not from a touch event.
     *
     * @return Whether any scroll was consumed in either direction.
     */
    boolean scrollByInternal(int x, int y, MotionEvent ev) {
        
        return false;
    }
    /**
     * <p>Compute the horizontal offset of the horizontal scrollbar's thumb within the horizontal
     * range. This value is used to compute the length of the thumb within the scrollbar's track.
     * </p>
     *
     * <p>The range is expressed in arbitrary units that must be the same as the units used by
     * {@link #computeHorizontalScrollRange()} and {@link #computeHorizontalScrollExtent()}.</p>
     *
     * <p>Default implementation returns 0.</p>
     *
     * <p>If you want to support scroll bars, override
     * {@link RecyclerView.LayoutManager#computeHorizontalScrollOffset(RecyclerView.State)} in your
     * LayoutManager. </p>
     *
     * @return The horizontal offset of the scrollbar's thumb
     * @see RecyclerView.LayoutManager#computeHorizontalScrollOffset
     * (RecyclerView.State)
     */
    @Override
    public int computeHorizontalScrollOffset() {
        
            return 0;
        
    }
    /**
     * <p>Compute the horizontal extent of the horizontal scrollbar's thumb within the
     * horizontal range. This value is used to compute the length of the thumb within the
     * scrollbar's track.</p>
     *
     * <p>The range is expressed in arbitrary units that must be the same as the units used by
     * {@link #computeHorizontalScrollRange()} and {@link #computeHorizontalScrollOffset()}.</p>
     *
     * <p>Default implementation returns 0.</p>
     *
     * <p>If you want to support scroll bars, override
     * {@link RecyclerView.LayoutManager#computeHorizontalScrollExtent(RecyclerView.State)} in your
     * LayoutManager.</p>
     *
     * @return The horizontal extent of the scrollbar's thumb
     * @see RecyclerView.LayoutManager#computeHorizontalScrollExtent(RecyclerView.State)
     */
    @Override
    public int computeHorizontalScrollExtent() {
        
            return 0;
        
    }
    /**
     * <p>Compute the horizontal range that the horizontal scrollbar represents.</p>
     *
     * <p>The range is expressed in arbitrary units that must be the same as the units used by
     * {@link #computeHorizontalScrollExtent()} and {@link #computeHorizontalScrollOffset()}.</p>
     *
     * <p>Default implementation returns 0.</p>
     *
     * <p>If you want to support scroll bars, override
     * {@link RecyclerView.LayoutManager#computeHorizontalScrollRange(RecyclerView.State)} in your
     * LayoutManager.</p>
     *
     * @return The total horizontal range represented by the vertical scrollbar
     * @see RecyclerView.LayoutManager#computeHorizontalScrollRange(RecyclerView.State)
     */
    @Override
    public int computeHorizontalScrollRange() {
        
            return 0;
        
    }
    /**
     * <p>Compute the vertical offset of the vertical scrollbar's thumb within the vertical range.
     * This value is used to compute the length of the thumb within the scrollbar's track. </p>
     *
     * <p>The range is expressed in arbitrary units that must be the same as the units used by
     * {@link #computeVerticalScrollRange()} and {@link #computeVerticalScrollExtent()}.</p>
     *
     * <p>Default implementation returns 0.</p>
     *
     * <p>If you want to support scroll bars, override
     * {@link RecyclerView.LayoutManager#computeVerticalScrollOffset(RecyclerView.State)} in your
     * LayoutManager.</p>
     *
     * @return The vertical offset of the scrollbar's thumb
     * @see RecyclerView.LayoutManager#computeVerticalScrollOffset
     * (RecyclerView.State)
     */
    @Override
    public int computeVerticalScrollOffset() {
        
            return 0;
        
    }
    /**
     * <p>Compute the vertical extent of the vertical scrollbar's thumb within the vertical range.
     * This value is used to compute the length of the thumb within the scrollbar's track.</p>
     *
     * <p>The range is expressed in arbitrary units that must be the same as the units used by
     * {@link #computeVerticalScrollRange()} and {@link #computeVerticalScrollOffset()}.</p>
     *
     * <p>Default implementation returns 0.</p>
     *
     * <p>If you want to support scroll bars, override
     * {@link RecyclerView.LayoutManager#computeVerticalScrollExtent(RecyclerView.State)} in your
     * LayoutManager.</p>
     *
     * @return The vertical extent of the scrollbar's thumb
     * @see RecyclerView.LayoutManager#computeVerticalScrollExtent(RecyclerView.State)
     */
    @Override
    public int computeVerticalScrollExtent() {
        
            return 0;
        
    }
    /**
     * <p>Compute the vertical range that the vertical scrollbar represents.</p>
     *
     * <p>The range is expressed in arbitrary units that must be the same as the units used by
     * {@link #computeVerticalScrollExtent()} and {@link #computeVerticalScrollOffset()}.</p>
     *
     * <p>Default implementation returns 0.</p>
     *
     * <p>If you want to support scroll bars, override
     * {@link RecyclerView.LayoutManager#computeVerticalScrollRange(RecyclerView.State)} in your
     * LayoutManager.</p>
     *
     * @return The total vertical range represented by the vertical scrollbar
     * @see RecyclerView.LayoutManager#computeVerticalScrollRange(RecyclerView.State)
     */
    @Override
    public int computeVerticalScrollRange() {
        
            return 0;
        
    }
    /**
     * This method should be called before any code that may trigger a child view to cause a call to
     * {@link RecyclerView#requestLayout()}.  Doing so enables {@link RecyclerView} to avoid
     * reacting to additional redundant calls to {@link #requestLayout()}.
     * <p>
     * A call to this method must always be accompanied by a call to
     * {@link #stopInterceptRequestLayout(boolean)} that follows the code that may trigger a
     * child View to cause a call to {@link RecyclerView#requestLayout()}.
     *
     * @see #stopInterceptRequestLayout(boolean)
     */
    void startInterceptRequestLayout() {
        mInterceptRequestLayoutDepth++;
        if (mInterceptRequestLayoutDepth == 1 && !mLayoutSuppressed) {
            mLayoutWasDefered = false;
        }
    }
    /**
     * This method should be called after any code that may trigger a child view to cause a call to
     * {@link RecyclerView#requestLayout()}.
     * <p>
     * A call to this method must always be accompanied by a call to
     * {@link #startInterceptRequestLayout()} that precedes the code that may trigger a child
     * View to cause a call to {@link RecyclerView#requestLayout()}.
     *
     * @see #startInterceptRequestLayout()
     */
    void stopInterceptRequestLayout(boolean performLayoutChildren) {
        
    }
    /**
     * Tells this RecyclerView to suppress all layout and scroll calls until layout
     * suppression is disabled with a later call to suppressLayout(false).
     * When layout suppression is disabled, a requestLayout() call is sent
     * if requestLayout() was attempted while layout was being suppressed.
     * <p>
     * In addition to the layout suppression {@link #smoothScrollBy(int, int)},
     * {@link #scrollBy(int, int)}, {@link #scrollToPosition(int)} and
     * {@link #smoothScrollToPosition(int)} are dropped; TouchEvents and GenericMotionEvents are
     * dropped; {@link LayoutManager#onFocusSearchFailed(View, int, Recycler, State)} will not be
     * called.
     *
     * <p>
     * <code>suppressLayout(true)</code> does not prevent app from directly calling {@link
     * LayoutManager#scrollToPosition(int)}, {@link LayoutManager#smoothScrollToPosition(
     * RecyclerView, State, int)}.
     * <p>
     * {@link #setAdapter(Adapter)} and {@link #swapAdapter(Adapter, boolean)} will automatically
     * stop suppressing.
     * <p>
     * Note: Running ItemAnimator is not stopped automatically,  it's caller's
     * responsibility to call ItemAnimator.end().
     *
     * @param suppress true to suppress layout and scroll, false to re-enable.
     */
    public final void suppressLayout(boolean suppress) {
        
    }
    /**
     * Returns whether layout and scroll calls on this container are currently being
     * suppressed, due to an earlier call to {@link #suppressLayout(boolean)}.
     *
     * @return true if layout and scroll are currently suppressed, false otherwise.
     */
    public final boolean isLayoutSuppressed() {
        return mLayoutSuppressed;
    }
    /**
     * Enable or disable layout and scroll.  After <code>setLayoutFrozen(true)</code> is called,
     * Layout requests will be postponed until <code>setLayoutFrozen(false)</code> is called;
     * child views are not updated when RecyclerView is frozen, {@link #smoothScrollBy(int, int)},
     * {@link #scrollBy(int, int)}, {@link #scrollToPosition(int)} and
     * {@link #smoothScrollToPosition(int)} are dropped; TouchEvents and GenericMotionEvents are
     * dropped; {@link LayoutManager#onFocusSearchFailed(View, int, Recycler, State)} will not be
     * called.
     *
     * <p>
     * <code>setLayoutFrozen(true)</code> does not prevent app from directly calling {@link
     * LayoutManager#scrollToPosition(int)}, {@link LayoutManager#smoothScrollToPosition(
     * RecyclerView, State, int)}.
     * <p>
     * {@link #setAdapter(Adapter)} and {@link #swapAdapter(Adapter, boolean)} will automatically
     * stop frozen.
     * <p>
     * Note: Running ItemAnimator is not stopped automatically,  it's caller's
     * responsibility to call ItemAnimator.end().
     *
     * @param frozen   true to freeze layout and scroll, false to re-enable.
     *
     * @deprecated Use {@link #suppressLayout(boolean)}.
     */
    @Deprecated
    public void setLayoutFrozen(boolean frozen) {
        suppressLayout(frozen);
    }
    /**
     * @return true if layout and scroll are frozen
     *
     * @deprecated Use {@link #isLayoutSuppressed()}.
     */
    @Deprecated
    public boolean isLayoutFrozen() {
        return isLayoutSuppressed();
    }
    /**
     * @deprecated Use {@link #setItemAnimator(ItemAnimator)} ()}.
     */
    @Deprecated
    @Override
    public void setLayoutTransition(LayoutTransition transition) {
        if (transition == null) {
            super.setLayoutTransition(null);
        } else {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is "
                    + "not supported. Please use setItemAnimator() instead for animating changes "
                    + "to the items in this RecyclerView");
        }
    }
    /**
     * Animate a scroll by the given amount of pixels along either axis.
     *
     * @param dx Pixels to scroll horizontally
     * @param dy Pixels to scroll vertically
     */
    public void smoothScrollBy( int dx,  int dy) {
        smoothScrollBy(dx, dy, null);
    }
    /**
     * Animate a scroll by the given amount of pixels along either axis.
     *
     * @param dx Pixels to scroll horizontally
     * @param dy Pixels to scroll vertically
     * @param interpolator {@link Interpolator} to be used for scrolling. If it is
     *                     {@code null}, RecyclerView is going to use the default interpolator.
     */
    public void smoothScrollBy( int dx,  int dy,  Interpolator interpolator) {
        
    }
    /**
     * Begin a standard fling with an initial velocity along each axis in pixels per second.
     * If the velocity given is below the system-defined minimum this method will return false
     * and no fling will occur.
     *
     * @param velocityX Initial horizontal velocity in pixels per second
     * @param velocityY Initial vertical velocity in pixels per second
     * @return true if the fling was started, false if the velocity was too low to fling or
     * LayoutManager does not support scrolling in the axis fling is issued.
     *
     * @see LayoutManager#canScrollVertically()
     * @see LayoutManager#canScrollHorizontally()
     */
    public boolean fling(int velocityX, int velocityY) {
            return false;
        
    }
    /**
     * Similar to {@link #stopScroll()} but does not set the state.
     */
    private void stopScrollersInternal() {
        
    }
    /**
     * Returns the minimum velocity to start a fling.
     *
     * @return The minimum velocity to start a fling
     */
    public int getMinFlingVelocity() {
        return mMinFlingVelocity;
    }
    /**
     * Returns the maximum fling velocity used by this RecyclerView.
     *
     * @return The maximum fling velocity used by this RecyclerView.
     */
    public int getMaxFlingVelocity() {
        return mMaxFlingVelocity;
    }
    /**
     * Apply a pull to relevant overscroll glow effects
     */
    private void pullGlows(float x, float overscrollX, float y, float overscrollY) {
        
    }
    private void releaseGlows() {
        boolean needsInvalidate = false;
        if (mLeftGlow != null) {
            mLeftGlow.onRelease();
            needsInvalidate = mLeftGlow.isFinished();
        }
        if (mTopGlow != null) {
            mTopGlow.onRelease();
            needsInvalidate |= mTopGlow.isFinished();
        }
        if (mRightGlow != null) {
            mRightGlow.onRelease();
            needsInvalidate |= mRightGlow.isFinished();
        }
        if (mBottomGlow != null) {
            mBottomGlow.onRelease();
            needsInvalidate |= mBottomGlow.isFinished();
        }
        if (needsInvalidate) {
           
        }
    }
    void considerReleasingGlowsOnScroll(int dx, int dy) {
        boolean needsInvalidate = false;
        if (mLeftGlow != null && !mLeftGlow.isFinished() && dx > 0) {
            mLeftGlow.onRelease();
            needsInvalidate = mLeftGlow.isFinished();
        }
        if (mRightGlow != null && !mRightGlow.isFinished() && dx < 0) {
            mRightGlow.onRelease();
            needsInvalidate |= mRightGlow.isFinished();
        }
        if (mTopGlow != null && !mTopGlow.isFinished() && dy > 0) {
            mTopGlow.onRelease();
            needsInvalidate |= mTopGlow.isFinished();
        }
        if (mBottomGlow != null && !mBottomGlow.isFinished() && dy < 0) {
            mBottomGlow.onRelease();
            needsInvalidate |= mBottomGlow.isFinished();
        }
        if (needsInvalidate) {
        }
    }
    void absorbGlows(int velocityX, int velocityY) {
        if (velocityX < 0) {
            ensureLeftGlow();
            if (mLeftGlow.isFinished()) {
                mLeftGlow.onAbsorb(-velocityX);
            }
        } else if (velocityX > 0) {
            ensureRightGlow();
            if (mRightGlow.isFinished()) {
                mRightGlow.onAbsorb(velocityX);
            }
        }
        if (velocityY < 0) {
            ensureTopGlow();
            if (mTopGlow.isFinished()) {
                mTopGlow.onAbsorb(-velocityY);
            }
        } else if (velocityY > 0) {
            ensureBottomGlow();
            if (mBottomGlow.isFinished()) {
                mBottomGlow.onAbsorb(velocityY);
            }
        }
        if (velocityX != 0 || velocityY != 0) {
        }
    }
    void ensureLeftGlow() {
        if (mLeftGlow != null) {
            return;
        }
        if (mClipToPadding) {
            mLeftGlow.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(),
                    getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
        } else {
            mLeftGlow.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }
    void ensureRightGlow() {
    }
    void ensureTopGlow() {
    }
    void ensureBottomGlow() {
    }
    void invalidateGlows() {
        mLeftGlow = mRightGlow = mTopGlow = mBottomGlow = null;
    }
    /**
     * Since RecyclerView is a collection ViewGroup that includes virtual children (items that are
     * in the Adapter but not visible in the UI), it employs a more involved focus search strategy
     * that differs from other ViewGroups.
     * <p>
     * It first does a focus search within the RecyclerView. If this search finds a View that is in
     * the focus direction with respect to the currently focused View, RecyclerView returns that
     * child as the next focus target. When it cannot find such child, it calls
     * {@link LayoutManager#onFocusSearchFailed(View, int, Recycler, State)} to layout more Views
     * in the focus search direction. If LayoutManager adds a View that matches the
     * focus search criteria, it will be returned as the focus search result. Otherwise,
     * RecyclerView will call parent to handle the focus search like a regular ViewGroup.
     * <p>
     * When the direction is {@link View#FOCUS_FORWARD} or {@link View#FOCUS_BACKWARD}, a View that
     * is not in the focus direction is still valid focus target which may not be the desired
     * behavior if the Adapter has more children in the focus direction. To handle this case,
     * RecyclerView converts the focus direction to an absolute direction and makes a preliminary
     * focus search in that direction. If there are no Views to gain focus, it will call
     * {@link LayoutManager#onFocusSearchFailed(View, int, Recycler, State)} before running a
     * focus search with the original (relative) direction. This allows RecyclerView to provide
     * better candidates to the focus search while still allowing the view system to take focus from
     * the RecyclerView and give it to a more suitable child if such child exists.
     *
     * @param focused The view that currently has focus
     * @param direction One of {@link View#FOCUS_UP}, {@link View#FOCUS_DOWN},
     * {@link View#FOCUS_LEFT}, {@link View#FOCUS_RIGHT}, {@link View#FOCUS_FORWARD},
     * {@link View#FOCUS_BACKWARD} or 0 for not applicable.
     *
     * @return A new View that can be the next focus after the focused View
     */
    @Override
    public View focusSearch(View focused, int direction) {
       return null;
    }
    /**
     * Checks if the new focus candidate is a good enough candidate such that RecyclerView will
     * assign it as the next focus View instead of letting view hierarchy decide.
     * A good candidate means a View that is aligned in the focus direction wrt the focused View
     * and is not the RecyclerView itself.
     * When this method returns false, RecyclerView will let the parent make the decision so the
     * same View may still get the focus as a result of that search.
     */
    private boolean isPreferredNextFocus(View focused, View next, int direction) {
    	return false;
    }
    @Override
    public void requestChildFocus(View child, View focused) {
       
    }
    /**
     * Requests that the given child of the RecyclerView be positioned onto the screen. This method
     * can be called for both unfocusable and focusable child views. For unfocusable child views,
     * the {@param focused} parameter passed is null, whereas for a focusable child, this parameter
     * indicates the actual descendant view within this child view that holds the focus.
     * @param child The child view of this RecyclerView that wants to come onto the screen.
     * @param focused The descendant view that actually has the focus if child is focusable, null
     *                otherwise.
     */
    private void requestChildOnScreen( View child,  View focused) {
       
    }
    @Override
    public boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) {
    	return false;
    }
    @Override
    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        
    }
    @Override
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        if (isComputingLayout()) {
            // if we are in the middle of a layout calculation, don't let any child take focus.
            // RV will handle it after layout calculation is finished.
            return false;
        }
        return super.onRequestFocusInDescendants(direction, previouslyFocusedRect);
    }
    @Override
    protected void onAttachedToWindow() {
        
    }
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
       
    }
    /**
     * Returns true if RecyclerView is attached to window.
     */
    @Override
    public boolean isAttachedToWindow() {
        return mIsAttached;
    }
    /**
     * Checks if RecyclerView is in the middle of a layout or scroll and throws an
     * {@link IllegalStateException} if it <b>is not</b>.
     *
     * @param message The message for the exception. Can be null.
     * @see #assertNotInLayoutOrScroll(String)
     */
    void assertInLayoutOrScroll(String message) {
        if (!isComputingLayout()) {
            if (message == null) {
                throw new IllegalStateException("Cannot call this method unless RecyclerView is "
                        + "computing a layout or scrolling" + exceptionLabel());
            }
            throw new IllegalStateException(message + exceptionLabel());
        }
    }
    /**
     * Checks if RecyclerView is in the middle of a layout or scroll and throws an
     * {@link IllegalStateException} if it <b>is</b>.
     *
     * @param message The message for the exception. Can be null.
     * @see #assertInLayoutOrScroll(String)
     */
    void assertNotInLayoutOrScroll(String message) {
        if (isComputingLayout()) {
            if (message == null) {
                throw new IllegalStateException("Cannot call this method while RecyclerView is "
                        + "computing a layout or scrolling" + exceptionLabel());
            }
            throw new IllegalStateException(message);
        }
        if (mDispatchScrollCounter > 0) {
            Log.w(TAG, "Cannot call this method in a scroll callback. Scroll callbacks might"
                            + "be run during a measure & layout pass where you cannot change the"
                            + "RecyclerView data. Any method call that might change the structure"
                            + "of the RecyclerView or the adapter contents should be postponed to"
                            + "the next frame.",
                    new IllegalStateException("" + exceptionLabel()));
        }
    }
    /**
     * Add an {@link OnItemTouchListener} to intercept touch events before they are dispatched
     * to child views or this view's standard scrolling behavior.
     *
     * <p>Client code may use listeners to implement item manipulation behavior. Once a listener
     * returns true from
     * {@link OnItemTouchListener#onInterceptTouchEvent(RecyclerView, MotionEvent)} its
     * {@link OnItemTouchListener#onTouchEvent(RecyclerView, MotionEvent)} method will be called
     * for each incoming MotionEvent until the end of the gesture.</p>
     *
     * @param listener Listener to add
     * @see SimpleOnItemTouchListener
     */
    public void addOnItemTouchListener( OnItemTouchListener listener) {
        mOnItemTouchListeners.add(listener);
    }
    /**
     * Remove an {@link OnItemTouchListener}. It will no longer be able to intercept touch events.
     *
     * @param listener Listener to remove
     */
    public void removeOnItemTouchListener( OnItemTouchListener listener) {
        mOnItemTouchListeners.remove(listener);
        if (mInterceptingOnItemTouchListener == listener) {
            mInterceptingOnItemTouchListener = null;
        }
    }
    /**
     * Dispatches the motion event to the intercepting OnItemTouchListener or provides opportunity
     * for OnItemTouchListeners to intercept.
     * @param e The MotionEvent
     * @return True if handled by an intercepting OnItemTouchListener.
     */
    private boolean dispatchToOnItemTouchListeners(MotionEvent e) {
        // OnItemTouchListeners should receive calls to their methods in the same pattern that
        // ViewGroups do. That pattern is a bit confusing, which in turn makes the below code a
        // bit confusing.  Here are rules for the pattern:
        //
        // 1. A single MotionEvent should not be passed to either OnInterceptTouchEvent or
        // OnTouchEvent twice.
        // 2. ACTION_DOWN MotionEvents may be passed to both onInterceptTouchEvent and
        // onTouchEvent.
        // 3. All other MotionEvents should be passed to either onInterceptTouchEvent or
        // onTouchEvent, not both.
        // Side Note: If we are to truly mimic how MotionEvents work in the view system, for every
        // MotionEvent, any OnItemTouchListener that is before the intercepting OnItemTouchEvent
        // should still have a chance to intercept, and if it does, the previously intercepting
        // OnItemTouchEvent should get an ACTION_CANCEL event.
        if (mInterceptingOnItemTouchListener == null) {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                return false;
            }
            return findInterceptingOnItemTouchListener(e);
        } else {
            mInterceptingOnItemTouchListener.onTouchEvent(this, e);
            final int action = e.getAction();
            if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
                mInterceptingOnItemTouchListener = null;
            }
            return true;
        }
    }
    /**
     * Looks for an OnItemTouchListener that wants to intercept.
     *
     * <p>Passes the MotionEvent to all registered OnItemTouchListeners one at a time. If one wants
     * to intercept and the action is not ACTION_UP or ACTION_CANCEL, saves the intercepting
     * OnItemTouchListener and immediately returns true. If none want to intercept
     * or the action is ACTION_UP or ACTION_CANCEL, returns false.
     *
     * @param e The MotionEvent
     * @return true if an OnItemTouchListener is saved as intercepting.
     */
    private boolean findInterceptingOnItemTouchListener(MotionEvent e) {
        int action = e.getAction();
        final int listenerCount = mOnItemTouchListeners.size();
        for (int i = 0; i < listenerCount; i++) {
            final OnItemTouchListener listener = mOnItemTouchListeners.get(i);
            if (listener.onInterceptTouchEvent(this, e)
                    && action != MotionEvent.ACTION_UP && action != MotionEvent.ACTION_CANCEL) {
                mInterceptingOnItemTouchListener = listener;
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
       return false;
    }
    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        final int listenerCount = mOnItemTouchListeners.size();
        for (int i = 0; i < listenerCount; i++) {
            final OnItemTouchListener listener = mOnItemTouchListeners.get(i);
            listener.onRequestDisallowInterceptTouchEvent(disallowIntercept);
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {
    	return false;
    }
    private void resetScroll() {
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
        }
        releaseGlows();
    }
    private void onPointerUp(MotionEvent e) {
        final int actionIndex = e.getActionIndex();
        if (e.getPointerId(actionIndex) == mScrollPointerId) {
            // Pick a new pointer to pick up the slack.
            final int newIndex = actionIndex == 0 ? 1 : 0;
            mScrollPointerId = e.getPointerId(newIndex);
            mInitialTouchX = mLastTouchX = (int) (e.getX(newIndex) + 0.5f);
            mInitialTouchY = mLastTouchY = (int) (e.getY(newIndex) + 0.5f);
        }
    }
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
    	return false;
    }
    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
       
    }
    /**
     * An implementation of {@link View#onMeasure(int, int)} to fall back to in various scenarios
     * where this RecyclerView is otherwise lacking better information.
     */
    void defaultOnMeasure(int widthSpec, int heightSpec) {
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            invalidateGlows();
            // layout's w/h are updated during measure/layout steps.
        }
    }
    /**
     * Sets the {@link ItemAnimator} that will handle animations involving changes
     * to the items in this RecyclerView. By default, RecyclerView instantiates and
     * uses an instance of {@link DefaultItemAnimator}. Whether item animations are
     * enabled for the RecyclerView depends on the ItemAnimator and whether
     * the LayoutManager {@link LayoutManager#supportsPredictiveItemAnimations()
     * supports item animations}.
     *
     * @param animator The ItemAnimator being set. If null, no animations will occur
     * when changes occur to the items in this RecyclerView.
     */
    void onEnterLayoutOrScroll() {
        mLayoutOrScrollCounter++;
    }
    void onExitLayoutOrScroll() {
        onExitLayoutOrScroll(true);
    }
    void onExitLayoutOrScroll(boolean enableChangeEvents) {
    }
    boolean isAccessibilityEnabled() {
        return mAccessibilityManager != null && mAccessibilityManager.isEnabled();
    }
    private void dispatchContentChangedIfNecessary() {
        final int flags = mEatenAccessibilityChangeFlags;
        mEatenAccessibilityChangeFlags = 0;
        if (flags != 0 && isAccessibilityEnabled()) {
            final AccessibilityEvent event = AccessibilityEvent.obtain();
            event.setEventType(AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED);
            sendAccessibilityEventUnchecked(event);
        }
    }
    /**
     * Returns whether RecyclerView is currently computing a layout.
     * <p>
     * If this method returns true, it means that RecyclerView is in a lockdown state and any
     * attempt to update adapter contents will result in an exception because adapter contents
     * cannot be changed while RecyclerView is trying to compute the layout.
     * <p>
     * It is very unlikely that your code will be running during this state as it is
     * called by the framework when a layout traversal happens or RecyclerView starts to scroll
     * in response to system events (touch, accessibility etc).
     * <p>
     * This case may happen if you have some custom logic to change adapter contents in
     * response to a View callback (e.g. focus change callback) which might be triggered during a
     * layout calculation. In these cases, you should just postpone the change using a Handler or a
     * similar mechanism.
     *
     * @return <code>true</code> if RecyclerView is currently computing a layout, <code>false</code>
     *         otherwise
     */
    public boolean isComputingLayout() {
        return mLayoutOrScrollCounter > 0;
    }
    /**
     * Returns true if an accessibility event should not be dispatched now. This happens when an
     * accessibility request arrives while RecyclerView does not have a stable state which is very
     * hard to handle for a LayoutManager. Instead, this method records necessary information about
     * the event and dispatches a window change event after the critical section is finished.
     *
     * @return True if the accessibility event should be postponed.
     */
    boolean shouldDeferAccessibilityEvent(AccessibilityEvent event) {
        return false;
    }
    @Override
    public void sendAccessibilityEventUnchecked(AccessibilityEvent event) {
        if (shouldDeferAccessibilityEvent(event)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(event);
    }
    /**
     * Consumes adapter updates and calculates which type of animations we want to run.
     * Called in onMeasure and dispatchLayout.
     * <p>
     * This method may process only the pre-layout state of updates or all of them.
     */
    private void processAdapterUpdatesAndSetAnimationFlags() {
    }
    /**
     * Wrapper around layoutChildren() that handles animating changes caused by layout.
     * Animations work on the assumption that there are five different kinds of items
     * in play:
     * PERSISTENT: items are visible before and after layout
     * REMOVED: items were visible before layout and were removed by the app
     * ADDED: items did not exist before layout and were added by the app
     * DISAPPEARING: items exist in the data set before/after, but changed from
     * visible to non-visible in the process of layout (they were moved off
     * screen as a side-effect of other changes)
     * APPEARING: items exist in the data set before/after, but changed from
     * non-visible to visible in the process of layout (they were moved on
     * screen as a side-effect of other changes)
     * The overall approach figures out what items exist before/after layout and
     * infers one of the five above states for each of the items. Then the animations
     * are set up accordingly:
     * PERSISTENT views are animated via
     * {@link ItemAnimator#animatePersistence(ViewHolder, ItemHolderInfo, ItemHolderInfo)}
     * DISAPPEARING views are animated via
     * {@link ItemAnimator#animateDisappearance(ViewHolder, ItemHolderInfo, ItemHolderInfo)}
     * APPEARING views are animated via
     * {@link ItemAnimator#animateAppearance(ViewHolder, ItemHolderInfo, ItemHolderInfo)}
     * and changed views are animated via
     * {@link ItemAnimator#animateChange(ViewHolder, ViewHolder, ItemHolderInfo, ItemHolderInfo)}.
     */
    void dispatchLayout() {
    }
    private void saveFocusInfo() {
        View child = null;
        if (mPreserveFocusAfterLayout && hasFocus() && mAdapter != null) {
            child = getFocusedChild();
        }
        final ViewHolder focusedVh = child == null ? null : findContainingViewHolder(child);
        if (focusedVh == null) {
            resetFocusInfo();
        } else {
            mState.mFocusedItemId = mAdapter.hasStableIds() ? focusedVh.getItemId() : NO_ID;
            // mFocusedItemPosition should hold the current adapter position of the previously
            // focused item. If the item is removed, we store the previous adapter position of the
            // removed item.
            mState.mFocusedItemPosition = mDataSetHasChangedAfterLayout ? NO_POSITION
                    : (focusedVh.isRemoved() ? focusedVh.mOldPosition
                            : focusedVh.getAdapterPosition());
            mState.mFocusedSubChildId = getDeepestFocusedViewWithId(focusedVh.itemView);
        }
    }
    private void resetFocusInfo() {
        mState.mFocusedItemId = NO_ID;
        mState.mFocusedItemPosition = NO_POSITION;
        mState.mFocusedSubChildId = View.NO_ID;
    }
    private int getDeepestFocusedViewWithId(View view) {
        int lastKnownId = view.getId();
        while (!view.isFocused() && view instanceof ViewGroup && view.hasFocus()) {
            view = ((ViewGroup) view).getFocusedChild();
            final int id = view.getId();
            if (id != View.NO_ID) {
                lastKnownId = view.getId();
            }
        }
        return lastKnownId;
    }
    private boolean didChildRangeChange(int minPositionPreLayout, int maxPositionPreLayout) {
    	return false;
    }
    @Override
    protected void removeDetachedView(View child, boolean animate) {
        ViewHolder vh = getChildViewHolderInt(child);
        if (vh != null) {
            if (vh.isTmpDetached()) {
                vh.clearTmpDetachFlag();
            } else if (!vh.shouldIgnore()) {
                throw new IllegalArgumentException("Called removeDetachedView with a view which"
                        + " is not flagged as tmp detached." + vh + exceptionLabel());
            }
        }
        // Clear any android.view.animation.Animation that may prevent the item from
        // detaching when being removed. If a child is re-added before the
        // lazy detach occurs, it will receive invalid attach/detach sequencing.
        child.clearAnimation();
        dispatchChildDetached(child);
        super.removeDetachedView(child, animate);
    }
    /**
     * Returns a unique key to be used while handling change animations.
     * It might be child's position or stable id depending on the adapter type.
     */
    long getChangedHolderKey(ViewHolder holder) {
        return mAdapter.hasStableIds() ? holder.getItemId() : holder.mPosition;
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        dispatchLayout();
        mFirstLayoutComplete = true;
    }
    @Override
    public void requestLayout() {
        if (mInterceptRequestLayoutDepth == 0 && !mLayoutSuppressed) {
            super.requestLayout();
        } else {
            mLayoutWasDefered = true;
        }
    }
    void markItemDecorInsetsDirty() {
    }
    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        final int count = mItemDecorations.size();
        for (int i = 0; i < count; i++) {
            mItemDecorations.get(i).onDraw(c, this, mState);
        }
    }
    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
    	return false;
    }
    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
    	return null;
    }
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return null;
    }
    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
    	return null;
    }
    /**
     * Returns true if RecyclerView is currently running some animations.
     * <p>
     * If you want to be notified when animations are finished, use
     * {@link ItemAnimator#isRunning(ItemAnimator.ItemAnimatorFinishedListener)}.
     *
     * @return True if there are some item animations currently running or waiting to be started.
     */
    public boolean isAnimating() {
    	return false;
    }
    void saveOldPositions() {
    }
    void clearOldPositions() {
    }
    void offsetPositionRecordsForMove(int from, int to) {
    }
    void offsetPositionRecordsForRemove(int positionStart, int itemCount,
            boolean applyToPreLayout) {
    }
    /**
     * Rebind existing views for the given range, or create as needed.
     *
     * @param positionStart Adapter position to start at
     * @param itemCount Number of views that must explicitly be rebound
     */
    void viewRangeUpdate(int positionStart, int itemCount, Object payload) {
    }
    boolean canReuseUpdatedViewHolder(ViewHolder viewHolder) {
       return false;
    }
    /**
     * Processes the fact that, as far as we can tell, the data set has completely changed.
     *
     * <ul>
     *   <li>Once layout occurs, all attached items should be discarded or animated.
     *   <li>Attached items are labeled as invalid.
     *   <li>Because items may still be prefetched between a "data set completely changed"
     *       event and a layout event, all cached items are discarded.
     * </ul>
     *
     * @param dispatchItemsChanged Whether to call
     * {@link LayoutManager#onItemsChanged(RecyclerView)} during measure/layout.
     */
    void processDataSetCompletelyChanged(boolean dispatchItemsChanged) {
        mDispatchItemsChangedEvent |= dispatchItemsChanged;
        mDataSetHasChangedAfterLayout = true;
        markKnownViewsInvalid();
    }
    /**
     * Mark all known views as invalid. Used in response to a, "the whole world might have changed"
     * data change event.
     */
    void markKnownViewsInvalid() {
    }
    /**
     * Invalidates all ItemDecorations. If RecyclerView has item decorations, calling this method
     * will trigger a {@link #requestLayout()} call.
     */
    public void invalidateItemDecorations() {
    }
    /**
     * Returns true if the RecyclerView should attempt to preserve currently focused Adapter Item's
     * focus even if the View representing the Item is replaced during a layout calculation.
     * <p>
     * By default, this value is {@code true}.
     *
     * @return True if the RecyclerView will try to preserve focused Item after a layout if it loses
     * focus.
     *
     * @see #setPreserveFocusAfterLayout(boolean)
     */
    public boolean getPreserveFocusAfterLayout() {
        return mPreserveFocusAfterLayout;
    }
    /**
     * Set whether the RecyclerView should try to keep the same Item focused after a layout
     * calculation or not.
     * <p>
     * Usually, LayoutManagers keep focused views visible before and after layout but sometimes,
     * views may lose focus during a layout calculation as their state changes or they are replaced
     * with another view due to type change or animation. In these cases, RecyclerView can request
     * focus on the new view automatically.
     *
     * @param preserveFocusAfterLayout Whether RecyclerView should preserve focused Item during a
     *                                 layout calculations. Defaults to true.
     *
     * @see #getPreserveFocusAfterLayout()
     */
    public void setPreserveFocusAfterLayout(boolean preserveFocusAfterLayout) {
        mPreserveFocusAfterLayout = preserveFocusAfterLayout;
    }
    /**
     * Retrieve the {@link ViewHolder} for the given child view.
     *
     * @param child Child of this RecyclerView to query for its ViewHolder
     * @return The child view's ViewHolder
     */
    public ViewHolder getChildViewHolder( View child) {
        final ViewParent parent = child.getParent();
        if (parent != null && parent != this) {
            throw new IllegalArgumentException("View " + child + " is not a direct child of "
                    + this);
        }
        return getChildViewHolderInt(child);
    }
    /**
     * Traverses the ancestors of the given view and returns the item view that contains it and
     * also a direct child of the RecyclerView. This returned view can be used to get the
     * ViewHolder by calling {@link #getChildViewHolder(View)}.
     *
     * @param view The view that is a descendant of the RecyclerView.
     *
     * @return The direct child of the RecyclerView which contains the given view or null if the
     * provided view is not a descendant of this RecyclerView.
     *
     * @see #getChildViewHolder(View)
     * @see #findContainingViewHolder(View)
     */
    
    public View findContainingItemView( View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && parent instanceof View) {
            view = (View) parent;
            parent = view.getParent();
        }
        return parent == this ? view : null;
    }
    /**
     * Returns the ViewHolder that contains the given view.
     *
     * @param view The view that is a descendant of the RecyclerView.
     *
     * @return The ViewHolder that contains the given view or null if the provided view is not a
     * descendant of this RecyclerView.
     */
    
    public ViewHolder findContainingViewHolder( View view) {
        View itemView = findContainingItemView(view);
        return itemView == null ? null : getChildViewHolder(itemView);
    }
    static ViewHolder getChildViewHolderInt(View child) {
        if (child == null) {
            return null;
        }
        return ((LayoutParams) child.getLayoutParams()).mViewHolder;
    }
    /**
     * @deprecated use {@link #getChildAdapterPosition(View)} or
     * {@link #getChildLayoutPosition(View)}.
     */
    @Deprecated
    public int getChildPosition( View child) {
        return getChildAdapterPosition(child);
    }
    /**
     * Return the adapter position that the given child view corresponds to.
     *
     * @param child Child View to query
     * @return Adapter position corresponding to the given view or {@link #NO_POSITION}
     */
    public int getChildAdapterPosition( View child) {
        final ViewHolder holder = getChildViewHolderInt(child);
        return holder != null ? holder.getAdapterPosition() : NO_POSITION;
    }
    /**
     * Return the adapter position of the given child view as of the latest completed layout pass.
     * <p>
     * This position may not be equal to Item's adapter position if there are pending changes
     * in the adapter which have not been reflected to the layout yet.
     *
     * @param child Child View to query
     * @return Adapter position of the given View as of last layout pass or {@link #NO_POSITION} if
     * the View is representing a removed item.
     */
    public int getChildLayoutPosition( View child) {
        final ViewHolder holder = getChildViewHolderInt(child);
        return holder != null ? holder.getLayoutPosition() : NO_POSITION;
    }
    /**
     * Return the stable item id that the given child view corresponds to.
     *
     * @param child Child View to query
     * @return Item id corresponding to the given view or {@link #NO_ID}
     */
    public long getChildItemId( View child) {
        if (mAdapter == null || !mAdapter.hasStableIds()) {
            return NO_ID;
        }
        final ViewHolder holder = getChildViewHolderInt(child);
        return holder != null ? holder.getItemId() : NO_ID;
    }
    @Override
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        return super.drawChild(canvas, child, drawingTime);
    }
    /**
     * Called when an item view is attached to this RecyclerView.
     *
     * <p>Subclasses of RecyclerView may want to perform extra bookkeeping or modifications
     * of child views as they become attached. This will be called before a
     * {@link LayoutManager} measures or lays out the view and is a good time to perform these
     * changes.</p>
     *
     * @param child Child view that is now attached to this RecyclerView and its associated window
     */
    public void onChildAttachedToWindow( View child) {
    }
    /**
     * Called when an item view is detached from this RecyclerView.
     *
     * <p>Subclasses of RecyclerView may want to perform extra bookkeeping or modifications
     * of child views as they become detached. This will be called as a
     * {@link LayoutManager} fully detaches the child view from the parent and its window.</p>
     *
     * @param child Child view that is now detached from this RecyclerView and its associated window
     */
    public void onChildDetachedFromWindow( View child) {
    }
    /**
     * Returns the bounds of the view including its decoration and margins.
     *
     * @param view The view element to check
     * @param outBounds A rect that will receive the bounds of the element including its
     *                  decoration and margins.
     */
    public void getDecoratedBoundsWithMargins( View view,  Rect outBounds) {
        getDecoratedBoundsWithMarginsInt(view, outBounds);
    }
    static void getDecoratedBoundsWithMarginsInt(View view, Rect outBounds) {
        final LayoutParams lp = (LayoutParams) view.getLayoutParams();
        final Rect insets = lp.mDecorInsets;
        outBounds.set(view.getLeft() - insets.left - lp.leftMargin,
                view.getTop() - insets.top - lp.topMargin,
                view.getRight() + insets.right + lp.rightMargin,
                view.getBottom() + insets.bottom + lp.bottomMargin);
    }
    Rect getItemDecorInsetsForChild(View child) {
        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        if (!lp.mInsetsDirty) {
            return lp.mDecorInsets;
        }
        if (mState.isPreLayout() && (lp.isItemChanged() || lp.isViewInvalid())) {
            // changed/invalid items should not be updated until they are rebound.
            return lp.mDecorInsets;
        }
        final Rect insets = lp.mDecorInsets;
        insets.set(0, 0, 0, 0);
        final int decorCount = mItemDecorations.size();
        for (int i = 0; i < decorCount; i++) {
            mTempRect.set(0, 0, 0, 0);
            mItemDecorations.get(i).getItemOffsets(mTempRect, child, this, mState);
            insets.left += mTempRect.left;
            insets.top += mTempRect.top;
            insets.right += mTempRect.right;
            insets.bottom += mTempRect.bottom;
        }
        lp.mInsetsDirty = false;
        return insets;
    }
    /**
     * Called when the scroll position of this RecyclerView changes. Subclasses should use
     * this method to respond to scrolling within the adapter's data set instead of an explicit
     * listener.
     *
     * <p>This method will always be invoked before listeners. If a subclass needs to perform
     * any additional upkeep or bookkeeping after scrolling but before listeners run,
     * this is a good place to do so.</p>
     *
     * <p>This differs from {@link View#onScrollChanged(int, int, int, int)} in that it receives
     * the distance scrolled in either direction within the adapter's data set instead of absolute
     * scroll coordinates. Since RecyclerView cannot compute the absolute scroll position from
     * any arbitrary point in the data set, <code>onScrollChanged</code> will always receive
     * the current {@link View#getScrollX()} and {@link View#getScrollY()} values which
     * do not correspond to the data set scroll position. However, some subclasses may choose
     * to use these fields as special offsets.</p>
     *
     * @param dx horizontal distance scrolled in pixels
     * @param dy vertical distance scrolled in pixels
     */
    public void onScrolled( int dx,  int dy) {
        // Do nothing
    }
    void dispatchOnScrolled(int hresult, int vresult) {
        mDispatchScrollCounter++;
        // Pass the current scrollX/scrollY values; no actual change in these properties occurred
        // but some general-purpose code may choose to respond to changes this way.
        final int scrollX = getScrollX();
        final int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX, scrollY);
        // Pass the real deltas to onScrolled, the RecyclerView-specific method.
        onScrolled(hresult, vresult);
        // Invoke listeners last. Subclassed view methods always handle the event first.
        // All internal state is consistent by the time listeners are invoked.
        if (mScrollListener != null) {
            mScrollListener.onScrolled(this, hresult, vresult);
        }
        if (mScrollListeners != null) {
            for (int i = mScrollListeners.size() - 1; i >= 0; i--) {
                mScrollListeners.get(i).onScrolled(this, hresult, vresult);
            }
        }
        mDispatchScrollCounter--;
    }
    /**
     * Called when the scroll state of this RecyclerView changes. Subclasses should use this
     * method to respond to state changes instead of an explicit listener.
     *
     * <p>This method will always be invoked before listeners, but after the LayoutManager
     * responds to the scroll state change.</p>
     *
     * @param state the new scroll state, one of {@link #SCROLL_STATE_IDLE},
     *              {@link #SCROLL_STATE_DRAGGING} or {@link #SCROLL_STATE_SETTLING}
     */
    public void onScrollStateChanged(int state) {
        // Do nothing
    }
    void repositionShadowingViews() {
        // Fix up shadow views used by change animations
        
    }
    private class RecyclerViewDataObserver extends AdapterDataObserver {
        RecyclerViewDataObserver() {
        }
        @Override
        public void onChanged() {
           
        }
        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
       
        }
        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
          
        }
        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
           
        }
        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
           
        }
        void triggerUpdateProcessor() {
           
        }
    }
    /**
     * RecycledViewPool lets you share Views between multiple RecyclerViews.
     * <p>
     * If you want to recycle views across RecyclerViews, create an instance of RecycledViewPool
     * and use {@link RecyclerView#setRecycledViewPool(RecycledViewPool)}.
     * <p>
     * RecyclerView automatically creates a pool for itself if you don't provide one.
     */
    public static class RecycledViewPool {
        private static final int DEFAULT_MAX_SCRAP = 5;
        /**
         * Tracks both pooled holders, as well as create/bind timing metadata for the given type.
         *
         * Note that this tracks running averages of create/bind time across all RecyclerViews
         * (and, indirectly, Adapters) that use this pool.
         *
         * 1) This enables us to track average create and bind times across multiple adapters. Even
         * though create (and especially bind) may behave differently for different Adapter
         * subclasses, sharing the pool is a strong signal that they'll perform similarly, per type.
         *
         * 2) If {@link #willBindInTime(int, long, long)} returns false for one view, it will return
         * false for all other views of its type for the same deadline. This prevents items
         * constructed by {@link GapWorker} prefetch from being bound to a lower priority prefetch.
         */
        static class ScrapData {
            final ArrayList<ViewHolder> mScrapHeap = new ArrayList<>();
            int mMaxScrap = DEFAULT_MAX_SCRAP;
            long mCreateRunningAverageNs = 0;
            long mBindRunningAverageNs = 0;
        }
        SparseArray<ScrapData> mScrap = new SparseArray<>();
        private int mAttachCount = 0;
        /**
         * Discard all ViewHolders.
         */
        public void clear() {
            for (int i = 0; i < mScrap.size(); i++) {
                ScrapData data = mScrap.valueAt(i);
                data.mScrapHeap.clear();
            }
        }
        /**
         * Sets the maximum number of ViewHolders to hold in the pool before discarding.
         *
         * @param viewType ViewHolder Type
         * @param max Maximum number
         */
        public void setMaxRecycledViews(int viewType, int max) {
            ScrapData scrapData = getScrapDataForType(viewType);
            scrapData.mMaxScrap = max;
            final ArrayList<ViewHolder> scrapHeap = scrapData.mScrapHeap;
            while (scrapHeap.size() > max) {
                scrapHeap.remove(scrapHeap.size() - 1);
            }
        }
        /**
         * Returns the current number of Views held by the RecycledViewPool of the given view type.
         */
        public int getRecycledViewCount(int viewType) {
            return getScrapDataForType(viewType).mScrapHeap.size();
        }
        /**
         * Acquire a ViewHolder of the specified type from the pool, or {@code null} if none are
         * present.
         *
         * @param viewType ViewHolder type.
         * @return ViewHolder of the specified type acquired from the pool, or {@code null} if none
         * are present.
         */
        
        public ViewHolder getRecycledView(int viewType) {
            final ScrapData scrapData = mScrap.get(viewType);
            if (scrapData != null && !scrapData.mScrapHeap.isEmpty()) {
                final ArrayList<ViewHolder> scrapHeap = scrapData.mScrapHeap;
                for (int i = scrapHeap.size() - 1; i >= 0; i--) {
                    if (!scrapHeap.get(i).isAttachedToTransitionOverlay()) {
                        return scrapHeap.remove(i);
                    }
                }
            }
            return null;
        }
        /**
         * Total number of ViewHolders held by the pool.
         *
         * @return Number of ViewHolders held by the pool.
         */
        int size() {
            int count = 0;
            for (int i = 0; i < mScrap.size(); i++) {
                ArrayList<ViewHolder> viewHolders = mScrap.valueAt(i).mScrapHeap;
                if (viewHolders != null) {
                    count += viewHolders.size();
                }
            }
            return count;
        }
        /**
         * Add a scrap ViewHolder to the pool.
         * <p>
         * If the pool is already full for that ViewHolder's type, it will be immediately discarded.
         *
         * @param scrap ViewHolder to be added to the pool.
         */
        public void putRecycledView(ViewHolder scrap) {
            final int viewType = scrap.getItemViewType();
            final ArrayList<ViewHolder> scrapHeap = getScrapDataForType(viewType).mScrapHeap;
            if (mScrap.get(viewType).mMaxScrap <= scrapHeap.size()) {
                return;
            }
            if (DEBUG && scrapHeap.contains(scrap)) {
                throw new IllegalArgumentException("this scrap item already exists");
            }
            scrap.resetInternal();
            scrapHeap.add(scrap);
        }
        long runningAverage(long oldAverage, long newValue) {
            if (oldAverage == 0) {
                return newValue;
            }
            return (oldAverage / 4 * 3) + (newValue / 4);
        }
        void factorInCreateTime(int viewType, long createTimeNs) {
            ScrapData scrapData = getScrapDataForType(viewType);
            scrapData.mCreateRunningAverageNs = runningAverage(
                    scrapData.mCreateRunningAverageNs, createTimeNs);
        }
        void factorInBindTime(int viewType, long bindTimeNs) {
            ScrapData scrapData = getScrapDataForType(viewType);
            scrapData.mBindRunningAverageNs = runningAverage(
                    scrapData.mBindRunningAverageNs, bindTimeNs);
        }
        boolean willCreateInTime(int viewType, long approxCurrentNs, long deadlineNs) {
            long expectedDurationNs = getScrapDataForType(viewType).mCreateRunningAverageNs;
            return expectedDurationNs == 0 || (approxCurrentNs + expectedDurationNs < deadlineNs);
        }
        boolean willBindInTime(int viewType, long approxCurrentNs, long deadlineNs) {
            long expectedDurationNs = getScrapDataForType(viewType).mBindRunningAverageNs;
            return expectedDurationNs == 0 || (approxCurrentNs + expectedDurationNs < deadlineNs);
        }
        void attach() {
            mAttachCount++;
        }
        void detach() {
            mAttachCount--;
        }
        /**
         * Detaches the old adapter and attaches the new one.
         * <p>
         * RecycledViewPool will clear its cache if it has only one adapter attached and the new
         * adapter uses a different ViewHolder than the oldAdapter.
         *
         * @param oldAdapter The previous adapter instance. Will be detached.
         * @param newAdapter The new adapter instance. Will be attached.
         * @param compatibleWithPrevious True if both oldAdapter and newAdapter are using the same
         *                               ViewHolder and view types.
         */
        void onAdapterChanged(Adapter oldAdapter, Adapter newAdapter,
                boolean compatibleWithPrevious) {
            if (oldAdapter != null) {
                detach();
            }
            if (!compatibleWithPrevious && mAttachCount == 0) {
                clear();
            }
            if (newAdapter != null) {
                attach();
            }
        }
        private ScrapData getScrapDataForType(int viewType) {
            ScrapData scrapData = mScrap.get(viewType);
            if (scrapData == null) {
                scrapData = new ScrapData();
                mScrap.put(viewType, scrapData);
            }
            return scrapData;
        }
    }
    /**
     * Utility method for finding an internal RecyclerView, if present
     */
    static RecyclerView findNestedRecyclerView(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        final ViewGroup parent = (ViewGroup) view;
        final int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView descendant = findNestedRecyclerView(child);
            if (descendant != null) {
                return descendant;
            }
        }
        return null;
    }
    /**
     * Utility method for clearing holder's internal RecyclerView, if present
     */
    static void clearNestedRecyclerViewIfNotNested(ViewHolder holder) {
        if (holder.mNestedRecyclerView != null) {
            View item = holder.mNestedRecyclerView.get();
            while (item != null) {
                if (item == holder.itemView) {
                    return; // match found, don't need to clear
                }
                ViewParent parent = item.getParent();
                if (parent instanceof View) {
                    item = (View) parent;
                } else {
                    item = null;
                }
            }
            holder.mNestedRecyclerView = null; // not nested
        }
    }
    /**
     * Time base for deadline-aware work scheduling. Overridable for testing.
     *
     * Will return 0 to avoid cost of System.nanoTime where deadline-aware work scheduling
     * isn't relevant.
     */
    long getNanoTime() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        } else {
            return 0;
        }
    }
    /**
     * A Recycler is responsible for managing scrapped or detached item views for reuse.
     *
     * <p>A "scrapped" view is a view that is still attached to its parent RecyclerView but
     * that has been marked for removal or reuse.</p>
     *
     * <p>Typical use of a Recycler by a {@link LayoutManager} will be to obtain views for
     * an adapter's data set representing the data at a given position or item ID.
     * If the view to be reused is considered "dirty" the adapter will be asked to rebind it.
     * If not, the view can be quickly reused by the LayoutManager with no further work.
     * Clean views that have not {@link android.view.View#isLayoutRequested() requested layout}
     * may be repositioned by a LayoutManager without remeasurement.</p>
     */
    public final class Recycler {

		public void clear() {
			// TODO Auto-generated method stub
			
		}
    }
    /**
     * ViewCacheExtension is a helper class to provide an additional layer of view caching that can
     * be controlled by the developer.
     * <p>
     * When {@link Recycler#getViewForPosition(int)} is called, Recycler checks attached scrap and
     * first level cache to find a matching View. If it cannot find a suitable View, Recycler will
     * call the {@link #getViewForPositionAndType(Recycler, int, int)} before checking
     * {@link RecycledViewPool}.
     * <p>
     * Note that, Recycler never sends Views to this method to be cached. It is developers
     * responsibility to decide whether they want to keep their Views in this custom cache or let
     * the default recycling policy handle it.
     */
    public abstract static class ViewCacheExtension {
        /**
         * Returns a View that can be binded to the given Adapter position.
         * <p>
         * This method should <b>not</b> create a new View. Instead, it is expected to return
         * an already created View that can be re-used for the given type and position.
         * If the View is marked as ignored, it should first call
         * {@link LayoutManager#stopIgnoringView(View)} before returning the View.
         * <p>
         * RecyclerView will re-bind the returned View to the position if necessary.
         *
         * @param recycler The Recycler that can be used to bind the View
         * @param position The adapter position
         * @param type     The type of the View, defined by adapter
         * @return A View that is bound to the given position or NULL if there is no View to re-use
         * @see LayoutManager#ignoreView(View)
         */
        
        public abstract View getViewForPositionAndType( Recycler recycler, int position,
                int type);
    }
    /**
     * Base class for an Adapter
     *
     * <p>Adapters provide a binding from an app-specific data set to views that are displayed
     * within a {@link RecyclerView}.</p>
     *
     * @param <VH> A class that extends ViewHolder that will be used by the adapter.
     */
    public abstract static class Adapter<VH extends ViewHolder> {
        private final AdapterDataObservable mObservable = new AdapterDataObservable();
        private boolean mHasStableIds = false;
        /**
         * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
         * an item.
         * <p>
         * This new ViewHolder should be constructed with a new View that can represent the items
         * of the given type. You can either create a new View manually or inflate it from an XML
         * layout file.
         * <p>
         * The new ViewHolder will be used to display items of the adapter using
         * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
         * different items in the data set, it is a good idea to cache references to sub views of
         * the View to avoid unnecessary {@link View#findViewById(int)} calls.
         *
         * @param parent The ViewGroup into which the new View will be added after it is bound to
         *               an adapter position.
         * @param viewType The view type of the new View.
         *
         * @return A new ViewHolder that holds a View of the given view type.
         * @see #getItemViewType(int)
         * @see #onBindViewHolder(ViewHolder, int)
         */
        
        public abstract VH onCreateViewHolder( ViewGroup parent, int viewType);
        /**
         * Called by RecyclerView to display the data at the specified position. This method should
         * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
         * position.
         * <p>
         * Note that unlike {@link android.widget.ListView}, RecyclerView will not call this method
         * again if the position of the item changes in the data set unless the item itself is
         * invalidated or the new position cannot be determined. For this reason, you should only
         * use the <code>position</code> parameter while acquiring the related data item inside
         * this method and should not keep a copy of it. If you need the position of an item later
         * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
         * have the updated adapter position.
         *
         * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
         * handle efficient partial bind.
         *
         * @param holder The ViewHolder which should be updated to represent the contents of the
         *        item at the given position in the data set.
         * @param position The position of the item within the adapter's data set.
         */
        public abstract void onBindViewHolder( VH holder, int position);
        /**
         * Called by RecyclerView to display the data at the specified position. This method
         * should update the contents of the {@link ViewHolder#itemView} to reflect the item at
         * the given position.
         * <p>
         * Note that unlike {@link android.widget.ListView}, RecyclerView will not call this method
         * again if the position of the item changes in the data set unless the item itself is
         * invalidated or the new position cannot be determined. For this reason, you should only
         * use the <code>position</code> parameter while acquiring the related data item inside
         * this method and should not keep a copy of it. If you need the position of an item later
         * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
         * have the updated adapter position.
         * <p>
         * Partial bind vs full bind:
         * <p>
         * The payloads parameter is a merge list from {@link #notifyItemChanged(int, Object)} or
         * {@link #notifyItemRangeChanged(int, int, Object)}.  If the payloads list is not empty,
         * the ViewHolder is currently bound to old data and Adapter may run an efficient partial
         * update using the payload info.  If the payload is empty,  Adapter must run a full bind.
         * Adapter should not assume that the payload passed in notify methods will be received by
         * onBindViewHolder().  For example when the view is not attached to the screen, the
         * payload in notifyItemChange() will be simply dropped.
         *
         * @param holder The ViewHolder which should be updated to represent the contents of the
         *               item at the given position in the data set.
         * @param position The position of the item within the adapter's data set.
         * @param payloads A non-null list of merged payloads. Can be empty list if requires full
         *                 update.
         */
        public void onBindViewHolder( VH holder, int position,
                 List<Object> payloads) {
            onBindViewHolder(holder, position);
        }
        /**
         * This method calls {@link #onCreateViewHolder(ViewGroup, int)} to create a new
         * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
         *
         * @see #onCreateViewHolder(ViewGroup, int)
         */
        
        public final VH createViewHolder( ViewGroup parent, int viewType) {
            try {
                
                final VH holder = onCreateViewHolder(parent, viewType);
                if (holder.itemView.getParent() != null) {
                    throw new IllegalStateException("ViewHolder views must not be attached when"
                            + " created. Ensure that you are not passing 'true' to the attachToRoot"
                            + " parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
                }
                holder.mItemViewType = viewType;
                return holder;
            } finally {
                
            }
        }
        /**
         * This method internally calls {@link #onBindViewHolder(ViewHolder, int)} to update the
         * {@link ViewHolder} contents with the item at the given position and also sets up some
         * private fields to be used by RecyclerView.
         *
         * @see #onBindViewHolder(ViewHolder, int)
         */
        public final void bindViewHolder( VH holder, int position) {
            holder.mPosition = position;
            if (hasStableIds()) {
                holder.mItemId = getItemId(position);
            }
            holder.setFlags(ViewHolder.FLAG_BOUND,
                    ViewHolder.FLAG_BOUND | ViewHolder.FLAG_UPDATE | ViewHolder.FLAG_INVALID
                            | ViewHolder.FLAG_ADAPTER_POSITION_UNKNOWN);            
            onBindViewHolder(holder, position, holder.getUnmodifiedPayloads());
            holder.clearPayload();
            final ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof RecyclerView.LayoutParams) {
                ((LayoutParams) layoutParams).mInsetsDirty = true;
            }
            
        }
        /**
         * Return the view type of the item at <code>position</code> for the purposes
         * of view recycling.
         *
         * <p>The default implementation of this method returns 0, making the assumption of
         * a single view type for the adapter. Unlike ListView adapters, types need not
         * be contiguous. Consider using id resources to uniquely identify item view types.
         *
         * @param position position to query
         * @return integer value identifying the type of the view needed to represent the item at
         *                 <code>position</code>. Type codes need not be contiguous.
         */
        public int getItemViewType(int position) {
            return 0;
        }
        /**
         * Indicates whether each item in the data set can be represented with a unique identifier
         * of type {@link java.lang.Long}.
         *
         * @param hasStableIds Whether items in data set have unique identifiers or not.
         * @see #hasStableIds()
         * @see #getItemId(int)
         */
        public void setHasStableIds(boolean hasStableIds) {
            if (hasObservers()) {
                throw new IllegalStateException("Cannot change whether this adapter has "
                        + "stable IDs while the adapter has registered observers.");
            }
            mHasStableIds = hasStableIds;
        }
        /**
         * Return the stable ID for the item at <code>position</code>. If {@link #hasStableIds()}
         * would return false this method should return {@link #NO_ID}. The default implementation
         * of this method returns {@link #NO_ID}.
         *
         * @param position Adapter position to query
         * @return the stable ID of the item at position
         */
        public long getItemId(int position) {
            return NO_ID;
        }
        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        public abstract int getItemCount();
        /**
         * Returns true if this adapter publishes a unique <code>long</code> value that can
         * act as a key for the item at a given position in the data set. If that item is relocated
         * in the data set, the ID returned for that item should be the same.
         *
         * @return true if this adapter's items have stable IDs
         */
        public final boolean hasStableIds() {
            return mHasStableIds;
        }
        /**
         * Called when a view created by this adapter has been recycled.
         *
         * <p>A view is recycled when a {@link LayoutManager} decides that it no longer
         * needs to be attached to its parent {@link RecyclerView}. This can be because it has
         * fallen out of visibility or a set of cached views represented by views still
         * attached to the parent RecyclerView. If an item view has large or expensive data
         * bound to it such as large bitmaps, this may be a good place to release those
         * resources.</p>
         * <p>
         * RecyclerView calls this method right before clearing ViewHolder's internal data and
         * sending it to RecycledViewPool. This way, if ViewHolder was holding valid information
         * before being recycled, you can call {@link ViewHolder#getAdapterPosition()} to get
         * its adapter position.
         *
         * @param holder The ViewHolder for the view being recycled
         */
        public void onViewRecycled( VH holder) {
        }
        /**
         * Called by the RecyclerView if a ViewHolder created by this Adapter cannot be recycled
         * due to its transient state. Upon receiving this callback, Adapter can clear the
         * animation(s) that effect the View's transient state and return <code>true</code> so that
         * the View can be recycled. Keep in mind that the View in question is already removed from
         * the RecyclerView.
         * <p>
         * In some cases, it is acceptable to recycle a View although it has transient state. Most
         * of the time, this is a case where the transient state will be cleared in
         * {@link #onBindViewHolder(ViewHolder, int)} call when View is rebound to a new position.
         * For this reason, RecyclerView leaves the decision to the Adapter and uses the return
         * value of this method to decide whether the View should be recycled or not.
         * <p>
         * Note that when all animations are created by {@link RecyclerView.ItemAnimator}, you
         * should never receive this callback because RecyclerView keeps those Views as children
         * until their animations are complete. This callback is useful when children of the item
         * views create animations which may not be easy to implement using an {@link ItemAnimator}.
         * <p>
         * You should <em>never</em> fix this issue by calling
         * <code>holder.itemView.setHasTransientState(false);</code> unless you've previously called
         * <code>holder.itemView.setHasTransientState(true);</code>. Each
         * <code>View.setHasTransientState(true)</code> call must be matched by a
         * <code>View.setHasTransientState(false)</code> call, otherwise, the state of the View
         * may become inconsistent. You should always prefer to end or cancel animations that are
         * triggering the transient state instead of handling it manually.
         *
         * @param holder The ViewHolder containing the View that could not be recycled due to its
         *               transient state.
         * @return True if the View should be recycled, false otherwise. Note that if this method
         * returns <code>true</code>, RecyclerView <em>will ignore</em> the transient state of
         * the View and recycle it regardless. If this method returns <code>false</code>,
         * RecyclerView will check the View's transient state again before giving a final decision.
         * Default implementation returns false.
         */
        public boolean onFailedToRecycleView( VH holder) {
            return false;
        }
        /**
         * Called when a view created by this adapter has been attached to a window.
         *
         * <p>This can be used as a reasonable signal that the view is about to be seen
         * by the user. If the adapter previously freed any resources in
         * {@link #onViewDetachedFromWindow(RecyclerView.ViewHolder) onViewDetachedFromWindow}
         * those resources should be restored here.</p>
         *
         * @param holder Holder of the view being attached
         */
        public void onViewAttachedToWindow( VH holder) {
        }
        /**
         * Called when a view created by this adapter has been detached from its window.
         *
         * <p>Becoming detached from the window is not necessarily a permanent condition;
         * the consumer of an Adapter's views may choose to cache views offscreen while they
         * are not visible, attaching and detaching them as appropriate.</p>
         *
         * @param holder Holder of the view being detached
         */
        public void onViewDetachedFromWindow( VH holder) {
        }
        /**
         * Returns true if one or more observers are attached to this adapter.
         *
         * @return true if this adapter has observers
         */
        public final boolean hasObservers() {
            return mObservable.hasObservers();
        }
        /**
         * Register a new observer to listen for data changes.
         *
         * <p>The adapter may publish a variety of events describing specific changes.
         * Not all adapters may support all change types and some may fall back to a generic
         * {@link RecyclerView.AdapterDataObserver#onChanged()
         * "something changed"} event if more specific data is not available.</p>
         *
         * <p>Components registering observers with an adapter are responsible for
         * {@link #unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver)
         * unregistering} those observers when finished.</p>
         *
         * @param observer Observer to register
         *
         * @see #unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver)
         */
        public void registerAdapterDataObserver( AdapterDataObserver observer) {
            mObservable.registerObserver(observer);
        }
        /**
         * Unregister an observer currently listening for data changes.
         *
         * <p>The unregistered observer will no longer receive events about changes
         * to the adapter.</p>
         *
         * @param observer Observer to unregister
         *
         * @see #registerAdapterDataObserver(RecyclerView.AdapterDataObserver)
         */
        public void unregisterAdapterDataObserver( AdapterDataObserver observer) {
            mObservable.unregisterObserver(observer);
        }
        /**
         * Called by RecyclerView when it starts observing this Adapter.
         * <p>
         * Keep in mind that same adapter may be observed by multiple RecyclerViews.
         *
         * @param recyclerView The RecyclerView instance which started observing this adapter.
         * @see #onDetachedFromRecyclerView(RecyclerView)
         */
        public void onAttachedToRecyclerView( RecyclerView recyclerView) {
        }
        /**
         * Called by RecyclerView when it stops observing this Adapter.
         *
         * @param recyclerView The RecyclerView instance which stopped observing this adapter.
         * @see #onAttachedToRecyclerView(RecyclerView)
         */
        public void onDetachedFromRecyclerView( RecyclerView recyclerView) {
        }
        /**
         * Notify any registered observers that the data set has changed.
         *
         * <p>There are two different classes of data change events, item changes and structural
         * changes. Item changes are when a single item has its data updated but no positional
         * changes have occurred. Structural changes are when items are inserted, removed or moved
         * within the data set.</p>
         *
         * <p>This event does not specify what about the data set has changed, forcing
         * any observers to assume that all existing items and structure may no longer be valid.
         * LayoutManagers will be forced to fully rebind and relayout all visible views.</p>
         *
         * <p><code>RecyclerView</code> will attempt to synthesize visible structural change events
         * for adapters that report that they have {@link #hasStableIds() stable IDs} when
         * this method is used. This can help for the purposes of animation and visual
         * object persistence but individual item views will still need to be rebound
         * and relaid out.</p>
         *
         * <p>If you are writing an adapter it will always be more efficient to use the more
         * specific change events if you can. Rely on <code>notifyDataSetChanged()</code>
         * as a last resort.</p>
         *
         * @see #notifyItemChanged(int)
         * @see #notifyItemInserted(int)
         * @see #notifyItemRemoved(int)
         * @see #notifyItemRangeChanged(int, int)
         * @see #notifyItemRangeInserted(int, int)
         * @see #notifyItemRangeRemoved(int, int)
         */
        public final void notifyDataSetChanged() {
            mObservable.notifyChanged();
        }
        /**
         * Notify any registered observers that the item at <code>position</code> has changed.
         * Equivalent to calling <code>notifyItemChanged(position, null);</code>.
         *
         * <p>This is an item change event, not a structural change event. It indicates that any
         * reflection of the data at <code>position</code> is out of date and should be updated.
         * The item at <code>position</code> retains the same identity.</p>
         *
         * @param position Position of the item that has changed
         *
         * @see #notifyItemRangeChanged(int, int)
         */
        public final void notifyItemChanged(int position) {
            mObservable.notifyItemRangeChanged(position, 1);
        }
        /**
         * Notify any registered observers that the item at <code>position</code> has changed with
         * an optional payload object.
         *
         * <p>This is an item change event, not a structural change event. It indicates that any
         * reflection of the data at <code>position</code> is out of date and should be updated.
         * The item at <code>position</code> retains the same identity.
         * </p>
         *
         * <p>
         * Client can optionally pass a payload for partial change. These payloads will be merged
         * and may be passed to adapter's {@link #onBindViewHolder(ViewHolder, int, List)} if the
         * item is already represented by a ViewHolder and it will be rebound to the same
         * ViewHolder. A notifyItemRangeChanged() with null payload will clear all existing
         * payloads on that item and prevent future payload until
         * {@link #onBindViewHolder(ViewHolder, int, List)} is called. Adapter should not assume
         * that the payload will always be passed to onBindViewHolder(), e.g. when the view is not
         * attached, the payload will be simply dropped.
         *
         * @param position Position of the item that has changed
         * @param payload Optional parameter, use null to identify a "full" update
         *
         * @see #notifyItemRangeChanged(int, int)
         */
        public final void notifyItemChanged(int position,  Object payload) {
            mObservable.notifyItemRangeChanged(position, 1, payload);
        }
        /**
         * Notify any registered observers that the <code>itemCount</code> items starting at
         * position <code>positionStart</code> have changed.
         * Equivalent to calling <code>notifyItemRangeChanged(position, itemCount, null);</code>.
         *
         * <p>This is an item change event, not a structural change event. It indicates that
         * any reflection of the data in the given position range is out of date and should
         * be updated. The items in the given range retain the same identity.</p>
         *
         * @param positionStart Position of the first item that has changed
         * @param itemCount Number of items that have changed
         *
         * @see #notifyItemChanged(int)
         */
        public final void notifyItemRangeChanged(int positionStart, int itemCount) {
            mObservable.notifyItemRangeChanged(positionStart, itemCount);
        }
        /**
         * Notify any registered observers that the <code>itemCount</code> items starting at
         * position <code>positionStart</code> have changed. An optional payload can be
         * passed to each changed item.
         *
         * <p>This is an item change event, not a structural change event. It indicates that any
         * reflection of the data in the given position range is out of date and should be updated.
         * The items in the given range retain the same identity.
         * </p>
         *
         * <p>
         * Client can optionally pass a payload for partial change. These payloads will be merged
         * and may be passed to adapter's {@link #onBindViewHolder(ViewHolder, int, List)} if the
         * item is already represented by a ViewHolder and it will be rebound to the same
         * ViewHolder. A notifyItemRangeChanged() with null payload will clear all existing
         * payloads on that item and prevent future payload until
         * {@link #onBindViewHolder(ViewHolder, int, List)} is called. Adapter should not assume
         * that the payload will always be passed to onBindViewHolder(), e.g. when the view is not
         * attached, the payload will be simply dropped.
         *
         * @param positionStart Position of the first item that has changed
         * @param itemCount Number of items that have changed
         * @param payload  Optional parameter, use null to identify a "full" update
         *
         * @see #notifyItemChanged(int)
         */
        public final void notifyItemRangeChanged(int positionStart, int itemCount,
                 Object payload) {
            mObservable.notifyItemRangeChanged(positionStart, itemCount, payload);
        }
        /**
         * Notify any registered observers that the item reflected at <code>position</code>
         * has been newly inserted. The item previously at <code>position</code> is now at
         * position <code>position + 1</code>.
         *
         * <p>This is a structural change event. Representations of other existing items in the
         * data set are still considered up to date and will not be rebound, though their
         * positions may be altered.</p>
         *
         * @param position Position of the newly inserted item in the data set
         *
         * @see #notifyItemRangeInserted(int, int)
         */
        public final void notifyItemInserted(int position) {
            mObservable.notifyItemRangeInserted(position, 1);
        }
        /**
         * Notify any registered observers that the item reflected at <code>fromPosition</code>
         * has been moved to <code>toPosition</code>.
         *
         * <p>This is a structural change event. Representations of other existing items in the
         * data set are still considered up to date and will not be rebound, though their
         * positions may be altered.</p>
         *
         * @param fromPosition Previous position of the item.
         * @param toPosition New position of the item.
         */
        public final void notifyItemMoved(int fromPosition, int toPosition) {
            mObservable.notifyItemMoved(fromPosition, toPosition);
        }
        /**
         * Notify any registered observers that the currently reflected <code>itemCount</code>
         * items starting at <code>positionStart</code> have been newly inserted. The items
         * previously located at <code>positionStart</code> and beyond can now be found starting
         * at position <code>positionStart + itemCount</code>.
         *
         * <p>This is a structural change event. Representations of other existing items in the
         * data set are still considered up to date and will not be rebound, though their positions
         * may be altered.</p>
         *
         * @param positionStart Position of the first item that was inserted
         * @param itemCount Number of items inserted
         *
         * @see #notifyItemInserted(int)
         */
        public final void notifyItemRangeInserted(int positionStart, int itemCount) {
            mObservable.notifyItemRangeInserted(positionStart, itemCount);
        }
        /**
         * Notify any registered observers that the item previously located at <code>position</code>
         * has been removed from the data set. The items previously located at and after
         * <code>position</code> may now be found at <code>oldPosition - 1</code>.
         *
         * <p>This is a structural change event. Representations of other existing items in the
         * data set are still considered up to date and will not be rebound, though their positions
         * may be altered.</p>
         *
         * @param position Position of the item that has now been removed
         *
         * @see #notifyItemRangeRemoved(int, int)
         */
        public final void notifyItemRemoved(int position) {
            mObservable.notifyItemRangeRemoved(position, 1);
        }
        /**
         * Notify any registered observers that the <code>itemCount</code> items previously
         * located at <code>positionStart</code> have been removed from the data set. The items
         * previously located at and after <code>positionStart + itemCount</code> may now be found
         * at <code>oldPosition - itemCount</code>.
         *
         * <p>This is a structural change event. Representations of other existing items in the data
         * set are still considered up to date and will not be rebound, though their positions
         * may be altered.</p>
         *
         * @param positionStart Previous position of the first item that was removed
         * @param itemCount Number of items removed from the data set
         */
        public final void notifyItemRangeRemoved(int positionStart, int itemCount) {
            mObservable.notifyItemRangeRemoved(positionStart, itemCount);
        }
    }
    void dispatchChildDetached(View child) {
        final ViewHolder viewHolder = getChildViewHolderInt(child);
        onChildDetachedFromWindow(child);
        if (mAdapter != null && viewHolder != null) {
            mAdapter.onViewDetachedFromWindow(viewHolder);
        }
        if (mOnChildAttachStateListeners != null) {
            final int cnt = mOnChildAttachStateListeners.size();
            for (int i = cnt - 1; i >= 0; i--) {
                mOnChildAttachStateListeners.get(i).onChildViewDetachedFromWindow(child);
            }
        }
    }
    void dispatchChildAttached(View child) {
        final ViewHolder viewHolder = getChildViewHolderInt(child);
        onChildAttachedToWindow(child);
        if (mAdapter != null && viewHolder != null) {
            mAdapter.onViewAttachedToWindow(viewHolder);
        }
        if (mOnChildAttachStateListeners != null) {
            final int cnt = mOnChildAttachStateListeners.size();
            for (int i = cnt - 1; i >= 0; i--) {
                mOnChildAttachStateListeners.get(i).onChildViewAttachedToWindow(child);
            }
        }
    }
    /**
     * An ItemDecoration allows the application to add a special drawing and layout offset
     * to specific item views from the adapter's data set. This can be useful for drawing dividers
     * between items, highlights, visual grouping boundaries and more.
     *
     * <p>All ItemDecorations are drawn in the order they were added, before the item
     * views (in {@link ItemDecoration#onDraw(Canvas, RecyclerView, RecyclerView.State) onDraw()}
     * and after the items (in {@link ItemDecoration#onDrawOver(Canvas, RecyclerView,
     * RecyclerView.State)}.</p>
     */
    public abstract static class ItemDecoration {
        /**
         * Draw any appropriate decorations into the Canvas supplied to the RecyclerView.
         * Any content drawn by this method will be drawn before the item views are drawn,
         * and will thus appear underneath the views.
         *
         * @param c Canvas to draw into
         * @param parent RecyclerView this ItemDecoration is drawing into
         * @param state The current state of RecyclerView
         */
        public void onDraw( Canvas c,  RecyclerView parent,  State state) {
            onDraw(c, parent);
        }
        /**
         * @deprecated
         * Override {@link #onDraw(Canvas, RecyclerView, RecyclerView.State)}
         */
        @Deprecated
        public void onDraw( Canvas c,  RecyclerView parent) {
        }
        /**
         * Draw any appropriate decorations into the Canvas supplied to the RecyclerView.
         * Any content drawn by this method will be drawn after the item views are drawn
         * and will thus appear over the views.
         *
         * @param c Canvas to draw into
         * @param parent RecyclerView this ItemDecoration is drawing into
         * @param state The current state of RecyclerView.
         */
        public void onDrawOver( Canvas c,  RecyclerView parent,
                 State state) {
            onDrawOver(c, parent);
        }
        /**
         * @deprecated
         * Override {@link #onDrawOver(Canvas, RecyclerView, RecyclerView.State)}
         */
        @Deprecated
        public void onDrawOver( Canvas c,  RecyclerView parent) {
        }
        /**
         * @deprecated
         * Use {@link #getItemOffsets(Rect, View, RecyclerView, State)}
         */
        @Deprecated
        public void getItemOffsets( Rect outRect, int itemPosition,
                 RecyclerView parent) {
            outRect.set(0, 0, 0, 0);
        }
        /**
         * Retrieve any offsets for the given item. Each field of <code>outRect</code> specifies
         * the number of pixels that the item view should be inset by, similar to padding or margin.
         * The default implementation sets the bounds of outRect to 0 and returns.
         *
         * <p>
         * If this ItemDecoration does not affect the positioning of item views, it should set
         * all four fields of <code>outRect</code> (left, top, right, bottom) to zero
         * before returning.
         *
         * <p>
         * If you need to access Adapter for additional data, you can call
         * {@link RecyclerView#getChildAdapterPosition(View)} to get the adapter position of the
         * View.
         *
         * @param outRect Rect to receive the output.
         * @param view    The child view to decorate
         * @param parent  RecyclerView this ItemDecoration is decorating
         * @param state   The current state of RecyclerView.
         */
        public void getItemOffsets( Rect outRect,  View view,
                 RecyclerView parent,  State state) {
            getItemOffsets(outRect, ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition(),
                    parent);
        }
    }
    /**
     * An OnItemTouchListener allows the application to intercept touch events in progress at the
     * view hierarchy level of the RecyclerView before those touch events are considered for
     * RecyclerView's own scrolling behavior.
     *
     * <p>This can be useful for applications that wish to implement various forms of gestural
     * manipulation of item views within the RecyclerView. OnItemTouchListeners may intercept
     * a touch interaction already in progress even if the RecyclerView is already handling that
     * gesture stream itself for the purposes of scrolling.</p>
     *
     * @see SimpleOnItemTouchListener
     */
    public interface OnItemTouchListener {
        /**
         * Silently observe and/or take over touch events sent to the RecyclerView
         * before they are handled by either the RecyclerView itself or its child views.
         *
         * <p>The onInterceptTouchEvent methods of each attached OnItemTouchListener will be run
         * in the order in which each listener was added, before any other touch processing
         * by the RecyclerView itself or child views occurs.</p>
         *
         * @param e MotionEvent describing the touch event. All coordinates are in
         *          the RecyclerView's coordinate system.
         * @return true if this OnItemTouchListener wishes to begin intercepting touch events, false
         *         to continue with the current behavior and continue observing future events in
         *         the gesture.
         */
        boolean onInterceptTouchEvent( RecyclerView rv,  MotionEvent e);
        /**
         * Process a touch event as part of a gesture that was claimed by returning true from
         * a previous call to {@link #onInterceptTouchEvent}.
         *
         * @param e MotionEvent describing the touch event. All coordinates are in
         *          the RecyclerView's coordinate system.
         */
        void onTouchEvent( RecyclerView rv,  MotionEvent e);
        /**
         * Called when a child of RecyclerView does not want RecyclerView and its ancestors to
         * intercept touch events with
         * {@link ViewGroup#onInterceptTouchEvent(MotionEvent)}.
         *
         * @param disallowIntercept True if the child does not want the parent to
         *            intercept touch events.
         * @see ViewParent#requestDisallowInterceptTouchEvent(boolean)
         */
        void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept);
    }
    /**
     * An implementation of {@link RecyclerView.OnItemTouchListener} that has empty method bodies
     * and default return values.
     * <p>
     * You may prefer to extend this class if you don't need to override all methods. Another
     * benefit of using this class is future compatibility. As the interface may change, we'll
     * always provide a default implementation on this class so that your code won't break when
     * you update to a new version of the support library.
     */
    public static class SimpleOnItemTouchListener implements RecyclerView.OnItemTouchListener {
        @Override
        public boolean onInterceptTouchEvent( RecyclerView rv,  MotionEvent e) {
            return false;
        }
        @Override
        public void onTouchEvent( RecyclerView rv,  MotionEvent e) {
        }
        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }
    /**
     * An OnScrollListener can be added to a RecyclerView to receive messages when a scrolling event
     * has occurred on that RecyclerView.
     * <p>
     * @see RecyclerView#addOnScrollListener(OnScrollListener)
     * @see RecyclerView#clearOnChildAttachStateChangeListeners()
     *
     */
    public abstract static class OnScrollListener {
        /**
         * Callback method to be invoked when RecyclerView's scroll state changes.
         *
         * @param recyclerView The RecyclerView whose scroll state has changed.
         * @param newState     The updated scroll state. One of {@link #SCROLL_STATE_IDLE},
         *                     {@link #SCROLL_STATE_DRAGGING} or {@link #SCROLL_STATE_SETTLING}.
         */
        public void onScrollStateChanged( RecyclerView recyclerView, int newState){}
        /**
         * Callback method to be invoked when the RecyclerView has been scrolled. This will be
         * called after the scroll has completed.
         * <p>
         * This callback will also be called if visible item range changes after a layout
         * calculation. In that case, dx and dy will be 0.
         *
         * @param recyclerView The RecyclerView which scrolled.
         * @param dx The amount of horizontal scroll.
         * @param dy The amount of vertical scroll.
         */
        public void onScrolled( RecyclerView recyclerView, int dx, int dy){}
    }
    /**
     * A RecyclerListener can be set on a RecyclerView to receive messages whenever
     * a view is recycled.
     *
     * @see RecyclerView#setRecyclerListener(RecyclerListener)
     */
    public interface RecyclerListener {
        /**
         * This method is called whenever the view in the ViewHolder is recycled.
         *
         * RecyclerView calls this method right before clearing ViewHolder's internal data and
         * sending it to RecycledViewPool. This way, if ViewHolder was holding valid information
         * before being recycled, you can call {@link ViewHolder#getAdapterPosition()} to get
         * its adapter position.
         *
         * @param holder The ViewHolder containing the view that was recycled
         */
        void onViewRecycled( ViewHolder holder);
    }
    /**
     * A Listener interface that can be attached to a RecylcerView to get notified
     * whenever a ViewHolder is attached to or detached from RecyclerView.
     */
    public interface OnChildAttachStateChangeListener {
        /**
         * Called when a view is attached to the RecyclerView.
         *
         * @param view The View which is attached to the RecyclerView
         */
        void onChildViewAttachedToWindow( View view);
        /**
         * Called when a view is detached from RecyclerView.
         *
         * @param view The View which is being detached from the RecyclerView
         */
        void onChildViewDetachedFromWindow( View view);
    }
    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     *
     * <p>{@link Adapter} implementations should subclass ViewHolder and add fields for caching
     * potentially expensive {@link View#findViewById(int)} results.</p>
     *
     * <p>While {@link LayoutParams} belong to the {@link LayoutManager},
     * {@link ViewHolder ViewHolders} belong to the adapter. Adapters should feel free to use
     * their own custom ViewHolder implementations to store data that makes binding view contents
     * easier. Implementations should assume that individual item views will hold strong references
     * to <code>ViewHolder</code> objects and that <code>RecyclerView</code> instances may hold
     * strong references to extra off-screen item views for caching purposes</p>
     */
    public abstract static class ViewHolder {
        public final View itemView;
        WeakReference<RecyclerView> mNestedRecyclerView;
        int mPosition = NO_POSITION;
        int mOldPosition = NO_POSITION;
        long mItemId = NO_ID;
        int mItemViewType = INVALID_TYPE;
        int mPreLayoutPosition = NO_POSITION;
        // The item that this holder is shadowing during an item change event/animation
        ViewHolder mShadowedHolder = null;
        // The item that is shadowing this holder during an item change event/animation
        ViewHolder mShadowingHolder = null;
        /**
         * This ViewHolder has been bound to a position; mPosition, mItemId and mItemViewType
         * are all valid.
         */
        static final int FLAG_BOUND = 1 << 0;
        /**
         * The data this ViewHolder's view reflects is stale and needs to be rebound
         * by the adapter. mPosition and mItemId are consistent.
         */
        static final int FLAG_UPDATE = 1 << 1;
        /**
         * This ViewHolder's data is invalid. The identity implied by mPosition and mItemId
         * are not to be trusted and may no longer match the item view type.
         * This ViewHolder must be fully rebound to different data.
         */
        static final int FLAG_INVALID = 1 << 2;
        /**
         * This ViewHolder points at data that represents an item previously removed from the
         * data set. Its view may still be used for things like outgoing animations.
         */
        static final int FLAG_REMOVED = 1 << 3;
        /**
         * This ViewHolder should not be recycled. This flag is set via setIsRecyclable()
         * and is intended to keep views around during animations.
         */
        static final int FLAG_NOT_RECYCLABLE = 1 << 4;
        /**
         * This ViewHolder is returned from scrap which means we are expecting an addView call
         * for this itemView. When returned from scrap, ViewHolder stays in the scrap list until
         * the end of the layout pass and then recycled by RecyclerView if it is not added back to
         * the RecyclerView.
         */
        static final int FLAG_RETURNED_FROM_SCRAP = 1 << 5;
        /**
         * This ViewHolder is fully managed by the LayoutManager. We do not scrap, recycle or remove
         * it unless LayoutManager is replaced.
         * It is still fully visible to the LayoutManager.
         */
        static final int FLAG_IGNORE = 1 << 7;
        /**
         * When the View is detached form the parent, we set this flag so that we can take correct
         * action when we need to remove it or add it back.
         */
        static final int FLAG_TMP_DETACHED = 1 << 8;
        /**
         * Set when we can no longer determine the adapter position of this ViewHolder until it is
         * rebound to a new position. It is different than FLAG_INVALID because FLAG_INVALID is
         * set even when the type does not match. Also, FLAG_ADAPTER_POSITION_UNKNOWN is set as soon
         * as adapter notification arrives vs FLAG_INVALID is set lazily before layout is
         * re-calculated.
         */
        static final int FLAG_ADAPTER_POSITION_UNKNOWN = 1 << 9;
        /**
         * Set when a addChangePayload(null) is called
         */
        static final int FLAG_ADAPTER_FULLUPDATE = 1 << 10;
        /**
         * Used by ItemAnimator when a ViewHolder's position changes
         */
        static final int FLAG_MOVED = 1 << 11;
        /**
         * Used by ItemAnimator when a ViewHolder appears in pre-layout
         */
        static final int FLAG_APPEARED_IN_PRE_LAYOUT = 1 << 12;
        static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
        /**
         * Used when a ViewHolder starts the layout pass as a hidden ViewHolder but is re-used from
         * hidden list (as if it was scrap) without being recycled in between.
         *
         * When a ViewHolder is hidden, there are 2 paths it can be re-used:
         *   a) Animation ends, view is recycled and used from the recycle pool.
         *   b) LayoutManager asks for the View for that position while the ViewHolder is hidden.
         *
         * This flag is used to represent "case b" where the ViewHolder is reused without being
         * recycled (thus "bounced" from the hidden list). This state requires special handling
         * because the ViewHolder must be added to pre layout maps for animations as if it was
         * already there.
         */
        static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 1 << 13;
        int mFlags;
        private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
        List<Object> mPayloads = null;
        List<Object> mUnmodifiedPayloads = null;
        private int mIsRecyclableCount = 0;
        // If non-null, view is currently considered scrap and may be reused for other data by the
        // scrap container.
        Recycler mScrapContainer = null;
        // Keeps whether this ViewHolder lives in Change scrap or Attached scrap
        boolean mInChangeScrap = false;
        // Saves isImportantForAccessibility value for the view item while it's in hidden state and
        // marked as unimportant for accessibility.
        private int mWasImportantForAccessibilityBeforeHidden =0;
        // set if we defer the accessibility state change of the view holder
                int mPendingAccessibilityState = PENDING_ACCESSIBILITY_STATE_NOT_SET;
        /**
         * Is set when VH is bound from the adapter and cleaned right before it is sent to
         * {@link RecycledViewPool}.
         */
        RecyclerView mOwnerRecyclerView;
        public ViewHolder( View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = itemView;
        }
        void flagRemovedAndOffsetPosition(int mNewPosition, int offset, boolean applyToPreLayout) {
            addFlags(ViewHolder.FLAG_REMOVED);
            offsetPosition(offset, applyToPreLayout);
            mPosition = mNewPosition;
        }
        void offsetPosition(int offset, boolean applyToPreLayout) {
            if (mOldPosition == NO_POSITION) {
                mOldPosition = mPosition;
            }
            if (mPreLayoutPosition == NO_POSITION) {
                mPreLayoutPosition = mPosition;
            }
            if (applyToPreLayout) {
                mPreLayoutPosition += offset;
            }
            mPosition += offset;
            if (itemView.getLayoutParams() != null) {
                ((LayoutParams) itemView.getLayoutParams()).mInsetsDirty = true;
            }
        }
        void clearOldPosition() {
            mOldPosition = NO_POSITION;
            mPreLayoutPosition = NO_POSITION;
        }
        void saveOldPosition() {
            if (mOldPosition == NO_POSITION) {
                mOldPosition = mPosition;
            }
        }
        boolean shouldIgnore() {
            return (mFlags & FLAG_IGNORE) != 0;
        }
        /**
         * @deprecated This method is deprecated because its meaning is ambiguous due to the async
         * handling of adapter updates. You should use {@link #getLayoutPosition()} or
         * {@link #getAdapterPosition()} depending on your use case.
         *
         * @see #getLayoutPosition()
         * @see #getAdapterPosition()
         */
        @Deprecated
        public final int getPosition() {
            return mPreLayoutPosition == NO_POSITION ? mPosition : mPreLayoutPosition;
        }
        /**
         * Returns the position of the ViewHolder in terms of the latest layout pass.
         * <p>
         * This position is mostly used by RecyclerView components to be consistent while
         * RecyclerView lazily processes adapter updates.
         * <p>
         * For performance and animation reasons, RecyclerView batches all adapter updates until the
         * next layout pass. This may cause mismatches between the Adapter position of the item and
         * the position it had in the latest layout calculations.
         * <p>
         * LayoutManagers should always call this method while doing calculations based on item
         * positions. All methods in {@link RecyclerView.LayoutManager}, {@link RecyclerView.State},
         * {@link RecyclerView.Recycler} that receive a position expect it to be the layout position
         * of the item.
         * <p>
         * If LayoutManager needs to call an external method that requires the adapter position of
         * the item, it can use {@link #getAdapterPosition()} or
         * {@link RecyclerView.Recycler#convertPreLayoutPositionToPostLayout(int)}.
         *
         * @return Returns the adapter position of the ViewHolder in the latest layout pass.
         * @see #getAdapterPosition()
         */
        public final int getLayoutPosition() {
            return mPreLayoutPosition == NO_POSITION ? mPosition : mPreLayoutPosition;
        }
        /**
         * Returns the Adapter position of the item represented by this ViewHolder.
         * <p>
         * Note that this might be different than the {@link #getLayoutPosition()} if there are
         * pending adapter updates but a new layout pass has not happened yet.
         * <p>
         * RecyclerView does not handle any adapter updates until the next layout traversal. This
         * may create temporary inconsistencies between what user sees on the screen and what
         * adapter contents have. This inconsistency is not important since it will be less than
         * 16ms but it might be a problem if you want to use ViewHolder position to access the
         * adapter. Sometimes, you may need to get the exact adapter position to do
         * some actions in response to user events. In that case, you should use this method which
         * will calculate the Adapter position of the ViewHolder.
         * <p>
         * Note that if you've called {@link RecyclerView.Adapter#notifyDataSetChanged()}, until the
         * next layout pass, the return value of this method will be {@link #NO_POSITION}.
         *
         * @return The adapter position of the item if it still exists in the adapter.
         * {@link RecyclerView#NO_POSITION} if item has been removed from the adapter,
         * {@link RecyclerView.Adapter#notifyDataSetChanged()} has been called after the last
         * layout pass or the ViewHolder has already been recycled.
         */
        public final int getAdapterPosition() {
            if (mOwnerRecyclerView == null) {
                return NO_POSITION;
            }
            return 0;
        }
        /**
         * When LayoutManager supports animations, RecyclerView tracks 3 positions for ViewHolders
         * to perform animations.
         * <p>
         * If a ViewHolder was laid out in the previous onLayout call, old position will keep its
         * adapter index in the previous layout.
         *
         * @return The previous adapter index of the Item represented by this ViewHolder or
         * {@link #NO_POSITION} if old position does not exists or cleared (pre-layout is
         * complete).
         */
        public final int getOldPosition() {
            return mOldPosition;
        }
        /**
         * Returns The itemId represented by this ViewHolder.
         *
         * @return The item's id if adapter has stable ids, {@link RecyclerView#NO_ID}
         * otherwise
         */
        public final long getItemId() {
            return mItemId;
        }
        /**
         * @return The view type of this ViewHolder.
         */
        public final int getItemViewType() {
            return mItemViewType;
        }
        boolean isScrap() {
            return mScrapContainer != null;
        }
        void unScrap() {
        }
        boolean wasReturnedFromScrap() {
            return (mFlags & FLAG_RETURNED_FROM_SCRAP) != 0;
        }
        void clearReturnedFromScrapFlag() {
            mFlags = mFlags & ~FLAG_RETURNED_FROM_SCRAP;
        }
        void clearTmpDetachFlag() {
            mFlags = mFlags & ~FLAG_TMP_DETACHED;
        }
        void stopIgnoring() {
            mFlags = mFlags & ~FLAG_IGNORE;
        }
        void setScrapContainer(Recycler recycler, boolean isChangeScrap) {
            mScrapContainer = recycler;
            mInChangeScrap = isChangeScrap;
        }
        boolean isInvalid() {
            return (mFlags & FLAG_INVALID) != 0;
        }
        boolean needsUpdate() {
            return (mFlags & FLAG_UPDATE) != 0;
        }
        boolean isBound() {
            return (mFlags & FLAG_BOUND) != 0;
        }
        boolean isRemoved() {
            return (mFlags & FLAG_REMOVED) != 0;
        }
        boolean hasAnyOfTheFlags(int flags) {
            return (mFlags & flags) != 0;
        }
        boolean isTmpDetached() {
            return (mFlags & FLAG_TMP_DETACHED) != 0;
        }
        boolean isAttachedToTransitionOverlay() {
            return itemView.getParent() != null && itemView.getParent() != mOwnerRecyclerView;
        }
        boolean isAdapterPositionUnknown() {
            return (mFlags & FLAG_ADAPTER_POSITION_UNKNOWN) != 0 || isInvalid();
        }
        void setFlags(int flags, int mask) {
            mFlags = (mFlags & ~mask) | (flags & mask);
        }
        void addFlags(int flags) {
            mFlags |= flags;
        }
        void addChangePayload(Object payload) {
            if (payload == null) {
                addFlags(FLAG_ADAPTER_FULLUPDATE);
            } else if ((mFlags & FLAG_ADAPTER_FULLUPDATE) == 0) {
                createPayloadsIfNeeded();
                mPayloads.add(payload);
            }
        }
        private void createPayloadsIfNeeded() {
            if (mPayloads == null) {
                mPayloads = new ArrayList<Object>();
                mUnmodifiedPayloads = Collections.unmodifiableList(mPayloads);
            }
        }
        void clearPayload() {
            if (mPayloads != null) {
                mPayloads.clear();
            }
            mFlags = mFlags & ~FLAG_ADAPTER_FULLUPDATE;
        }
        List<Object> getUnmodifiedPayloads() {
            if ((mFlags & FLAG_ADAPTER_FULLUPDATE) == 0) {
                if (mPayloads == null || mPayloads.size() == 0) {
                    // Initial state,  no update being called.
                    return FULLUPDATE_PAYLOADS;
                }
                // there are none-null payloads
                return mUnmodifiedPayloads;
            } else {
                // a full update has been called.
                return FULLUPDATE_PAYLOADS;
            }
        }
        void resetInternal() {
            mFlags = 0;
            mPosition = NO_POSITION;
            mOldPosition = NO_POSITION;
            mItemId = NO_ID;
            mPreLayoutPosition = NO_POSITION;
            mIsRecyclableCount = 0;
            mShadowedHolder = null;
            mShadowingHolder = null;
            clearPayload();
            mPendingAccessibilityState = PENDING_ACCESSIBILITY_STATE_NOT_SET;
            clearNestedRecyclerViewIfNotNested(this);
        }
        /**
         * Called when the child view enters the hidden state
         */
        void onEnteredHiddenState(RecyclerView parent) {
            // While the view item is in hidden state, make it invisible for the accessibility.
            if (mPendingAccessibilityState != PENDING_ACCESSIBILITY_STATE_NOT_SET) {
                mWasImportantForAccessibilityBeforeHidden = mPendingAccessibilityState;
            } else {
            }
        }
        /**
         * Called when the child view leaves the hidden state
         */
        void onLeftHiddenState(RecyclerView parent) {
        }
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ViewHolder{"
                    + Integer.toHexString(hashCode()) + " position=" + mPosition + " id=" + mItemId
                    + ", oldPos=" + mOldPosition + ", pLpos:" + mPreLayoutPosition);
            if (isScrap()) {
                sb.append(" scrap ")
                        .append(mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
            }
            if (isInvalid()) sb.append(" invalid");
            if (!isBound()) sb.append(" unbound");
            if (needsUpdate()) sb.append(" update");
            if (isRemoved()) sb.append(" removed");
            if (shouldIgnore()) sb.append(" ignored");
            if (isTmpDetached()) sb.append(" tmpDetached");
            if (!isRecyclable()) sb.append(" not recyclable(" + mIsRecyclableCount + ")");
            if (isAdapterPositionUnknown()) sb.append(" undefined adapter position");
            if (itemView.getParent() == null) sb.append(" no parent");
            sb.append("}");
            return sb.toString();
        }
        /**
         * Informs the recycler whether this item can be recycled. Views which are not
         * recyclable will not be reused for other items until setIsRecyclable() is
         * later set to true. Calls to setIsRecyclable() should always be paired (one
         * call to setIsRecyclabe(false) should always be matched with a later call to
         * setIsRecyclable(true)). Pairs of calls may be nested, as the state is internally
         * reference-counted.
         *
         * @param recyclable Whether this item is available to be recycled. Default value
         * is true.
         *
         * @see #isRecyclable()
         */
        public final void setIsRecyclable(boolean recyclable) {
            mIsRecyclableCount = recyclable ? mIsRecyclableCount - 1 : mIsRecyclableCount + 1;
            if (mIsRecyclableCount < 0) {
                mIsRecyclableCount = 0;
                if (DEBUG) {
                    throw new RuntimeException("isRecyclable decremented below 0: "
                            + "unmatched pair of setIsRecyable() calls for " + this);
                }
                Log.e(VIEW_LOG_TAG, "isRecyclable decremented below 0: "
                        + "unmatched pair of setIsRecyable() calls for " + this);
            } else if (!recyclable && mIsRecyclableCount == 1) {
                mFlags |= FLAG_NOT_RECYCLABLE;
            } else if (recyclable && mIsRecyclableCount == 0) {
                mFlags &= ~FLAG_NOT_RECYCLABLE;
            }
            if (DEBUG) {
                Log.d(TAG, "setIsRecyclable val:" + recyclable + ":" + this);
            }
        }
        /**
         * @return true if this item is available to be recycled, false otherwise.
         *
         * @see #setIsRecyclable(boolean)
         */
        public final boolean isRecyclable() {
        	return false;
        }
        /**
         * Returns whether we have animations referring to this view holder or not.
         * This is similar to isRecyclable flag but does not check transient state.
         */
        boolean shouldBeKeptAsChild() {
            return (mFlags & FLAG_NOT_RECYCLABLE) != 0;
        }
        /**
         * @return True if ViewHolder is not referenced by RecyclerView animations but has
         * transient state which will prevent it from being recycled.
         */
        boolean doesTransientStatePreventRecycling() {
        	return false;
        }
        boolean isUpdated() {
            return (mFlags & FLAG_UPDATE) != 0;
        }
    }

    /**
     * {@link android.view.ViewGroup.MarginLayoutParams LayoutParams} subclass for children of
     * {@link RecyclerView}. Custom {@link LayoutManager layout managers} are encouraged
     * to create their own subclass of this <code>LayoutParams</code> class
     * to store any additional required per-child view metadata about the layout.
     */
    public static class LayoutParams extends android.view.ViewGroup.MarginLayoutParams {
        ViewHolder mViewHolder;
        final Rect mDecorInsets = new Rect();
        boolean mInsetsDirty = true;
        // Flag is set to true if the view is bound while it is detached from RV.
        // In this case, we need to manually call invalidate after view is added to guarantee that
        // invalidation is populated through the View hierarchy
        boolean mPendingInvalidate = false;
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }
        public LayoutParams(int width, int height) {
            super(width, height);
        }
        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
        public LayoutParams(LayoutParams source) {
            super((ViewGroup.LayoutParams) source);
        }
        /**
         * Returns true if the view this LayoutParams is attached to needs to have its content
         * updated from the corresponding adapter.
         *
         * @return true if the view should have its content updated
         */
        public boolean viewNeedsUpdate() {
            return mViewHolder.needsUpdate();
        }
        /**
         * Returns true if the view this LayoutParams is attached to is now representing
         * potentially invalid data. A LayoutManager should scrap/recycle it.
         *
         * @return true if the view is invalid
         */
        public boolean isViewInvalid() {
            return mViewHolder.isInvalid();
        }
        /**
         * Returns true if the adapter data item corresponding to the view this LayoutParams
         * is attached to has been removed from the data set. A LayoutManager may choose to
         * treat it differently in order to animate its outgoing or disappearing state.
         *
         * @return true if the item the view corresponds to was removed from the data set
         */
        public boolean isItemRemoved() {
            return mViewHolder.isRemoved();
        }
        /**
         * Returns true if the adapter data item corresponding to the view this LayoutParams
         * is attached to has been changed in the data set. A LayoutManager may choose to
         * treat it differently in order to animate its changing state.
         *
         * @return true if the item the view corresponds to was changed in the data set
         */
        public boolean isItemChanged() {
            return mViewHolder.isUpdated();
        }
        /**
         * @deprecated use {@link #getViewLayoutPosition()} or {@link #getViewAdapterPosition()}
         */
        @Deprecated
        public int getViewPosition() {
            return mViewHolder.getPosition();
        }
        /**
         * Returns the adapter position that the view this LayoutParams is attached to corresponds
         * to as of latest layout calculation.
         *
         * @return the adapter position this view as of latest layout pass
         */
        public int getViewLayoutPosition() {
            return mViewHolder.getLayoutPosition();
        }
        /**
         * Returns the up-to-date adapter position that the view this LayoutParams is attached to
         * corresponds to.
         *
         * @return the up-to-date adapter position this view. It may return
         * {@link RecyclerView#NO_POSITION} if item represented by this View has been removed or
         * its up-to-date position cannot be calculated.
         */
        public int getViewAdapterPosition() {
            return mViewHolder.getAdapterPosition();
        }
    }
    /**
     * Observer base class for watching changes to an {@link Adapter}.
     * See {@link Adapter#registerAdapterDataObserver(AdapterDataObserver)}.
     */
    public abstract static class AdapterDataObserver {
        public void onChanged() {
            // Do nothing
        }
        public void onItemRangeChanged(int positionStart, int itemCount) {
            // do nothing
        }
        public void onItemRangeChanged(int positionStart, int itemCount,  Object payload) {
            // fallback to onItemRangeChanged(positionStart, itemCount) if app
            // does not override this method.
            onItemRangeChanged(positionStart, itemCount);
        }
        public void onItemRangeInserted(int positionStart, int itemCount) {
            // do nothing
        }
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            // do nothing
        }
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            // do nothing
        }
    }
    static class AdapterDataObservable extends Observable<AdapterDataObserver> {
        public boolean hasObservers() {
            return !mObservers.isEmpty();
        }
        public void notifyChanged() {
            // since onChanged() is implemented by the app, it could do anything, including
            // removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onChanged();
            }
        }
        public void notifyItemRangeChanged(int positionStart, int itemCount) {
            notifyItemRangeChanged(positionStart, itemCount, null);
        }
        public void notifyItemRangeChanged(int positionStart, int itemCount,
                 Object payload) {
            // since onItemRangeChanged() is implemented by the app, it could do anything, including
            // removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onItemRangeChanged(positionStart, itemCount, payload);
            }
        }
        public void notifyItemRangeInserted(int positionStart, int itemCount) {
            // since onItemRangeInserted() is implemented by the app, it could do anything,
            // including removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onItemRangeInserted(positionStart, itemCount);
            }
        }
        public void notifyItemRangeRemoved(int positionStart, int itemCount) {
            // since onItemRangeRemoved() is implemented by the app, it could do anything, including
            // removing itself from {@link mObservers} - and that could cause problems if
            // an iterator is used on the ArrayList {@link mObservers}.
            // to avoid such problems, just march thru the list in the reverse order.
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onItemRangeRemoved(positionStart, itemCount);
            }
        }
        public void notifyItemMoved(int fromPosition, int toPosition) {
            for (int i = mObservers.size() - 1; i >= 0; i--) {
                mObservers.get(i).onItemRangeMoved(fromPosition, toPosition, 1);
            }
        }
    }
    /**
     * <p>Contains useful information about the current RecyclerView state like target scroll
     * position or view focus. State object can also keep arbitrary data, identified by resource
     * ids.</p>
     * <p>Often times, RecyclerView components will need to pass information between each other.
     * To provide a well defined data bus between components, RecyclerView passes the same State
     * object to component callbacks and these components can use it to exchange data.</p>
     * <p>If you implement custom components, you can use State's put/get/remove methods to pass
     * data between your components without needing to manage their lifecycles.</p>
     */
    public static class State {
        static final int STEP_START = 1;
        static final int STEP_LAYOUT = 1 << 1;
        static final int STEP_ANIMATIONS = 1 << 2;
        void assertLayoutStep(int accepted) {
            if ((accepted & mLayoutStep) == 0) {
                throw new IllegalStateException("Layout state should be one of "
                        + Integer.toBinaryString(accepted) + " but it is "
                        + Integer.toBinaryString(mLayoutStep));
            }
        }
        /** Owned by SmoothScroller */
        int mTargetPosition = RecyclerView.NO_POSITION;
        private SparseArray<Object> mData;
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Fields below are carried from one layout pass to the next
        ////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * Number of items adapter had in the previous layout.
         */
        int mPreviousLayoutItemCount = 0;
        /**
         * Number of items that were NOT laid out but has been deleted from the adapter after the
         * previous layout.
         */
        int mDeletedInvisibleItemCountSincePreviousLayout = 0;
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Fields below must be updated or cleared before they are used (generally before a pass)
        ////////////////////////////////////////////////////////////////////////////////////////////
        @Retention(RetentionPolicy.SOURCE)
        @interface LayoutState {}
        @LayoutState
        int mLayoutStep = STEP_START;
        /**
         * Number of items adapter has.
         */
        int mItemCount = 0;
        boolean mStructureChanged = false;
        /**
         * True if the associated {@link RecyclerView} is in the pre-layout step where it is having
         * its {@link LayoutManager} layout items where they will be at the beginning of a set of
         * predictive item animations.
         */
        boolean mInPreLayout = false;
        boolean mTrackOldChangeHolders = false;
        boolean mIsMeasuring = false;
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Fields below are always reset outside of the pass (or passes) that use them
        ////////////////////////////////////////////////////////////////////////////////////////////
        boolean mRunSimpleAnimations = false;
        boolean mRunPredictiveAnimations = false;
        /**
         * This data is saved before a layout calculation happens. After the layout is finished,
         * if the previously focused view has been replaced with another view for the same item, we
         * move the focus to the new item automatically.
         */
        int mFocusedItemPosition;
        long mFocusedItemId;
        // when a sub child has focus, record its id and see if we can directly request focus on
        // that one instead
        int mFocusedSubChildId;
        int mRemainingScrollHorizontal;
        int mRemainingScrollVertical;
        ////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * Prepare for a prefetch occurring on the RecyclerView in between traversals, potentially
         * prior to any layout passes.
         *
         * <p>Don't touch any state stored between layout passes, only reset per-layout state, so
         * that Recycler#getViewForPosition() can function safely.</p>
         */
        void prepareForNestedPrefetch(Adapter adapter) {
            mLayoutStep = STEP_START;
            mItemCount = adapter.getItemCount();
            mInPreLayout = false;
            mTrackOldChangeHolders = false;
            mIsMeasuring = false;
        }
        /**
         * Returns true if the RecyclerView is currently measuring the layout. This value is
         * {@code true} only if the LayoutManager opted into the auto measure API and RecyclerView
         * has non-exact measurement specs.
         * <p>
         * Note that if the LayoutManager supports predictive animations and it is calculating the
         * pre-layout step, this value will be {@code false} even if the RecyclerView is in
         * {@code onMeasure} call. This is because pre-layout means the previous state of the
         * RecyclerView and measurements made for that state cannot change the RecyclerView's size.
         * LayoutManager is always guaranteed to receive another call to
         * {@link LayoutManager#onLayoutChildren(Recycler, State)} when this happens.
         *
         * @return True if the RecyclerView is currently calculating its bounds, false otherwise.
         */
        public boolean isMeasuring() {
            return mIsMeasuring;
        }
        /**
         * Returns true if the {@link RecyclerView} is in the pre-layout step where it is having its
         * {@link LayoutManager} layout items where they will be at the beginning of a set of
         * predictive item animations.
         */
        public boolean isPreLayout() {
            return mInPreLayout;
        }
        /**
         * Returns whether RecyclerView will run predictive animations in this layout pass
         * or not.
         *
         * @return true if RecyclerView is calculating predictive animations to be run at the end
         *         of the layout pass.
         */
        public boolean willRunPredictiveAnimations() {
            return mRunPredictiveAnimations;
        }
        /**
         * Returns whether RecyclerView will run simple animations in this layout pass
         * or not.
         *
         * @return true if RecyclerView is calculating simple animations to be run at the end of
         *         the layout pass.
         */
        public boolean willRunSimpleAnimations() {
            return mRunSimpleAnimations;
        }
        /**
         * Removes the mapping from the specified id, if there was any.
         * @param resourceId Id of the resource you want to remove. It is suggested to use R.id.* to
         *                   preserve cross functionality and avoid conflicts.
         */
        public void remove(int resourceId) {
            if (mData == null) {
                return;
            }
            mData.remove(resourceId);
        }
        /**
         * Gets the Object mapped from the specified id, or <code>null</code>
         * if no such data exists.
         *
         * @param resourceId Id of the resource you want to remove. It is suggested to use R.id.*
         *                   to
         *                   preserve cross functionality and avoid conflicts.
         */
        @SuppressWarnings("TypeParameterUnusedInFormals")
        public <T> T get(int resourceId) {
            if (mData == null) {
                return null;
            }
            return (T) mData.get(resourceId);
        }
        /**
         * Adds a mapping from the specified id to the specified value, replacing the previous
         * mapping from the specified key if there was one.
         *
         * @param resourceId Id of the resource you want to add. It is suggested to use R.id.* to
         *                   preserve cross functionality and avoid conflicts.
         * @param data       The data you want to associate with the resourceId.
         */
        public void put(int resourceId, Object data) {
            if (mData == null) {
                mData = new SparseArray<Object>();
            }
            mData.put(resourceId, data);
        }
        /**
         * If scroll is triggered to make a certain item visible, this value will return the
         * adapter index of that item.
         * @return Adapter index of the target item or
         * {@link RecyclerView#NO_POSITION} if there is no target
         * position.
         */
        public int getTargetScrollPosition() {
            return mTargetPosition;
        }
        /**
         * Returns if current scroll has a target position.
         * @return true if scroll is being triggered to make a certain position visible
         * @see #getTargetScrollPosition()
         */
        public boolean hasTargetScrollPosition() {
            return mTargetPosition != RecyclerView.NO_POSITION;
        }
        /**
         * @return true if the structure of the data set has changed since the last call to
         *         onLayoutChildren, false otherwise
         */
        public boolean didStructureChange() {
            return mStructureChanged;
        }
        /**
         * Returns the total number of items that can be laid out. Note that this number is not
         * necessarily equal to the number of items in the adapter, so you should always use this
         * number for your position calculations and never access the adapter directly.
         * <p>
         * RecyclerView listens for Adapter's notify events and calculates the effects of adapter
         * data changes on existing Views. These calculations are used to decide which animations
         * should be run.
         * <p>
         * To support predictive animations, RecyclerView may rewrite or reorder Adapter changes to
         * present the correct state to LayoutManager in pre-layout pass.
         * <p>
         * For example, a newly added item is not included in pre-layout item count because
         * pre-layout reflects the contents of the adapter before the item is added. Behind the
         * scenes, RecyclerView offsets {@link Recycler#getViewForPosition(int)} calls such that
         * LayoutManager does not know about the new item's existence in pre-layout. The item will
         * be available in second layout pass and will be included in the item count. Similar
         * adjustments are made for moved and removed items as well.
         * <p>
         * You can get the adapter's item count via {@link LayoutManager#getItemCount()} method.
         *
         * @return The number of items currently available
         * @see LayoutManager#getItemCount()
         */
        public int getItemCount() {
            return mInPreLayout
                    ? (mPreviousLayoutItemCount - mDeletedInvisibleItemCountSincePreviousLayout)
                    : mItemCount;
        }
        /**
         * Returns remaining horizontal scroll distance of an ongoing scroll animation(fling/
         * smoothScrollTo/SmoothScroller) in pixels. Returns zero if {@link #getScrollState()} is
         * other than {@link #SCROLL_STATE_SETTLING}.
         *
         * @return Remaining horizontal scroll distance
         */
        public int getRemainingScrollHorizontal() {
            return mRemainingScrollHorizontal;
        }
        /**
         * Returns remaining vertical scroll distance of an ongoing scroll animation(fling/
         * smoothScrollTo/SmoothScroller) in pixels. Returns zero if {@link #getScrollState()} is
         * other than {@link #SCROLL_STATE_SETTLING}.
         *
         * @return Remaining vertical scroll distance
         */
        public int getRemainingScrollVertical() {
            return mRemainingScrollVertical;
        }
        @Override
        public String toString() {
            return "State{"
                    + "mTargetPosition=" + mTargetPosition
                    + ", mData=" + mData
                    + ", mItemCount=" + mItemCount
                    + ", mIsMeasuring=" + mIsMeasuring
                    + ", mPreviousLayoutItemCount=" + mPreviousLayoutItemCount
                    + ", mDeletedInvisibleItemCountSincePreviousLayout="
                    + mDeletedInvisibleItemCountSincePreviousLayout
                    + ", mStructureChanged=" + mStructureChanged
                    + ", mInPreLayout=" + mInPreLayout
                    + ", mRunSimpleAnimations=" + mRunSimpleAnimations
                    + ", mRunPredictiveAnimations=" + mRunPredictiveAnimations
                    + '}';
        }
    }
    /**
     * This class defines the behavior of fling if the developer wishes to handle it.
     * <p>
     * Subclasses of {@link OnFlingListener} can be used to implement custom fling behavior.
     *
     * @see #setOnFlingListener(OnFlingListener)
     */
    public abstract static class OnFlingListener {
        /**
         * Override this to handle a fling given the velocities in both x and y directions.
         * Note that this method will only be called if the associated {@link LayoutManager}
         * supports scrolling and the fling is not handled by nested scrolls first.
         *
         * @param velocityX the fling velocity on the X axis
         * @param velocityY the fling velocity on the Y axis
         *
         * @return true if the fling was handled, false otherwise.
         */
        public abstract boolean onFling(int velocityX, int velocityY);
    }
    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        if (mChildDrawingOrderCallback == null) {
            return super.getChildDrawingOrder(childCount, i);
        } else {
            return mChildDrawingOrderCallback.onGetChildDrawingOrder(childCount, i);
        }
    }
    /**
     * A callback interface that can be used to alter the drawing order of RecyclerView children.
     * <p>
     * It works using the {@link ViewGroup#getChildDrawingOrder(int, int)} method, so any case
     * that applies to that method also applies to this callback. For example, changing the drawing
     * order of two views will not have any effect if their elevation values are different since
     * elevation overrides the result of this callback.
     */
    public interface ChildDrawingOrderCallback {
        /**
         * Returns the index of the child to draw for this iteration. Override this
         * if you want to change the drawing order of children. By default, it
         * returns i.
         *
         * @param i The current iteration.
         * @return The index of the child to draw this iteration.
         *
         * @see RecyclerView#setChildDrawingOrderCallback(RecyclerView.ChildDrawingOrderCallback)
         */
        int onGetChildDrawingOrder(int childCount, int i);
    }
}
