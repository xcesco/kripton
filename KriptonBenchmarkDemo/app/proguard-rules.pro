# LoganSquare
-keep class com.bluelinelabs.logansquare.** { *; }
-keep @com.bluelinelabs.logansquare.annotation.JsonObject class *
-keep class **$$JsonObjectMapper { *; }

# Jackson
-dontwarn org.w3c.dom.bootstrap.DOMImplementationRegistry

# Kripton
-keep class com.abubusoft.kripton.annotation.** { *; }
-keep @com.abubusoft.kripton.annotation.BindType class ** { *; }
-keep @com.abubusoft.kripton.annotation.BindMap class ** { *; }