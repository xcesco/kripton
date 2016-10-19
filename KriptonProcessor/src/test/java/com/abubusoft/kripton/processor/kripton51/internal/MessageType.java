package com.abubusoft.kripton.processor.kripton51.internal;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public enum MessageType {
    SYSTEM_CHANNEL_CREATED,
    SYSTEM_USER_ADDED,
    SYSTEM_USER_EXITS,
    SYSTEM_USER_BANNED,
    SYSTEM_CHANNEL_DESTROYED,
    TEXT,
    ACTION;

    private MessageType() {
    }
}