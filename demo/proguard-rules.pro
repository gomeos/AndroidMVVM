# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/liuyuxuan/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-ignorewarning
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes Signature
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations
-keepattributes EnclosingMethod

-keep class com.android.support.**{ *; }
-keep interface android.support.v4.app.**{ *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment

-keep public class * extends com.gomeos.mvvm.viewmodel.ViewModel{*;}
-keep public class * extends android.view.View{*;}
-keep public class * extends com.gomeos.mvvm.view.factory.ItemViewFactory{*;}
-keep public class com.gomeos.mvvm.view.LayoutManagers{*;}

#DataBinding
-dontwarn android.databinding.**
-keep class android.databinding.** { *; }
-dontwarn android.databinding.tool.util.**

# EventBus 3.0.x #
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

# rxjava
-keep class rx.operators.OperationReplay* {
    *;
}

-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}

-assumenosideeffects class android.util.Log{
    public static *** v(...);
    public static *** i(...);
    public static *** d(...);
    public static *** w(...);
    public static *** e(...);
}
-assumenosideeffects class java.io.PrintStream{
    public void println(...);
    public void print(...);
}