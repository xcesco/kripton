package com.abubusoft.kripton.processor.kripton47;

import com.abubusoft.kripton.android.annotation.BindSharedPreferences;
import com.abubusoft.kripton.annotation.BindType;

@BindType
@BindSharedPreferences
public class SecurityConfig {

    /**
     * FCM-ID
     */
    public String fcmId;

    /**
     * Autorization token
     */
    public DeviceAccessToken authorizationToken;

    /**
     * device UID
     */
    public String deviceUid;

    /**
     * user identitfy
     */
    public UserIdentity userIdentity;
}
