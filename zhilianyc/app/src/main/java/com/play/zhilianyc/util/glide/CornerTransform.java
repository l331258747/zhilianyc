package com.play.zhilianyc.util.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by LGQ
 * Time: 2018/7/23
 * Function: 圆角图片
 */

public class CornerTransform extends BitmapTransformation {

    private float radius = 0f;
    private boolean exceptLeftTop, exceptRightTop, exceptLeftBottom, exceptRightBotoom;

    public CornerTransform(Context context) {
        this(context, 4);
    }

    public CornerTransform(Context context, int dp) {
        super(context);
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    public void setExceptCorner(boolean leftTop, boolean rightTop, boolean leftBottom, boolean rightBottom) {
        this.exceptLeftTop = leftTop;
        this.exceptRightTop = rightTop;
        this.exceptLeftBottom = leftBottom;
        this.exceptRightBotoom = rightBottom;
    }


    @Override protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);


        if (exceptLeftTop) { //左上角不为圆角
            canvas.drawRect(0, 0, radius, radius, paint);
        }
        if (exceptRightTop) {//右上角不为圆角
            canvas.drawRect(canvas.getWidth() - radius, 0, canvas.getWidth(), radius, paint);
        }

        if (exceptLeftBottom) {//左下角不为圆角
            canvas.drawRect(0, canvas.getHeight() - radius, radius, canvas.getHeight(), paint);
        }

        if (exceptRightBotoom) {//右下角不为圆角
            canvas.drawRect(canvas.getWidth() - radius, canvas.getHeight() - radius, canvas.getWidth(), canvas.getHeight(), paint);
        }

        return result;
    }

    @Override public String getId() {
        return getClass().getName() + Math.round(radius);
    }

}


