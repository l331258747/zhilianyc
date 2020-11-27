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

#---------------------------------实体类---------------------------------
# 自定义数据模型的bean目录
-keep class com.zqlc.www.bean.**{*;}

#---------------------------------基本指令区----------------------------------
# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
-optimizationpasses 5
# 混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
# 指定不去忽略非公共库的类
-dontskipnonpubliclibraryclasses
# 指定不去忽略非公共库的类成员
-dontskipnonpubliclibraryclassmembers
# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify
# 这句话能够使我们的项目混淆后产生映射文件
# 包含有类名->混淆后类名的映射关系
-verbose
# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*
# 保留Annotation不混淆
-keepattributes *Annotation*,InnerClasses
# 避免混淆泛型
-keepattributes Signature
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

-printmapping proguardMapping.txt
-dontoptimize
-keepattributes Exceptions,Deprecated,EnclosingMethod
#----------------------------------------------------------------------------


#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}

# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

#androidx包使用混淆
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**
#----------------------------------------------------------------------------


#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}

#----------------------------------------------------------------------------

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}
-keep interface okhttp3.**{*;}


#okio
-dontwarn okio.**
-keep class okio.**{*;}
-keep interface okio.**{*;}

#RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Gson specific classes
#gson
-keep class com.google.gson.** {*;}
-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }



#retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

#banner
-keep class com.youth.banner.** {
    *;
 }

#glide 4
-keep public class * implements com.bumptech.glide.module.AppGlideModule
-keep public class * implements com.bumptech.glide.module.LibraryGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


# 百度定位
-keep class vi.com.gdi.** { *; }
-keep public class com.baidu.** {*;}
-keep public class com.mobclick.** {*;}
-dontwarn com.baidu.mapapi.utils.*
-dontwarn com.baidu.platform.comapi.b.*
-dontwarn com.baidu.platform.comapi.map.*



#游戏流
-keep class com.qq.e.** {
public protected *;
}
-keep class android.support.v4.**{
public *;
}
-keep class android.support.v7.**{
public *;
}
-keep class MTT.ThirdAppInfoNew {
*;
}
-keep class com.tencent.** {
*;
}
-keep class com.androidquery.callback.** {*;}
-keep class com.ss.sys.ces.a {*;}
-keep class com.ss.android.socialbase.** {*;}
-keep class org.chromium.** {*;}
-keep class org.chromium.** { *; }
-keep class aegon.chrome.** { *; }
-keep class com.kwai.**{ *; }
-dontwarn com.kwai.**
-dontwarn com.kwad.**
-dontwarn com.ksad.**
-dontwarn aegon.chrome.**

#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
**[] $VALUES;
public *;
}
-keep public class vlion.cn.** { *; }


-keep class pl.droidsonroids.gif.sample.GifSelectorDrawable { *; }

-keep class com.bird.** { *; }
-keep class com.birdhfn.** { *; }
-keep class com.core.**{*;}
-keep class com.hfn0xx2.**{*;}
-keep class com.ss.**{*;}


-keep class com.wannuosili.sdk.** {*;}
-dontwarn com.wannuosili.sdk.**


-keep class com.jd.ad.sdk.core.event.CustomAdEvent {*;}
-keep class com.jd.ad.sdk.imp.splash.CustomSplashEvent {*;}
-keep class com.jd.ad.sdk.adapter.TT*




#新闻流
-keep class com.qq.e.** {
public protected *;
}
-keep class android.support.v4.**{
public *;
}
-keep class android.support.v7.**{
public *;
}
-keep class MTT.ThirdAppInfoNew {
*;
}
-keep class com.tencent.** {
*;
}
-keep class com.bytedance.sdk.openadsdk.** { *; }
-keep class com.androidquery.callback.** {*;}
-keep public interface com.bytedance.sdk.openadsdk.downloadnew.** {*;}
-keep class com.ss.sys.ces.a {*;}
-keep class com.ss.android.socialbase.** {*;}
-keep class org.chromium.** {*;}
-keep class org.chromium.** { *; }
-keep class aegon.chrome.** { *; }
-keep class com.kwai.**{ *; }
-dontwarn com.kwai.**
-dontwarn com.kwad.**
-dontwarn com.ksad.**
-dontwarn aegon.chrome.**


-keep class com.tencent.**{*;}

-keep public class vlion.cn.** { *; }


#二维码
-keep public class com.uuzuche.lib_zxing.**{*;}
-keep public class com.google.zxing.**{*;}

-keep public class com.amulyakhare.**{*;}
-keep public class com.github.PhilJay.**{*;}
-keep public class com.luozm.captcha.**{*;}
-keep public class com.android.support.**{*;}


#三级联动
-keep public class com.bigkoo.pickerview.**{*;}
-keep public class com.contrarywind.**{*;}


#抽奖
-ignorewarnings
-dontwarn com.mediamain.android.**
-keep class com.mediamain.android.** { *; }



#激励视频
-keep class com.pgl.sys.ces.* {*;}
-keep class com.qq.e.comm.plugin.** {
*;
}
-keep class com.qq.e.** {
public protected *;
}
-keep class android.support.v4.**{
public *;
}
-keep class android.support.v7.**{
public *;
}
-keep class MTT.ThirdAppInfoNew {
*;
}
-keep class com.tencent.** {
*;
}
-dontwarn dalvik.**
-dontwarn com.tencent.smtt.**
-keep class yaq.gdtadv{
*;
}
-keep class com.qq.e.** {
*;
}
-keep class com.tencent.** {
*;
}
-keep class cn.mmachina.JniClient {
*;
}
-keep class c.t.m.li.tsa.** {
*;
}

-keep class * extends java.lang.annotation.Annotation { *; }
-keep interface * extends java.lang.annotation.Annotation { *; }

-keep, allowobfuscation class com.qq.e.comm.plugin.services.SDKServerService {*;}
-keepclassmembers, allowobfuscation class com.qq.e.comm.plugin.net.SecurePackager {
public *;
}

-keepclasseswithmembers,includedescriptorclasses class * {
native <methods>;
}

-keep class * extends com.qq.e.mediation.interfaces.BaseNativeUnifiedAd { *; }
-keep class * extends com.qq.e.mediation.interfaces.BaseSplashAd { *; }
-keep class * extends com.qq.e.mediation.interfaces.BaseRewardAd { *; }

-keep class org.chromium.** {*;}
-keep class org.chromium.** { *; }
-keep class aegon.chrome.** { *; }
-keep class com.kwai.**{ *; }
-keep class com.kwad.**{ *; }
-keepclasseswithmembernames class * {
native <methods>;
}
-dontwarn com.kwai.**
-dontwarn com.kwad.**
-dontwarn com.ksad.**
-dontwarn aegon.chrome.**