package net.sqlcipher.database;

public interface SQLiteTransactionListener {

	void onBegin();

	void onCommit();

	void onRollback();

}
