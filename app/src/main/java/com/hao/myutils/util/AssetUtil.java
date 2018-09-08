package com.hao.myutils.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetUtil {
    private AssetUtil() {
    }

    public static String getAsset2String(Context context, String filePath) {
        if (context == null) {
            return null;
        }

        try {
            InputStream is = context.getAssets().open(filePath);
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
                builder.append("\n");
            }
            return builder.toString();
        } catch (IOException ignore) {
            return null;
        }
    }
}
