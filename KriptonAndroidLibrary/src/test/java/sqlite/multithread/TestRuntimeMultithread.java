/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.Logger;

import base.BaseAndroidTest;

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class TestRuntimeMultithread extends BaseAndroidTest {

	@Test
	public void testMultithreadWritable() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(5);

		for (int c = 0; c < 5; c++) {
			final int start = c * 10;
			final int threadId = c;
			Runnable worker = new Runnable() {

				@Override
				public void run() {
					int id = threadId;
					Logger.info("Start thread-" + id);
					try (BindPersonDataSource dataSource = BindPersonDataSource.open()) {
						PersonDAOImpl dao = dataSource.getPersonDAO();
						Person bean = new Person();

						for (int i = start; i < start + 10; i++) {
							bean.name = "name" + i;
							bean.surname = "surname" + i;
							dao.insertThread1(bean);
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					Logger.info("End thread-" + id);

				}
			};
			executor.execute(worker);
		}

		executor.shutdown();
		while (!executor.isTerminated()) {

		}

		Logger.info("Finished all thread!");

	}

	@Test
	public void testMultithreadMixed() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		Runnable worker;
		
		//BindPersonDataSource.open();
		//BindPersonDataSource.instance().close();

		for (int c = 0; c < 10; c++) {
			Thread.sleep(10);
			final int start = c * 10;
			final int threadId = c;

			switch (c % 4) {
			case 0:
				worker = new Runnable() {

					@Override
					public void run() {
						int id = threadId;
						Logger.info("Start thread-" + id+" T0");
						try (BindPersonDataSource dataSource = BindPersonDataSource.open()) {
							PersonDAOImpl dao = dataSource.getPersonDAO();
							Person bean = new Person();

							for (int i = start; i < start + 10; i++) {
								bean.name = "name" + i;
								bean.surname = "surname" + i;
								dao.insertThread1(bean);
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
						Logger.info("End thread-" + id+" T0");

					}
				};
				break;
			case 1:
				worker = new Runnable() {

					@Override
					public void run() {
						int id = threadId;
						Logger.info("Start thread-" + id+" T1");
						try (BindPersonDataSource dataSource = BindPersonDataSource.openReadOnly()) {
							PersonDAOImpl dao = dataSource.getPersonDAO();
							Person bean = new Person();

							for (int i = start; i < start + 10; i++) {
								bean.name = "name" + i;
								bean.surname = "surname" + i;
								dao.selectThread1();
							}
						}
						Logger.info("End thread-" + id+" T1");

					}
				};
				break;
			case 2:
				worker = new Runnable() {

					@Override
					public void run() {
						int id = threadId;
						Logger.info("Start thread-" + id+" T2");
						try (BindPersonDataSource dataSource = BindPersonDataSource.openReadOnly()) {
							PersonDAOImpl dao = dataSource.getPersonDAO();
							Person bean = new Person();

							for (int i = start; i < start + 10; i++) {
								bean.name = "name" + i;
								bean.surname = "surname" + i;
								dao.selectThread2();
							}
						}
						Logger.info("End thread-" + id+" T2");

					}
				};
				break;
			case 3:
				worker = new Runnable() {

					@Override
					public void run() {
						int id = threadId;
						Logger.info("Start thread-" + id+" T3");
						BindPersonDataSource dataSource = BindPersonDataSource.instance();
						dataSource.execute(new BindPersonDataSource.SimpleTransaction() {

							@Override
							public boolean onExecute(BindPersonDaoFactory daoFactory) throws Throwable {
								PersonDAOImpl dao = daoFactory.getPersonDAO();
								Person bean = new Person();

								for (int i = start; i < start + 10; i++) {
									bean.name = "name" + i;
									bean.surname = "surname" + i;
									dao.insertThread1(bean);
									try {
										Thread.sleep(20);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}

								return true;
							}
						});
						Logger.info("End thread-" + id+" T3");

					}
				};

				break;
			default:
				worker=null;
				break;
			}

			if (worker != null)
				executor.execute(worker);
		}

		executor.shutdown();
		while (!executor.isTerminated()) {

		}

		Logger.info("Finished all thread!");

	}

}
