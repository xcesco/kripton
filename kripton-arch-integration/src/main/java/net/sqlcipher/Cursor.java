package net.sqlcipher;

public interface Cursor extends android.database.Cursor {
	/*
     * Values returned by {@link #getType(int)}.
     * These should be consistent with the corresponding types defined in CursorWindow.h
     */
    /** Value returned by {@link #getType(int)} if the specified column is null */
    static final int FIELD_TYPE_NULL = 0;

    /** Value returned by {@link #getType(int)} if the specified  column type is integer */
    static final int FIELD_TYPE_INTEGER = 1;

    /** Value returned by {@link #getType(int)} if the specified column type is float */
    static final int FIELD_TYPE_FLOAT = 2;

    /** Value returned by {@link #getType(int)} if the specified column type is string */
    static final int FIELD_TYPE_STRING = 3;

    /** Value returned by {@link #getType(int)} if the specified column type is blob */
    static final int FIELD_TYPE_BLOB = 4;

	 int getType(int columnIndex);

}
