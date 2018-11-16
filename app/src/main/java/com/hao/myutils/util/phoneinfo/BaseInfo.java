package com.hao.myutils.util.phoneinfo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseInfo {

    public Resources res = null;
    //public static Http_post hp = new Http_post();
    public static String IMEI = null;


    public static void initialize() {

    }

    public static String fetchVersionInfo() {
        String result = null;
        CMDExecute cmdexe = new CMDExecute();
        try {
            String[] args = {"/system/bin/cat", "/proc/version"};
            result = cmdexe.run(args, "system/bin/");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

//    public static String fetchIMEI(Context cx) {
//        TelephonyManager tm = (TelephonyManager) cx
//                .getSystemService(Context.TELEPHONY_SERVICE);
//        IMEI = tm.getDeviceId();
//        return IMEI;
//    }

    public static String fetchCpuInfo() {
        String result = null;
        CMDExecute cmdexe = new CMDExecute();
        try {
            String[] args = {"/system/bin/cat", "/proc/cpuinfo"};
            result = cmdexe.run(args, "/system/bin/");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 系统内存情况查看
     */
    public static String getMemoryInfo(Context context) {
        StringBuffer memoryInfo = new StringBuffer();

        final ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(outInfo);

        //memoryInfo.append("\nTotal Available Memory :").append(
        //	outInfo.availMem >> 10).append("K");
        memoryInfo.append("\nTotal Available Memory :").append(
                outInfo.availMem >> 20).append("M");
        memoryInfo.append("\nIn low memory situation:").append(
                outInfo.lowMemory);

        String result = null;
        CMDExecute cmdexe = new CMDExecute();
        try {
            String[] args = {"/system/bin/cat", "/proc/meminfo"};
            result = cmdexe.run(args, "/system/bin/");
        } catch (IOException ex) {
            Log.i("fetch_process_info", "ex=" + ex.toString());
        }
        return (memoryInfo.toString() + "\n\n" + result);
    }

    // 磁盘信息
    public static String fetchDiskInfo() {
        String result = null;
        CMDExecute cmdexe = new CMDExecute();
        try {
            String[] args = {"/system/bin/df"};
            result = cmdexe.run(args, "/system/bin/");
            //Log.i("result", "result=" + result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    //网络信息
    public static String fetch_netcfg_info() {
		String result = null;
		CMDExecute cmdexe = new CMDExecute();
		Log.e("Yang", "String fetch_netcfg_info()");
		try {
			String[] args = { "/system/bin/netcfg" };
			result = cmdexe.run(args, "/system/bin/");
			String regEx="(?<=eth0[\\s]{5}UP[\\s]{4})[\\S]+(?=\\s)";
			Log.i("Basic_info", regEx);
			Pattern p= Pattern.compile(regEx);
			Matcher m=p.matcher(result);
			boolean br=m.find();
			// IpAddress.getLocalIPAddress()=m.group();
			Log.e("Yang", IpAddress.getLocalIPAddress());
			//int i;
			//for(i=0;i<ipaddress.length();i++)
				//Log.i("Basic info ipaddr", ""+ipaddress.charAt(i));
			Log.i("Basic_info", m.group());
		} catch (Exception ex) {
			Log.i("fetch_netcfg_info", "ex=" + ex.toString());
		}
		return result;
	}

    public static String getDisplayMetrics(Context cx) {
        String str = "";
        DisplayMetrics dm = new DisplayMetrics();
        dm = cx.getApplicationContext().getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        float density = dm.density;
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        str += "The absolute width: " + String.valueOf(screenWidth)
                + "pixels\n";
        str += "The absolute heightin: " + String.valueOf(screenHeight)
                + "pixels\n";
        str += "The logical density of the display. : "
                + String.valueOf(density) + "\n";
        str += "X dimension : " + String.valueOf(xdpi) + "pixels per inch\n";
        str += "Y dimension : " + String.valueOf(ydpi) + "pixels per inch\n";
        return str;
    }
}
