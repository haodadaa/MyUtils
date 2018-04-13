package com.hao.myutils.util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {

    private FileUtil() {
    }

    //string写入应用私有目录,保存在fileName中
    public void writeString2File(Context ctx, String fileName, String content) {
        try {
            FileOutputStream fos = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读取文件string
    public String readFile(Context ctx, String fileName) {
        try {
            FileInputStream inStream = ctx.openFileInput(fileName);
            int len;
            byte[] buf = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while ((len = inStream.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            inStream.close();
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
