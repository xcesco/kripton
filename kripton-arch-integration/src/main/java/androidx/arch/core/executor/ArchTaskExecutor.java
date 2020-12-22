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

import java.util.concurrent.Executor;

/**
 * A static class that serves as a central point to execute common tasks.
 *
 */
public class ArchTaskExecutor extends TaskExecutor {
    
    /** The s instance. */
    private static volatile ArchTaskExecutor sInstance;

    /** The m delegate. */
    private TaskExecutor mDelegate;

    /** The m default task executor. */
    private final TaskExecutor mDefaultTaskExecutor;

    /** The Constant sMainThreadExecutor. */
    private static final Executor sMainThreadExecutor = command -> getInstance().postToMainThread(command);

    /** The Constant sIOThreadExecutor. */
    private static final Executor sIOThreadExecutor = command -> getInstance().executeOnDiskIO(command);

    /**
     * Instantiates a new arch task executor.
     */
    private ArchTaskExecutor() {
        mDefaultTaskExecutor = new DefaultTaskExecutor();
        mDelegate = mDefaultTaskExecutor;
    }

    /**
     * Returns an instance of the task executor.
     *
     * @return The singleton ArchTaskExecutor.
     */
    public static ArchTaskExecutor getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (ArchTaskExecutor.class) {
            if (sInstance == null) {
                sInstance = new ArchTaskExecutor();
            }
        }
        return sInstance;
    }

    /**
     * Sets a delegate to handle task execution requests.
     * <p>
     * If you have a common executor, you can set it as the delegate and App Toolkit components will
     * use your executors. You may also want to use this for your tests.
     * <p>
     * Calling this method with {@code null} sets it to the default TaskExecutor.
     *
     * @param taskExecutor The task executor to handle task requests.
     */
    public void setDelegate(TaskExecutor taskExecutor) {
        mDelegate = taskExecutor == null ? mDefaultTaskExecutor : taskExecutor;
    }

    /* (non-Javadoc)
     * @see android.arch.core.executor.TaskExecutor#executeOnDiskIO(java.lang.Runnable)
     */
    @Override
    public void executeOnDiskIO(Runnable runnable) {
        mDelegate.executeOnDiskIO(runnable);
    }

    /* (non-Javadoc)
     * @see android.arch.core.executor.TaskExecutor#postToMainThread(java.lang.Runnable)
     */
    @Override
    public void postToMainThread(Runnable runnable) {
        mDelegate.postToMainThread(runnable);
    }

    /**
     * Gets the main thread executor.
     *
     * @return the main thread executor
     */
    public static Executor getMainThreadExecutor() {
        return sMainThreadExecutor;
    }

    /**
     * Gets the IO thread executor.
     *
     * @return the IO thread executor
     */
    public static Executor getIOThreadExecutor() {
        return sIOThreadExecutor;
    }

    /* (non-Javadoc)
     * @see android.arch.core.executor.TaskExecutor#isMainThread()
     */
    @Override
    public boolean isMainThread() {
        return mDelegate.isMainThread();
    }
}
