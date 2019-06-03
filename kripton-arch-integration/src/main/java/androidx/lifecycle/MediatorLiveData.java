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

package androidx.lifecycle;


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
public class MediatorLiveData<T>  extends android.arch.lifecycle.MediatorLiveData<T>  {
    
}
