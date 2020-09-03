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

package androidx.arch.core.executor;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The Class DefaultTaskExecutor.
 *
 * 
 */
public class DefaultTaskExecutor extends TaskExecutor {
    
    /** The m lock. */
    private final Object mLock = new Object();
    
    /** The m disk IO. */
    private ExecutorService mDiskIO = Executors.newFixedThreadPool(2);

    /** The m main handler. */
    private volatile Handler mMainHandler;

    /* (non-Javadoc)
     * @see android.arch.core.executor.TaskExecutor#executeOnDiskIO(java.lang.Runnable)
     */
    @Override
    public void executeOnDiskIO(Runnable runnable) {
        mDiskIO.execute(runnable);
    }

    /* (non-Javadoc)
     * @see android.arch.core.executor.TaskExecutor#postToMainThread(java.lang.Runnable)
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
     * @see android.arch.core.executor.TaskExecutor#isMainThread()
     */
    @Override
    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
