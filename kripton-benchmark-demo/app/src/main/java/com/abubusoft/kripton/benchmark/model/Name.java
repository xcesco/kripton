package com.abubusoft.kripton.benchmark.model;

import com.abubusoft.kripton.annotation.BindType;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

@BindType
@JsonObject
public class Name {

    @JsonField
    public String first;

    @JsonField
    public String last;
}
