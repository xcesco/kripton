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

/**
 * LiveData which publicly exposes #setValue(T) and #postValue(T) method.
 *
 * @param <T> The type of data hold by this instance
 */
public class MutableLiveData<T> extends LiveData<T> {
    
    /* (non-Javadoc)
     * @see android.arch.lifecycle.LiveData#postValue(java.lang.Object)
     */
    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    /* (non-Javadoc)
     * @see android.arch.lifecycle.LiveData#setValue(java.lang.Object)
     */
    @Override
    public void setValue(T value) {
        super.setValue(value);
    }
}
