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
-dontwarn okio.**

# Gson
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class benchmark.model.** { *; }

# Prevent proguard from stripping interface information from TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer