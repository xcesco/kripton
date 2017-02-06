package com.abubusoft.kripton.android;

/**
 * <P>
 * Allows to specify how a BindAsyncTask works with the data source for which it
 * is generated. In a generated async task a data source can be:
 * </p>
 * 
 * <ul>
 * <li><strong>opened in read mode</strong>: data source is opened in read mode
 * and only SELECT operation are admitted. Async task will manage open/close
 * connetion operations.</li>
 * <li><strong>opened in read/write mode</strong>: every operation on data
 * source are admitted. Async task will manage open/close connetion
 * operations.</li>
 * <li><strong>unmanaged mode</strong>: async task will NOT manage open/close
 * connetion operations. You can to manage it as you want</li>
 * </ul>
 * 
 * @author xcesco
 *
 */
public enum BindAsyncTaskType {
	/**
	 * data source is opened in read mode and only SELECT operation are
	 * admitted. Async task will manage open/close connetion operations.
	 */
	READ,

	/**
	 * every operation on data source are admitted. Async task will manage
	 * open/close connetion operations.
	 */
	READ_WRITE,

	/**
	 * async task will NOT manage open/close connetion operations. You can
	 * manage it as you want
	 */
	UNMANAGE
}