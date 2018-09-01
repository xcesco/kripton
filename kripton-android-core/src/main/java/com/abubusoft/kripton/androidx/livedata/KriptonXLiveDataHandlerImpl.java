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

package com.abubusoft.kripton.androidx.livedata;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.executor.KriptonTaskExecutor;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;

/**
 * A LiveData class that can be invalidated & computed when there are active
 * observers.
 * <p>
 * It can be invalidated via {@link #invalidate()}, which will result in a call
 * to {@link #compute()} if there are active observers (or when they start
 * observing)
 * <p>
 * This is an internal class for now, might be public if we see the necessity.
 *
 * @param <T>
 *            The type of the live data
 * @hide internal
 */
public abstract class KriptonXLiveDataHandlerImpl<T> implements LiveDataHandler {

	private final Executor mExecutor;
	private final KriptonXLiveData<T> mLiveData;

	private AtomicBoolean mInvalid = new AtomicBoolean(true);
	private AtomicBoolean mComputing = new AtomicBoolean(false);

	/**
	 * Creates a computable live data that computes values on the arch IO thread
	 * executor.
	 */
	public KriptonXLiveDataHandlerImpl() {
		this(KriptonTaskExecutor.getIOThreadExecutor());
	}

	/**
	 *
	 * Creates a computable live data that computes values on the specified
	 * executor.
	 *
	 * @param executor
	 *            Executor that is used to compute new LiveData values.
	 */
	public KriptonXLiveDataHandlerImpl(@NonNull Executor executor) {
		mExecutor = executor;
		mLiveData = new KriptonXLiveData<T>() {
			@Override
			protected void onActive() {
				mExecutor.execute(mRefreshRunnable);
			}
		};
	}

	/**
	 * Returns the LiveData managed by this class.
	 *
	 * @return A LiveData that is controlled by ComputableLiveData.
	 */
	@NonNull
	public KriptonXLiveData<T> getLiveData() {
		return mLiveData;
	}

	@VisibleForTesting
	final Runnable mRefreshRunnable = new Runnable() {
		@WorkerThread
		@Override
		public void run() {
			boolean computed;
			do {
				computed = false;
				// compute can happen only in 1 thread but no reason to lock
				// others.
				if (mComputing.compareAndSet(false, true)) {
					// as long as it is invalid, keep computing.
					try {
						T value = null;
						while (mInvalid.compareAndSet(true, false)) {
							computed = true;
							value = compute();
						}
						if (computed) {
							mLiveData.postValue(value);
						}
					} finally {
						// release compute lock
						mComputing.set(false);
					}
				}
				// check invalid after releasing compute lock to avoid the
				// following scenario.
				// Thread A runs compute()
				// Thread A checks invalid, it is false
				// Main thread sets invalid to true
				// Thread B runs, fails to acquire compute lock and skips
				// Thread A releases compute lock
				// We've left invalid in set state. The check below recovers.
			} while (computed && mInvalid.get());
		}
	};

	// invalidation check always happens on the main thread
	@VisibleForTesting
	final Runnable mInvalidationRunnable = new Runnable() {
		@MainThread
		@Override
		public void run() {
			boolean isActive = mLiveData.hasActiveObservers();
			if (mInvalid.compareAndSet(false, true)) {
				if (isActive) {
					mExecutor.execute(mRefreshRunnable);
				}
			}
		}
	};

	/**
	 * Invalidates the LiveData.
	 * <p>
	 * When there are active observers, this will trigger a call to
	 * {@link #compute()}.
	 */
	@Override
	public void invalidate() {
		KriptonTaskExecutor.getInstance().executeOnMainThread(mInvalidationRunnable);
	}

	@WorkerThread
	protected abstract T compute();
}
