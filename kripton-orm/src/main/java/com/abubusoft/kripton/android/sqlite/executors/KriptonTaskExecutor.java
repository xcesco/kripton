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

import java.util.concurrent.Executor;

/**
 * A static class that serves as a central point to execute common tasks.
 * <p>
 *
 * @hide This API is not final.
 */
public class KriptonTaskExecutor extends TaskExecutor {
	private static volatile KriptonTaskExecutor sInstance;

	private TaskExecutor mDelegate;

	private TaskExecutor mDefaultTaskExecutor;

	private static final Executor sMainThreadExecutor = new Executor() {
		@Override
		public void execute(Runnable command) {
			getInstance().postToMainThread(command);
		}
	};

	private static final Executor sIOThreadExecutor = new Executor() {
		@Override
		public void execute(Runnable command) {
			getInstance().executeOnDiskIO(command);
		}
	};

	private KriptonTaskExecutor() {
		mDefaultTaskExecutor = new KriptonDefaultTaskExecutor();
		mDelegate = mDefaultTaskExecutor;
	}

	/**
	 * Returns an instance of the task executor.
	 *
	 * @return The singleton ArchTaskExecutor.
	 */
	public static KriptonTaskExecutor getInstance() {
		if (sInstance != null) {
			return sInstance;
		}
		synchronized (KriptonTaskExecutor.class) {
			if (sInstance == null) {
				sInstance = new KriptonTaskExecutor();
			}
		}
		return sInstance;
	}

	/**
	 * Sets a delegate to handle task execution requests.
	 * <p>
	 * If you have a common executor, you can set it as the delegate and App
	 * Toolkit components will use your executors. You may also want to use this
	 * for your tests.
	 * <p>
	 * Calling this method with {@code null} sets it to the default
	 * TaskExecutor.
	 *
	 * @param taskExecutor
	 *            The task executor to handle task requests.
	 */
	public void setDelegate(TaskExecutor taskExecutor) {
		mDelegate = taskExecutor == null ? mDefaultTaskExecutor : taskExecutor;
	}

	@Override
	public void executeOnDiskIO(Runnable runnable) {
		mDelegate.executeOnDiskIO(runnable);
	}

	@Override
	public void postToMainThread(Runnable runnable) {
		mDelegate.postToMainThread(runnable);
	}

	public static Executor getMainThreadExecutor() {
		return sMainThreadExecutor;
	}

	public static Executor getIOThreadExecutor() {
		return sIOThreadExecutor;
	}

	@Override
	public boolean isMainThread() {
		return mDelegate.isMainThread();
	}
}
