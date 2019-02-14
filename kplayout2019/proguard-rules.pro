# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**
-dontwarn okio.**
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
-keep class om.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**
-keepclassmembers enum com.kplayout2019.** { *; }
-keep class org.anddev.andengine.* { *; }
-dontwarn org.anddev.andengine.**
-keep class com.badlogic.gdx.physics.box2d.* { *; }
-dontwarn com.badlogic.gdx.physics.box2d.**

-dontwarn android.databinding.**
-keep class android.databinding.** { *; }
