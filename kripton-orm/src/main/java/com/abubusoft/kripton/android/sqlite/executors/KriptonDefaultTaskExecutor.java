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

package com.abubusoft.kripton.android.sqlite.executors;

import java.util.concurrent.ExecutorService;

import com.abubusoft.kripton.android.KriptonLibrary;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

// TODO: Auto-generated Javadoc
/**
 * The Class KriptonDefaultTaskExecutor.
 *
 */
public class KriptonDefaultTaskExecutor extends TaskExecutor {
	
	/**
	 * Instantiates a new kripton default task executor.
	 */
	public KriptonDefaultTaskExecutor() {
		mDiskIO = KriptonLibrary.getExecutorService();		
	}
			
    /** The m lock. */
    private final Object mLock = new Object();
    
    /** The m disk IO. */
    private ExecutorService mDiskIO;

    /** The m main handler. */
    @Nullable
    private volatile Handler mMainHandler;

    /* (non-Javadoc)
     * @see com.abubusoft.kripton.android.sqlite.executors.TaskExecutor#executeOnDiskIO(java.lang.Runnable)
     */
    @Override
    public void executeOnDiskIO(Runnable runnable) {
        mDiskIO.execute(runnable);
    }

    /* (non-Javadoc)
     * @see com.abubusoft.kripton.android.sqlite.executors.TaskExecutor#postToMainThread(java.lang.Runnable)
     */
    @Override
    public void postToMainThread(Runnable runnable) {
        if (mMainHandler == null) {
            synchronized (mLock) {
                if (mMainHandler == null) {
                    mMainHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        //noinspection ConstantConditions
        mMainHandler.post(runnable);
    }

    /* (non-Javadoc)
     * @see com.abubusoft.kripton.android.sqlite.executors.TaskExecutor#isMainThread()
     */
    @Override
    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
