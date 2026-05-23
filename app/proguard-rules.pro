# Retrofit
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes *Annotation*

# Serialization
-keepclassmembers class * {
    @kotlinx.serialization.Serializable <fields>;
}

# Compose
-keep class androidx.compose.** { *; }

# Keep all model classes
-keep class com.glyphdev.glyph.data.** { *; }
-keep class com.glyphdev.glyph.domain.** { *; }

# General
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception
