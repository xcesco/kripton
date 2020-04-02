package net.sqlcipher.database;

import androidx.sqlite.db.SupportSQLiteStatement;

public class SQLiteStatement extends SQLiteProgram implements SupportSQLiteStatement {

	public void execute() {
		// TODO Auto-generated method stub
		
	}

	public int executeUpdateDelete() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long executeInsert() {
		// TODO Auto-generated method stub
		return 0;
	}

	public long simpleQueryForLong() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String simpleQueryForString() {
		// TODO Auto-generated method stub
		return null;
	}

	public void bindString(int i, String absolutePath) {
		// TODO Auto-generated method stub
		
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bindNull(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bindLong(int index, long value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bindDouble(int index, double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bindBlob(int index, byte[] value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearBindings() {
		// TODO Auto-generated method stub
		
	}

}
