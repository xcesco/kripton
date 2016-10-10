package com.abubusoft.kripton.processor.kripton51.persistence;

import com.abubusoft.kripton.android.annotation.BindDataSource;

/**
 * Created by xcesco on 27/09/2016.
 */

@BindDataSource(value={DaoMessage.class}, fileName = "whisper", version = 1)
public interface WhisperDataSource {
}
