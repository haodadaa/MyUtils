package com.hao.myutils.util;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InfoUtil {

    private InfoUtil() {
    }

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

//    public static String getDeviceID(){
//        // 反射获取
//        String CPUID =  android.os.SystemProperties.get("ro.hardware.cpuid", "0");
//        return CPUID;
//    }

    public static Map<String, String> getCPUInfo() throws IOException {
        Map<String, String> output = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("/proc/cpuinfo"));
        String str;
        while ((str = br.readLine()) != null) {
            String[] data = str.split(":");
            if (data.length > 1) {
                String key = data[0].trim().replace(" ", "_");
                if (key.equals("model_name")) key = "cpu_model";
                String value = data[1].trim();
                if (key.equals("cpu_model"))
                    value = value.replaceAll("\\s+", " ");
                output.put(key, value);
            }
        }

        br.close();
        return output;
    }

    // 实时获取CPU当前频率（单位KHZ）
    public static String getCurCpuFreq() {
        String result = "N/A";
        try {
            FileReader fr = new FileReader(
                    "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            result = text.trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void logMemoryInfo() {
        String str1 = "/proc/meminfo";
        String str2;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            while ((str2 = localBufferedReader.readLine()) != null) {
                Log.e("memoryInfo", "---" + str2);
            }
        } catch (IOException e) {
        }
    }

    // 获取rom大小
    public static long[] getRomMemroy() {
        long[] romInfo = new long[2];
        romInfo[0] = getTotalInternalMemorySize();

        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        romInfo[1] = blockSize * availableBlocks;
        return romInfo;
    }

    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    // 获取SDCard大小
    public static long[] getSDCardMemory() {
        long[] sdCardInfo = new long[2];
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(sdcardDir.getPath());
            long bSize = sf.getBlockSize();
            long bCount = sf.getBlockCount();
            long availBlocks = sf.getAvailableBlocks();

            sdCardInfo[0] = bSize * bCount;//总大小
            sdCardInfo[1] = bSize * availBlocks;//可用大小
        }
        return sdCardInfo;
    }

    // 获取系统版本信息
    public static String[] getVersion() {
        String[] version = {"null", "null", "null", "null"};
        String str1 = "/proc/version";
        String str2;
        String[] arrayOfString;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            version[0] = arrayOfString[2];//KernelVersion
            localBufferedReader.close();
        } catch (IOException e) {
        }
        version[1] = Build.VERSION.RELEASE;// firmware version
        version[2] = Build.MODEL;//model
        version[3] = Build.DISPLAY;//system version
        return version;
    }

    // 获取系统开机时间
    public static String getTimes() {
        long ut = SystemClock.elapsedRealtime() / 1000;
        if (ut == 0) {
            ut = 1;
        }
        int m = (int) ((ut / 60) % 60);
        int h = (int) ((ut / 3600));
        return h + " " + "h" + m + " " + "m";
    }
}
