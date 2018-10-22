package com.hao.myutils.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class PixelatedUtil {

    private PixelatedUtil() {
    }

    public static Bitmap pixelated(Bitmap source, int width) {
        int srcWidth = source.getWidth();
        int srcHeight = source.getHeight();
        float srcAspect = (float) srcWidth / srcHeight;
        int pixelWidth = srcWidth / width;
        int pixelHeight = (int) (pixelWidth / srcAspect);
        int height = (int) (width / srcAspect);
        if (srcWidth < width || pixelWidth <= 1) {
            return source;
        }

        Bitmap result = Bitmap.createBitmap(width, (int) (width / srcAspect), Bitmap.Config.ARGB_4444);
        for (int i = 1; i < width; i++) {
            int x = pixelWidth / 2 + (i - 1) * pixelWidth;
            for (int j = 1; j < height; j++) {
                int y = pixelHeight / 2 + (j - 1) * pixelHeight;
                int color = source.getPixel(x, y);
                result.setPixel(i, j, color);
            }
        }
        return result;
    }

    /**
     * 普通图－>像素图，left、top、right、bottom可指定打马赛克区域
     */
    public static Bitmap pixelated(Bitmap bitmap, int zoneWidth, int left, int top, int right, int bottom) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap result = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        for (int i = left; i < right; i += zoneWidth) {
            for (int j = top; j < bottom; j += zoneWidth) {
                int color = bitmap.getPixel(i, j);
                paint.setColor(color);
                int gridRight = Math.min(w, i + zoneWidth);
                int gridBottom = Math.min(h, j + zoneWidth);
                canvas.drawRect(i, j, gridRight, gridBottom, paint);
            }
        }
        return result;
    }

}
