/*
 * Copyright (C) 2017 The Android Open Source Project
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

package android.arch.lifecycle;

import android.arch.core.internal.SafeIterableMap;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * {@link LiveData} subclass which may observe other {@code LiveData} objects and react on
 * {@code OnChanged} events from them.
 * <p>
 * This class correctly propagates its active/inactive states down to source {@code LiveData}
 * objects.
 * <p>
 * Consider the following scenario: we have 2 instances of {@code LiveData}, let's name them
 * {@code liveData1} and {@code liveData2}, and we want to merge their emissions in one object:
 * {@code liveDataMerger}. Then, {@code liveData1} and {@code liveData2} will become sources for
 * the {@code MediatorLiveData liveDataMerger} and every time {@code onChanged} callback
 * is called for either of them, we set a new value in {@code liveDataMerger}.
 *
 * <pre>
 * LiveData&lt;Integer&gt; liveData1 = ...;
 * LiveData&lt;Integer&gt; liveData2 = ...;
 *
 * MediatorLiveData&lt;Integer&gt; liveDataMerger = new MediatorLiveData&lt;&gt;();
 * liveDataMerger.addSource(liveData1, value -&gt; liveDataMerger.setValue(value));
 * liveDataMerger.addSource(liveData2, value -&gt;liveDataMerger.setValue(value));
 * </pre>
 * <p>
 * Let's consider that we only want 10 values emitted by {@code liveData1}, to be
 * merged in the {@code liveDataMerger}. Then, after 10 values, we can stop listening to {@code
 * liveData1} and remove it as a source.
 * <pre>
 * liveDataMerger.addSource(liveData1, new Observer&lt;Integer&gt;() {
 *      private int count = 1;
 *
 *      {@literal @}Override public void onChanged(@Nullable Integer s) {
 *          count++;
 *          liveDataMerger.setValue(s);
 *          if (count &gt; 10) {
 *              liveDataMerger.removeSource(liveData1);
 *          }
 *      }
 * });
 * </pre>
 *
 * @param <T> The type of data hold by this instance
 */
public class MediatorLiveData<T> extends MutableLiveData<T> {
    
    /** The m sources. */
    private SafeIterableMap<LiveData<?>, Source<?>> mSources = new SafeIterableMap<>();

    /**
     * Starts to listen the given {@code source} LiveData, {@code onChanged} observer will be called
     * when {@code source} value was changed.
     * <p>
     * {@code onChanged} callback will be called only when this {@code MediatorLiveData} is active.
     * <p> If the given LiveData is already added as a source but with a different Observer,
     * {@link IllegalArgumentException} will be thrown.
     *
     * @param <S>       The type of data hold by {@code source} LiveData
     * @param source    the {@code LiveData} to listen to
     * @param onChanged The observer that will receive the events
     */
    @MainThread
    public <S> void addSource(@NonNull LiveData<S> source, @NonNull Observer<S> onChanged) {
        Source<S> e = new Source<>(source, onChanged);
        Source<?> existing = mSources.putIfAbsent(source, e);
        if (existing != null && existing.mObserver != onChanged) {
            throw new IllegalArgumentException(
                    "This source was already added with the different observer");
        }
        if (existing != null) {
            return;
        }
        if (hasActiveObservers()) {
            e.plug();
        }
    }

    /**
     * Stops to listen the given {@code LiveData}.
     *
     * @param <S>      the type of data hold by {@code source} LiveData
     * @param toRemote {@code LiveData} to stop to listen
     */
    @MainThread
    public <S> void removeSource(@NonNull LiveData<S> toRemote) {
        Source<?> source = mSources.remove(toRemote);
        if (source != null) {
            source.unplug();
        }
    }

    /* (non-Javadoc)
     * @see android.arch.lifecycle.LiveData#onActive()
     */
    @CallSuper
    @Override
    protected void onActive() {
        for (Map.Entry<LiveData<?>, Source<?>> source : mSources) {
            source.getValue().plug();
        }
    }

    /* (non-Javadoc)
     * @see android.arch.lifecycle.LiveData#onInactive()
     */
    @CallSuper
    @Override
    protected void onInactive() {
        for (Map.Entry<LiveData<?>, Source<?>> source : mSources) {
            source.getValue().unplug();
        }
    }

    /**
     * The Class Source.
     *
     * @param <V> the value type
     */
    private static class Source<V> implements Observer<V> {
        
        /** The m live data. */
        final LiveData<V> mLiveData;
        
        /** The m observer. */
        final Observer<V> mObserver;
        
        /** The m version. */
        int mVersion = START_VERSION;

        /**
         * Instantiates a new source.
         *
         * @param liveData the live data
         * @param observer the observer
         */
        Source(LiveData<V> liveData, final Observer<V> observer) {
            mLiveData = liveData;
            mObserver = observer;
        }

        /**
         * Plug.
         */
        void plug() {
            mLiveData.observeForever(this);
        }

        /**
         * Unplug.
         */
        void unplug() {
            mLiveData.removeObserver(this);
        }

        /* (non-Javadoc)
         * @see android.arch.lifecycle.Observer#onChanged(java.lang.Object)
         */
        @Override
        public void onChanged(@Nullable V v) {
            if (mVersion != mLiveData.getVersion()) {
                mVersion = mLiveData.getVersion();
                mObserver.onChanged(v);
            }
        }
    }
}
