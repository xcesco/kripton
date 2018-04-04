package com.abubusoft.kripton.isocodes.input;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

/**
 * Created by xcesco on 25/02/2017.
 */

@BindType
public class CountryCode {

    public String name;

    @Bind("alpha-2")
    public String regionCode;

}
