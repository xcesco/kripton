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

package androidx.arch.core.executor.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

import androidx.annotation.NonNull;

// TODO: Auto-generated Javadoc
/**
 * LinkedList, which pretends to be a map and supports modifications during iterations.
 * It is NOT thread safe.
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class SafeIterableMap<K, V> implements Iterable<Map.Entry<K, V>> {

    /** The m start. */
    private Entry<K, V> mStart;
    
    /** The m end. */
    private Entry<K, V> mEnd;
    // using WeakHashMap over List<WeakReference>, so we don't have to manually remove
    /** The m iterators. */
    // WeakReferences that have null in them.
    private WeakHashMap<SupportRemove<K, V>, Boolean> mIterators = new WeakHashMap<>();
    
    /** The m size. */
    private int mSize = 0;

    /**
     * Gets the.
     *
     * @param k the k
     * @return the entry
     */
    protected Entry<K, V> get(K k) {
        Entry<K, V> currentNode = mStart;
        while (currentNode != null) {
            if (currentNode.mKey.equals(k)) {
                break;
            }
            currentNode = currentNode.mNext;
        }
        return currentNode;
    }

    /**
     * If the specified key is not already associated
     * with a value, associates it with the given value.
     *
     * @param key key with which the specified value is to be associated
     * @param v   value to be associated with the specified key
     * @return the previous value associated with the specified key,
     * or {@code null} if there was no mapping for the key
     */
    public V putIfAbsent(@NonNull K key, @NonNull V v) {
        Entry<K, V> entry = get(key);
        if (entry != null) {
            return entry.mValue;
        }
        put(key, v);
        return null;
    }

    /**
     * Put.
     *
     * @param key the key
     * @param v the v
     * @return the entry
     */
    protected Entry<K, V> put(@NonNull K key, @NonNull V v) {
        Entry<K, V> newEntry = new Entry<>(key, v);
        mSize++;
        if (mEnd == null) {
            mStart = newEntry;
            mEnd = mStart;
            return newEntry;
        }

        mEnd.mNext = newEntry;
        newEntry.mPrevious = mEnd;
        mEnd = newEntry;
        return newEntry;

    }

    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with the specified key,
     * or {@code null} if there was no mapping for the key
     */
    public V remove(@NonNull K key) {
        Entry<K, V> toRemove = get(key);
        if (toRemove == null) {
            return null;
        }
        mSize--;
        if (!mIterators.isEmpty()) {
            for (SupportRemove<K, V> iter : mIterators.keySet()) {
                iter.supportRemove(toRemove);
            }
        }

        if (toRemove.mPrevious != null) {
            toRemove.mPrevious.mNext = toRemove.mNext;
        } else {
            mStart = toRemove.mNext;
        }

        if (toRemove.mNext != null) {
            toRemove.mNext.mPrevious = toRemove.mPrevious;
        } else {
            mEnd = toRemove.mPrevious;
        }

        toRemove.mNext = null;
        toRemove.mPrevious = null;
        return toRemove.mValue;
    }

    /**
     * Size.
     *
     * @return the number of elements in this map
     */
    public int size() {
        return mSize;
    }

    /**
     * Iterator.
     *
     * @return an ascending iterator, which doesn't include new elements added during an
     * iteration.
     */
    @NonNull
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        ListIterator<K, V> iterator = new AscendingIterator<>(mStart, mEnd);
        mIterators.put(iterator, false);
        return iterator;
    }

    /**
     * Descending iterator.
     *
     * @return an descending iterator, which doesn't include new elements added during an
     * iteration.
     */
    public Iterator<Map.Entry<K, V>> descendingIterator() {
        DescendingIterator<K, V> iterator = new DescendingIterator<>(mEnd, mStart);
        mIterators.put(iterator, false);
        return iterator;
    }

    /**
     * return an iterator with additions.
     *
     * @return the iterator with additions
     */
    public IteratorWithAdditions iteratorWithAdditions() {
        @SuppressWarnings("unchecked")
        IteratorWithAdditions iterator = new IteratorWithAdditions();
        mIterators.put(iterator, false);
        return iterator;
    }

    /**
     * Eldest.
     *
     * @return eldest added entry or null
     */
    public Map.Entry<K, V> eldest() {
        return mStart;
    }

    /**
     * Newest.
     *
     * @return newest added entry or null
     */
    public Map.Entry<K, V> newest() {
        return mEnd;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SafeIterableMap)) {
            return false;
        }
        SafeIterableMap map = (SafeIterableMap) obj;
        if (this.size() != map.size()) {
            return false;
        }
        Iterator<Map.Entry<K, V>> iterator1 = iterator();
        Iterator iterator2 = map.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()) {
            Map.Entry<K, V> next1 = iterator1.next();
            Object next2 = iterator2.next();
            if ((next1 == null && next2 != null)
                    || (next1 != null && !next1.equals(next2))) {
                return false;
            }
        }
        return !iterator1.hasNext() && !iterator2.hasNext();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Iterator<Map.Entry<K, V>> iterator = iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next().toString());
            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * The Class ListIterator.
     *
     * @param <K> the key type
     * @param <V> the value type
     */
    private abstract static class ListIterator<K, V> implements Iterator<Map.Entry<K, V>>,
            SupportRemove<K, V> {
        
        /** The m expected end. */
        Entry<K, V> mExpectedEnd;
        
        /** The m next. */
        Entry<K, V> mNext;

        /**
         * Instantiates a new list iterator.
         *
         * @param start the start
         * @param expectedEnd the expected end
         */
        ListIterator(Entry<K, V> start, Entry<K, V> expectedEnd) {
            this.mExpectedEnd = expectedEnd;
            this.mNext = start;
        }

        /* (non-Javadoc)
         * @see java.util.Iterator#hasNext()
         */
        @Override
        public boolean hasNext() {
            return mNext != null;
        }

        /* (non-Javadoc)
         * @see android.arch.core.internal.SafeIterableMap.SupportRemove#supportRemove(android.arch.core.internal.SafeIterableMap.Entry)
         */
        @Override
        public void supportRemove(@NonNull Entry<K, V> entry) {
            if (mExpectedEnd == entry && entry == mNext) {
                mNext = null;
                mExpectedEnd = null;
            }

            if (mExpectedEnd == entry) {
                mExpectedEnd = backward(mExpectedEnd);
            }

            if (mNext == entry) {
                mNext = nextNode();
            }
        }

        /**
         * Next node.
         *
         * @return the entry
         */
        private Entry<K, V> nextNode() {
            if (mNext == mExpectedEnd || mExpectedEnd == null) {
                return null;
            }
            return forward(mNext);
        }

        /* (non-Javadoc)
         * @see java.util.Iterator#next()
         */
        @Override
        public Map.Entry<K, V> next() {
            Map.Entry<K, V> result = mNext;
            mNext = nextNode();
            return result;
        }

        /**
         * Forward.
         *
         * @param entry the entry
         * @return the entry
         */
        abstract Entry<K, V> forward(Entry<K, V> entry);

        /**
         * Backward.
         *
         * @param entry the entry
         * @return the entry
         */
        abstract Entry<K, V> backward(Entry<K, V> entry);
    }

    /**
     * The Class AscendingIterator.
     *
     * @param <K> the key type
     * @param <V> the value type
     */
    static class AscendingIterator<K, V> extends ListIterator<K, V> {
        
        /**
         * Instantiates a new ascending iterator.
         *
         * @param start the start
         * @param expectedEnd the expected end
         */
        AscendingIterator(Entry<K, V> start, Entry<K, V> expectedEnd) {
            super(start, expectedEnd);
        }

        /* (non-Javadoc)
         * @see android.arch.core.internal.SafeIterableMap.ListIterator#forward(android.arch.core.internal.SafeIterableMap.Entry)
         */
        @Override
        Entry<K, V> forward(Entry<K, V> entry) {
            return entry.mNext;
        }

        /* (non-Javadoc)
         * @see android.arch.core.internal.SafeIterableMap.ListIterator#backward(android.arch.core.internal.SafeIterableMap.Entry)
         */
        @Override
        Entry<K, V> backward(Entry<K, V> entry) {
            return entry.mPrevious;
        }

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
    }

    /**
     * The Class DescendingIterator.
     *
     * @param <K> the key type
     * @param <V> the value type
     */
    private static class DescendingIterator<K, V> extends ListIterator<K, V> {

        /**
         * Instantiates a new descending iterator.
         *
         * @param start the start
         * @param expectedEnd the expected end
         */
        DescendingIterator(Entry<K, V> start, Entry<K, V> expectedEnd) {
            super(start, expectedEnd);
        }

        /* (non-Javadoc)
         * @see android.arch.core.internal.SafeIterableMap.ListIterator#forward(android.arch.core.internal.SafeIterableMap.Entry)
         */
        @Override
        Entry<K, V> forward(Entry<K, V> entry) {
            return entry.mPrevious;
        }

        /* (non-Javadoc)
         * @see android.arch.core.internal.SafeIterableMap.ListIterator#backward(android.arch.core.internal.SafeIterableMap.Entry)
         */
        @Override
        Entry<K, V> backward(Entry<K, V> entry) {
            return entry.mNext;
        }

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
    }

    /**
     * The Class IteratorWithAdditions.
     */
    private class IteratorWithAdditions implements Iterator<Map.Entry<K, V>>, SupportRemove<K, V> {
        
        /** The m current. */
        private Entry<K, V> mCurrent;
        
        /** The m before start. */
        private boolean mBeforeStart = true;

        /* (non-Javadoc)
         * @see android.arch.core.internal.SafeIterableMap.SupportRemove#supportRemove(android.arch.core.internal.SafeIterableMap.Entry)
         */
        @Override
        public void supportRemove(@NonNull Entry<K, V> entry) {
            if (entry == mCurrent) {
                mCurrent = mCurrent.mPrevious;
                mBeforeStart = mCurrent == null;
            }
        }

        /* (non-Javadoc)
         * @see java.util.Iterator#hasNext()
         */
        @Override
        public boolean hasNext() {
            if (mBeforeStart) {
                return mStart != null;
            }
            return mCurrent != null && mCurrent.mNext != null;
        }

        /* (non-Javadoc)
         * @see java.util.Iterator#next()
         */
        @Override
        public Map.Entry<K, V> next() {
            if (mBeforeStart) {
                mBeforeStart = false;
                mCurrent = mStart;
            } else {
                mCurrent = mCurrent != null ? mCurrent.mNext : null;
            }
            return mCurrent;
        }

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
    }

    /**
     * The Interface SupportRemove.
     *
     * @param <K> the key type
     * @param <V> the value type
     */
    interface SupportRemove<K, V> {
        
        /**
         * Support remove.
         *
         * @param entry the entry
         */
        void supportRemove(@NonNull Entry<K, V> entry);
    }

    /**
     * The Class Entry.
     *
     * @param <K> the key type
     * @param <V> the value type
     */
    static class Entry<K, V> implements Map.Entry<K, V> {
        
        /** The m key. */
        @NonNull
        final K mKey;
        
        /** The m value. */
        @NonNull
        final V mValue;
        
        /** The m next. */
        Entry<K, V> mNext;
        
        /** The m previous. */
        Entry<K, V> mPrevious;

        /**
         * Instantiates a new entry.
         *
         * @param key the key
         * @param value the value
         */
        Entry(@NonNull K key, @NonNull V value) {
            mKey = key;
            this.mValue = value;
        }

        /* (non-Javadoc)
         * @see java.util.Map.Entry#getKey()
         */
        @NonNull
        @Override
        public K getKey() {
            return mKey;
        }

        /* (non-Javadoc)
         * @see java.util.Map.Entry#getValue()
         */
        @NonNull
        @Override
        public V getValue() {
            return mValue;
        }

        /* (non-Javadoc)
         * @see java.util.Map.Entry#setValue(java.lang.Object)
         */
        @Override
        public V setValue(V value) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        /* (non-Javadoc)
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return mKey + "=" + mValue;
        }

        /* (non-Javadoc)
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            return mKey.equals(entry.mKey) && mValue.equals(entry.mValue);
        }
    }
}
