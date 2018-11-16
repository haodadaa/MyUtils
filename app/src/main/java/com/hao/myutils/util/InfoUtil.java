package com.hao.myutils.util;

import android.os.Build;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InfoUtil {

    private InfoUtil() {}

    private static final String CPU_INFO_PATH = "/proc/cpuinfo";

    public static String getCpuName() {
        String str2;
        try {
            FileReader fr = new FileReader(CPU_INFO_PATH);
            BufferedReader localBufferedReader = new BufferedReader(fr);
            while ((str2 = localBufferedReader.readLine()) != null) {
                if (str2.contains("Hardware")) {
                    return str2.split(":")[1];
                }
            }
            localBufferedReader.close();
        } catch (IOException e) {
        }
        return Build.HARDWARE;
    }

}
