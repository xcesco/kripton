package sqlite.feature.custombean.case2;

import java.util.Calendar;
import java.util.Date;

import com.abubusoft.kripton.android.sqlite.SQLitePopulator;
import com.abubusoft.kripton.android.sqlite.TransactionResult;

import android.support.annotation.NonNull;
import android.util.Log;
import sqlite.feature.custombean.case2.BindAppDataSource.Transaction;

public class DataSourcePopulator implements SQLitePopulator {

	// Simulate a blocking operation delaying each Loan insertion with a delay:
	private static final int DELAY_MILLIS = 500;

	public void populateSync(@NonNull final BindAppDataSource db) {
		populateWithTestData(db);
	}

	private void addLoan(final BindAppDaoFactory daoFactory, final String id, final User user, final Book book, Date from, Date to) {
		Loan loan = new Loan();
		loan.id = id;
		loan.bookId = book.id;
		loan.userId = user.id;
		loan.startTime = from;
		loan.endTime = to;
		daoFactory.getLoanDao().insertLoan(loan);
	}

	private Book addBook(final BindAppDaoFactory daoFactory, final String id, final String title) {
		Book book = new Book();
		book.id = id;
		book.title = title;
		daoFactory.getBookDao().insertBook(book);
		return book;
	}

	private User addUser(final BindAppDaoFactory daoFactory, final String id, final String name, final String lastName, final int age) {
		User user = new User();
		user.id = id;
		user.age = age;
		user.name = name;
		user.lastName = lastName;
		daoFactory.getUserDao().insertUser(user);
		return user;
	}

	private void populateWithTestData(BindAppDaoFactory daoFactory) {
		daoFactory.getLoanDao().deleteAll();
		daoFactory.getUserDao().deleteAll();
		daoFactory.getBookDao().deleteAll();

		User user1 = addUser(daoFactory, "1", "Jason", "Seaver", 40);
		User user2 = addUser(daoFactory, "2", "Mike", "Seaver", 12);
		addUser(daoFactory, "3", "Carol", "Seaver", 15);

		Book book1 = addBook(daoFactory, "1", "Dune");
		Book book2 = addBook(daoFactory, "2", "1984");
		Book book3 = addBook(daoFactory, "3", "The War of the Worlds");
		Book book4 = addBook(daoFactory, "4", "Brave New World");
		addBook(daoFactory, "5", "Foundation");
		try {
			// Loans are added with a delay, to have time for the UI to react to
			// changes.

			Date today = getTodayPlusDays(0);
			Date yesterday = getTodayPlusDays(-1);
			Date twoDaysAgo = getTodayPlusDays(-2);
			Date lastWeek = getTodayPlusDays(-7);
			Date twoWeeksAgo = getTodayPlusDays(-14);

			addLoan(daoFactory, "1", user1, book1, twoWeeksAgo, lastWeek);
			Thread.sleep(DELAY_MILLIS);
			addLoan(daoFactory, "2", user2, book1, lastWeek, yesterday);
			Thread.sleep(DELAY_MILLIS);
			addLoan(daoFactory, "3", user2, book2, lastWeek, today);
			Thread.sleep(DELAY_MILLIS);
			addLoan(daoFactory, "4", user2, book3, lastWeek, twoDaysAgo);
			Thread.sleep(DELAY_MILLIS);
			addLoan(daoFactory, "5", user2, book4, lastWeek, today);
			Log.d("DB", "Added loans");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static Date getTodayPlusDays(int daysAgo) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, daysAgo);
		return calendar.getTime();
	}

	/**
	 * Execute the populator.
	 */
	@Override
	public void execute() {
		BindAppDataSource.getInstance().executeAsync(new Transaction() {
			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
				populateWithTestData(daoFactory);
				return TransactionResult.COMMIT;
			}
		});
	}
	/*
	 * private static class PopulateDbAsync extends BindAppAsynTask
	 * AsyncTask<Void, Void, Void> {
	 * 
	 * private final AppDatabase mDb;
	 * 
	 * PopulateDbAsync(AppDatabase db) { mDb = db; }
	 * 
	 * @Override protected Void doInBackground(final Void... params) {
	 * populateWithTestData(mDb); return null; }
	 * 
	 * }
	 */
}
