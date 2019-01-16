package com.hao.myutils.autogenerate;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;

public class XmlSequenceImage {

    @Test
    public void generateXml() {
        String dirPath = "/Users/yanhh/YHH/XML";
        String fileName = "lottery_cat_wakeup.xml";
        String prefixName = "wakeup_";
        int firstSuffix = 0;
        int endSuffix = 37;
        int duration = 83;
        File outFile = new File(dirPath, fileName);
        generateFile(outFile, prefixName, firstSuffix, endSuffix, duration);
    }

    public static void generateFile(File outFile, String prefixName,
                                    int firstSuffix, int endSuffix, int duration) {

        FileWriter fw = null;

        try {
            //拼接字符串
            StringBuilder outSb = concatStr(prefixName, firstSuffix, endSuffix, duration);
            //写出到磁盘
            fw = new FileWriter(outFile);
            fw.write(outSb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static StringBuilder concatStr(String prefixName, int firstSuffix, int endSuffix, int duration) {
        StringBuilder outSb = new StringBuilder();
        outSb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<animation-list xmlns:android=\"http://schemas.android.com/apk/res/android\">\n");

        for (int i = firstSuffix; i <= endSuffix; i++) {
            outSb.append("    <item\n" +
                    "        android:drawable=\"@drawable/");
            outSb.append(prefixName);
            outSb.append(i);
            outSb.append("\""
                    + "\n        android:duration=\"");
            outSb.append(duration);
            outSb.append("\" />\n");
        }
        outSb.append("</animation-list>");
        return outSb;
    }
}
