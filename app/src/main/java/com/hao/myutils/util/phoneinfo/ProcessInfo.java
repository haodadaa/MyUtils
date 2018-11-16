package com.hao.myutils.util.phoneinfo;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ProcessInfo {

    public static final int INDEX_FIRST = -1;
    public static final int INDEX_PID = INDEX_FIRST + 1;
    public static final int INDEX_CPU = INDEX_FIRST + 2;
    public static final int INDEX_STAT = INDEX_FIRST + 3;
    public static final int INDEX_THR = INDEX_FIRST + 4;
    public static final int INDEX_VSS = INDEX_FIRST + 5;
    public static final int INDEX_RSS = INDEX_FIRST + 6;
    public static final int INDEX_PCY = INDEX_FIRST + 7;
    public static final int INDEX_UID = INDEX_FIRST + 8;
    public static final int INDEX_NAME = INDEX_FIRST + 9;
    public static final int INDEX_APPNAME = INDEX_FIRST + 10;
    public static final int Length_ProcStat = 9;

    public List<String[]> PMUList = null;

    public int[] pid_array;

    public ProcessInfo() {

    }

    public static String getProcessRunningInfo() {
        Log.i("fetch_process_info", "start. . . . ");
        String result = null;
        CMDExecute execute = new CMDExecute();
        try {
            String[] args = {"/system/bin/top", "-n", "1"};
            result = execute.run(args, "/system/bin/");
        } catch (IOException ex) {
            Log.i("fetch_process_info", "ex=" + ex.toString());
        }
        //System.out.println("kk"+result);
        return result;
    }

    private int parseProcessRunningInfo(String infoString) {
        String tempString;
        boolean bIsProcInfo = false;

        String[] rows;
        String[] columns;
        rows = infoString.split("[\n]+"); // 使用正则表达式分割字符串

        pid_array = new int[rows.length];
        //System.out.println(""+rows.length);
        for (int i = 0; i < rows.length; i++) {
            pid_array[i] = 0;
            tempString = rows[i];
            if (!tempString.contains("PID")) {
                if (bIsProcInfo) {
                    tempString = tempString.trim();
                    columns = tempString.split("[ ]+");
                    //if (columns.length == Length_ProcStat) {
                    pid_array[i] = Integer.parseInt(columns[0]);
                    PMUList.add(columns);
                    //}
                }
            } else {
                bIsProcInfo = true;
            }
        }

        return PMUList.size();
    }

    // 初始化所有进程的CPU和内存列表，用于检索每个进程的信息
    public void initProcessInfo() {
        PMUList = new ArrayList<>();
        String resultString = getProcessRunningInfo();
        int processNumber = parseProcessRunningInfo(resultString);
        Log.e("result", resultString);
        //System.out.println("result;"+resultString);
        //System.out.println(""+processNumber);
    }

    public String getNameByPid(int pid) {
        String result = "";

        String tempPidString = "";
        int tempPid = 0;
        int count = PMUList.size();
        for (int i = 0; i < count; i++) {
            String[] item = PMUList.get(i);
            tempPidString = item[INDEX_PID];
            if (tempPidString == null) {
                continue;
            }
            tempPid = Integer.parseInt(tempPidString);
            if (tempPid == pid) {
                result = item[INDEX_NAME];
                break;
            }
        }
        return result;
    }

    // 根据进程名获取CPU和内存信息
    public String getMemInfoByName(String procName) {
        String result = "";

        String tempString;
        for (Iterator<String[]> iterator = PMUList.iterator(); iterator
                .hasNext(); ) {
            String[] item = iterator.next();
            tempString = item[INDEX_NAME];
            if (tempString != null && tempString.equals(procName)) {
                result = "CPU:" + item[INDEX_CPU] + "  Mem:" + item[INDEX_RSS];
                break;
            }
        }
        return result;
    }

    // 根据进程ID获取CPU和内存信息
    public String getMemInfoByPid(int pid) {
        String result = "";

        String tempPidString;
        int tempPid;
        int count = PMUList.size();
        for (int i = 0; i < count; i++) {
            String[] item = PMUList.get(i);
            tempPidString = item[INDEX_PID];
            if (tempPidString == null) {
                continue;
            }
            tempPid = Integer.parseInt(tempPidString);
            if (tempPid == pid) {
                result = "CPU:" + item[INDEX_CPU] + "  Mem:" + item[INDEX_RSS];
                break;
            }
        }
        return result;
    }
}
