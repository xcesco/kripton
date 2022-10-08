/*
 * Copyright 2019 The Android Open Source Project
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

import android.content.Context;
import android.content.SharedPreferences;
import android.util.ArraySet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.DeterministicAead;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of {@link SharedPreferences} that encrypts keys and values.
 *
 * <pre>
 *  String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
 *
 *  SharedPreferences sharedPreferences = EncryptedSharedPreferences.create(
 *      "secret_shared_prefs",
 *      masterKeyAlias,
 *      context,
 *      EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
 *      EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
 *  );
 *
 *  // use the shared preferences and editor as you normally would
 *  SharedPreferences.Editor editor = sharedPreferences.edit();
 * </pre>
 */
public final class EncryptedSharedPreferences implements SharedPreferences {

    private static SharedPreferences prefs;

    EncryptedSharedPreferences(@NonNull String name,
                               @NonNull String masterKeyAlias,
                               @NonNull SharedPreferences sharedPreferences,
                               @NonNull Aead aead,
                               @NonNull DeterministicAead deterministicAead) {
    }

    /**
     * Opens an instance of encrypted SharedPreferences
     *
     * @param fileName                  The name of the file to open; can not contain path
     *                                  separators.
     * @param masterKey                 The master key to use.
     * @param prefKeyEncryptionScheme   The scheme to use for encrypting keys.
     * @param prefValueEncryptionScheme The scheme to use for encrypting values.
     * @return The SharedPreferences instance that encrypts all data.
     * @throws GeneralSecurityException when a bad master key or keyset has been attempted
     * @throws IOException              when fileName can not be used
     */
    @NonNull
    public static SharedPreferences create(@NonNull Context context,
                                           @NonNull String fileName,
                                           @NonNull MasterKey masterKey,
                                           @NonNull PrefKeyEncryptionScheme prefKeyEncryptionScheme,
                                           @NonNull PrefValueEncryptionScheme prefValueEncryptionScheme)
            throws GeneralSecurityException, IOException {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs;
    }

    /**
     * Opens an instance of encrypted SharedPreferences
     *
     * @param fileName                  The name of the file to open; can not contain path
     *                                  separators.
     * @param masterKeyAlias            The alias of the master key to use.
     * @param context                   The context to use to open the preferences file.
     * @param prefKeyEncryptionScheme   The scheme to use for encrypting keys.
     * @param prefValueEncryptionScheme The scheme to use for encrypting values.
     * @return The SharedPreferences instance that encrypts all data.
     * @throws GeneralSecurityException when a bad master key or keyset has been attempted
     * @throws IOException              when fileName can not be used
     * @deprecated Use {@link #create(Context, String, MasterKey,
     * PrefKeyEncryptionScheme, PrefValueEncryptionScheme)} instead.
     */
    @Deprecated
    @NonNull
    public static SharedPreferences create(@NonNull String fileName,
                                           @NonNull String masterKeyAlias,
                                           @NonNull Context context,
                                           @NonNull PrefKeyEncryptionScheme prefKeyEncryptionScheme,
                                           @NonNull PrefValueEncryptionScheme prefValueEncryptionScheme)
            throws GeneralSecurityException, IOException {

        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * The encryption scheme to encrypt keys.
     */
    public enum PrefKeyEncryptionScheme {
        /**
         * Pref keys are encrypted deterministically with AES256-SIV-CMAC (RFC 5297).
         * <p>
         * For more information please see the Tink documentation:
         *
         * <a href="https://google.github.io/tink/javadoc/tink/1.4.0-rc2/com/google/crypto/tink/daead/AesSivKeyManager.html">AesSivKeyManager</a>.aes256SivTemplate()
         */
        AES256_SIV
    }

    /**
     * The encryption scheme to encrypt values.
     */
    public enum PrefValueEncryptionScheme {
        /**
         * Pref values are encrypted with AES256-GCM. The associated data is the encrypted pref key.
         * <p>
         * For more information please see the Tink documentation:
         *
         * <a href="https://google.github.io/tink/javadoc/tink/1.4.0-rc2/com/google/crypto/tink/aead/AesGcmKeyManager.html">AesGcmKeyManager</a>.aes256GcmTemplate()
         */
        AES256_GCM
    }

    private static final class Editor implements SharedPreferences.Editor {

        Editor(EncryptedSharedPreferences encryptedSharedPreferences,
               SharedPreferences.Editor editor) {
        }

        @Override
        @NonNull
        public SharedPreferences.Editor putString(@Nullable String key, @Nullable String value) {
            return prefs.edit();
        }

        @Override
        @NonNull
        public SharedPreferences.Editor putStringSet(@Nullable String key,
                                                     @Nullable Set<String> values) {
            return prefs.edit();
        }

        @Override
        @NonNull
        public SharedPreferences.Editor putInt(@Nullable String key, int value) {
            return this;
        }

        @Override
        @NonNull
        public SharedPreferences.Editor putLong(@Nullable String key, long value) {
            return this;
        }

        @Override
        @NonNull
        public SharedPreferences.Editor putFloat(@Nullable String key, float value) {
            return this;
        }

        @Override
        @NonNull
        public SharedPreferences.Editor putBoolean(@Nullable String key, boolean value) {
            return this;
        }

        @Override
        @NonNull
        public SharedPreferences.Editor remove(@Nullable String key) {
            return this;
        }

        @Override
        @NonNull
        public SharedPreferences.Editor clear() {
            return this;
        }

        @Override
        public boolean commit() {
            return false;
        }

        @Override
        public void apply() {
        }


    }

    // SharedPreferences methods

    @Override
    @NonNull
    public Map<String, ?> getAll() {
        Map<String, ? super Object> allEntries = new HashMap<>();
        return allEntries;
    }

    @Nullable
    @Override
    public String getString(@Nullable String key, @Nullable String defValue) {
        return "";
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public Set<String> getStringSet(@Nullable String key, @Nullable Set<String> defValues) {
        return new ArraySet<>();
    }

    @Override
    public int getInt(@Nullable String key, int defValue) {
        return 0;
    }

    @Override
    public long getLong(@Nullable String key, long defValue) {
        return 0L;
    }

    @Override
    public float getFloat(@Nullable String key, float defValue) {
        return 0.0f;
    }

    @Override
    public boolean getBoolean(@Nullable String key, boolean defValue) {
        return false;
    }

    @Override
    public boolean contains(@Nullable String key) {
        return false;
    }

    @Override
    @NonNull
    public SharedPreferences.Editor edit() {
        return null;
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(
            @NonNull OnSharedPreferenceChangeListener listener) {

    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(
            @NonNull OnSharedPreferenceChangeListener listener) {
    }

    /**
     * Internal enum to set the type of encrypted data.
     */
    private enum EncryptedType {
        STRING(0),
        STRING_SET(1),
        INT(2),
        LONG(3),
        FLOAT(4),
        BOOLEAN(5);

        private final int mId;

        EncryptedType(int id) {
            mId = id;
        }

        public int getId() {
            return mId;
        }

        public static EncryptedType fromId(int id) {
            switch (id) {
                case 0:
                    return STRING;
                case 1:
                    return STRING_SET;
                case 2:
                    return INT;
                case 3:
                    return LONG;
                case 4:
                    return FLOAT;
                case 5:
                    return BOOLEAN;
            }
            return null;
        }
    }

}
