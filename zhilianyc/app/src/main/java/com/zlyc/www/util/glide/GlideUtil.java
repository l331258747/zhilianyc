package com.zlyc.www.util.glide;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.zlyc.www.R;
import com.zlyc.www.util.AppUtils;

import java.io.File;

/**
 * Created by LGQ
 * Time: 2018/7/23
 * Function: glide工具类
 */

public class GlideUtil {


    /**
     * 设置缓存策略 diskCacheStrategy
     * all:缓存源资源和转换后的资源
     * none:不作任何磁盘缓存
     * source:缓存源资源
     * result：缓存转换后的资源
     *
     * 占位图 默认图片 placeholder
     * 加载失败 error
     *
     * 设置加载动画 crossFade
     *
     * 设置加载尺寸 override
     *
     * 设置动态转换
     *  api提供了比如：centerCrop()、fitCenter()等函数也可以通过自定义Transformation，举例说明：比如一个人圆角转化器
     *
     *  缓存的动态清理
     *  Glide.get(this).clearDiskCache();//清理磁盘缓存 需要在子线程中执行
     *  Glide.get(this).clearMemory();//清理内存缓存  可以在UI主线程中进行
     *
     */


    /**
     * 加载gif图
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadGifImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }


    /**
     * 加载本地图片文件
     *
     * @param context
     * @param file
     * @param imageView
     */
    public static void loadFileImage(Context context, File file, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop();

        Glide.with(context)
                .load(file)
                .apply(requestOptions)
                .into(imageView);
    }

    /**
     * 加载图片指定大小
     *
     * @param context
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    public static void loadSizeImage(Context context, String url, ImageView imageView, int width, int height) {
        RequestOptions requestOptions = new RequestOptions()
                .priority(Priority.HIGH)
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }


    /**
     * 加载圆角图片
     * @param context
     * @param url
     * @param imageView
     * @param radius 圆角大小
     */
    public static void loadRoundImage(Context context, String url, ImageView imageView, int radius) {
        if(TextUtils.isEmpty(url)) url = "";

        radius = AppUtils.dip2px(radius);

        RequestOptions requestOptions = new RequestOptions()
                .priority(Priority.HIGH)
                .dontAnimate()
                .error(R.mipmap.default_head)
                .placeholder(R.mipmap.default_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transforms(new CenterCrop(), new RoundedCorners(radius));

        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }


    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        if(TextUtils.isEmpty(url)) url = "";
        RequestOptions requestOptions = new RequestOptions()
//                .priority(Priority.HIGH)
                .dontAnimate()
                .error(R.mipmap.default_head)
                .placeholder(R.mipmap.default_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(new CircleCrop());

        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }



    /**
     * 默认加载方式
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        if(TextUtils.isEmpty(url)) url = "";
        RequestOptions requestOptions = new RequestOptions()
                .priority(Priority.HIGH)
                .error(R.mipmap.default_head)
                .placeholder(R.mipmap.default_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }


    public static void loadTopImage(Context context, String url, ImageView imageView,int px){
        if(TextUtils.isEmpty(url)) url = "";
        RequestOptions options = new RequestOptions()
                .fitCenter() /*处理源图片ScaleType*/
                .priority(Priority.HIGH)
                .error(R.mipmap.default_head)
                .placeholder(R.mipmap.default_head)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new RoundedCornersTransform(px, px, 0, 0));

        Glide.with(context).load(url).apply(options).into(imageView);
    }

}
