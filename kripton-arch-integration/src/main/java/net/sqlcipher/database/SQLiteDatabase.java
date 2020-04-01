package net.sqlcipher.database;

import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Pair;

public class SQLiteDatabase {
	
	public interface CursorFactory {

		net.sqlcipher.Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver masterQuery, String editTable,
				SQLiteQuery query);

	}

	public static final String OPEN_READONLY = null;
	public static final String OPEN_READWRITE = null;


	public SQLiteStatement compileStatement(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	public void beginTransaction() {
		// TODO Auto-generated method stub
		
	}

	public void beginTransactionNonExclusive() {
		// TODO Auto-generated method stub
		
	}

	public void beginTransactionWithListener(
			net.sqlcipher.database.SQLiteTransactionListener sqLiteTransactionListener) {
		// TODO Auto-generated method stub
		
	}

	public void beginTransactionWithListenerNonExclusive(
			net.sqlcipher.database.SQLiteTransactionListener sqLiteTransactionListener) {
		// TODO Auto-generated method stub
		
	}

	public void endTransaction() {
		// TODO Auto-generated method stub
		
	}

	public void setTransactionSuccessful() {
		// TODO Auto-generated method stub
		
	}

	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean inTransaction() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isDbLockedByCurrentThread() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean yieldIfContendedSafely() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean yieldIfContendedSafely(long sleepAfterYieldDelay) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setVersion(int version) {
		// TODO Auto-generated method stub
		
	}

	public long getMaximumSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long setMaximumSize(long numBytes) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long getPageSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPageSize(long numBytes) {
		// TODO Auto-generated method stub
		
	}

	public Cursor rawQueryWithFactory(CursorFactory cursorFactory, String sql, String[] bindings, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public void execSQL(String sql) {
		// TODO Auto-generated method stub
		
	}

	public void execSQL(String sql, Object[] bindArgs) {
		// TODO Auto-generated method stub
		
	}

	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean needUpgrade(int newVersion) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLocale(Locale locale) {
		// TODO Auto-generated method stub
		
	}

	public void setMaxSqlCacheSize(int cacheSize) {
		// TODO Auto-generated method stub
		
	}

	public void setForeignKeyConstraintsEnabled(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	public boolean enableWriteAheadLogging() {
		// TODO Auto-generated method stub
		return false;
	}

	public void disableWriteAheadLogging() {
		// TODO Auto-generated method stub
		
	}

	public boolean isWriteAheadLoggingEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Pair<String, String>> getAttachedDbs() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isDatabaseIntegrityOk() {
		// TODO Auto-generated method stub
		return false;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public void changePassword(char[] passphrase) {
		// TODO Auto-generated method stub
		
	}

	public static byte[] getBytes(char[] passphrase) {
		// TODO Auto-generated method stub
		return null;
	}

	public long insertWithOnConflict(String table, Object object, ContentValues values, int conflictAlgorithm) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void loadLibs(Context context) {
		// TODO Auto-generated method stub
		
	}

	public static SQLiteDatabase openDatabase(String absolutePath, String string, Object object, String openReadonly) {
		// TODO Auto-generated method stub
		return null;
	}

	public static SQLiteDatabase openDatabase(String absolutePath, byte[] passphrase, Object object,
			String openReadwrite, Object object2, Object object3) {
		// TODO Auto-generated method stub
		return null;
	}

	public void rawExecSQL(String string) {
		// TODO Auto-generated method stub
		
	}

}
