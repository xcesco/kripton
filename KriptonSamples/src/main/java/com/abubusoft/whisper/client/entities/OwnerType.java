package com.abubusoft.whisper.client.entities;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public enum OwnerType {
    OWN_MESSAGE,
    OTHER_MESSAGE,
    SYSTEM_MESSAGE;
}