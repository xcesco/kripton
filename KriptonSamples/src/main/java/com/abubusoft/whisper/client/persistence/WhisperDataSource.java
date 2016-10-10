package com.abubusoft.whisper.client.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 27/09/2016.
 */

@BindDataSource(value={DaoChannel.class, DaoMessage.class}, fileName = "whisper", version = 1)
public interface WhisperDataSource {
}
