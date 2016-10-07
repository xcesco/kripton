package com.abubusoft.kripton.tutorial;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class User  {
    public long id;
    public String email;
    public String name;
    public String surname;
    public String username;
}