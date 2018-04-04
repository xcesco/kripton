##############################
# Kripton Persistence Library
##############################
-keep class com.abubusoft.kripton.annotation.** { *; }
-keep @com.abubusoft.kripton.annotation.BindType class ** { *; }
-keep @com.abubusoft.kripton.annotation.BindMap class ** { *; }
-dontwarn okio.**

##############################
# Retrofit
##############################
-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*

#
# https://stackoverflow.com/questions/33047806/proguard-duplicate-definition-of-library-class
#
-dontnote android.net.http.*
-dontnote org.apache.commons.codec.**
-dontnote org.apache.http.**

#
# https://github.com/square/okhttp/issues/3355
#
-dontwarn okio.**
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.* { *; }
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault

-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations
-keepattributes EnclosingMethod

-keepclasseswithmembers class * {
    @retrofit2.* <methods>;
}

-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

##############################
# Glide
##############################

-keep class com.abubusoft.kripton.quickstart.network.okhttp3.OkHttpGlideModule { *; }

