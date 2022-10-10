/*
 * Copyright 2020 The Android Open Source Project
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

package androidx.security.crypto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Wrapper for a master key used in the library.
 * <p>
 * On Android M (API 23) and above, this is class references a key that's stored in the
 * Android Keystore. On Android L (API 21, 22), there isn't a master key.
 */
public final class MasterKey {


    /**
     * Algorithm/Cipher choices used for the master key.
     */
    public enum KeyScheme {
        AES256_GCM
    }


    /* package */ MasterKey(@NonNull String keyAlias, @Nullable Object keyGenParameterSpec) {
        // STUB
    }

    /**
     * Checks if this key is backed by the Android Keystore.
     *
     * @return {@code true} if the key is in Android Keystore, {@code false} otherwise. This
     * method always returns false when called on Android Lollipop (API 21 and 22).
     */
    public boolean isKeyStoreBacked() {
        return false;
    }

    /**
     * Gets whether user authentication is required to use this key.
     * <p>
     * This method always returns {@code false} on Android L (API 21 + 22).
     */
    public boolean isUserAuthenticationRequired() {
        return false;
    }

    /**
     * Gets the duration in seconds that the key is unlocked for following user authentication.
     * <p>
     * The value returned for this method is only meaningful on Android M+ (API 23) when
     * {@link #isUserAuthenticationRequired()} returns {@code true}.
     *
     * @return The duration the key is unlocked for in seconds.
     */
    @SuppressLint("MethodNameUnits")
    public int getUserAuthenticationValidityDurationSeconds() {
        return 0;
    }

    /**
     * Gets whether the key is backed by strong box.
     */
    public boolean isStrongBoxBacked() {
        return false;
    }


    @NonNull/* package */ String getKeyAlias() {
        return "";
    }

    /**
     * Builder for generating a {@link MasterKey}.
     */
    public static final class Builder {

        @Nullable
        private KeyGenParameterSpec mKeyGenParameterSpec;
        @Nullable
        private KeyScheme mKeyScheme;

        private boolean mAuthenticationRequired;
        private int mUserAuthenticationValidityDurationSeconds;
        private boolean mRequestStrongBoxBacked;

        private final Context mContext;

        public Builder(@NonNull Context context) {
            mContext = context;
        }


        /**
         * Creates a builder for a {@link MasterKey}.
         *
         * @param context The context to use with this master key.
         */
        public Builder(@NonNull Context context, @NonNull String keyAlias) {
            mContext = context.getApplicationContext();

        }

        /**
         * Sets a {@link KeyScheme} to be used for the master key.
         * This uses a default {@link KeyGenParameterSpec} associated with the provided
         * {@code KeyScheme}.
         * NOTE: Either this method OR {@link #setKeyGenParameterSpec} should be used to set
         * the parameters to use for building the master key. Calling either function after
         * the other will throw an {@link IllegalArgumentException}.
         *
         * @param keyScheme The KeyScheme to use.
         * @return This builder.
         */
        @NonNull
        public Builder setKeyScheme(@NonNull KeyScheme keyScheme) {
            return this;
        }

        @NonNull
        public Builder setRequestStrongBoxBacked(boolean requestStrongBoxBacked) {
            mRequestStrongBoxBacked = requestStrongBoxBacked;
            return this;
        }

        /**
         * Sets a custom {@link KeyGenParameterSpec} to use as the basis of the master key.
         * NOTE: Either this method OR {@link #setKeyGenParameterSpec} should be used to set
         * the parameters to use for building the master key. Calling either function after
         * the other will throw an {@link IllegalArgumentException}.
         *
         * @param keyGenParameterSpec The key spec to use.
         * @return This builder.
         */
        @NonNull
        public Builder setKeyGenParameterSpec(@NonNull KeyGenParameterSpec keyGenParameterSpec) {
            return this;
        }

        /**
         * Builds a {@link MasterKey} from this builder.
         *
         * @return The master key.
         */
        @NonNull
        public MasterKey build() throws GeneralSecurityException, IOException {
            return new MasterKey("stub", "stub");
        }
    }
}
