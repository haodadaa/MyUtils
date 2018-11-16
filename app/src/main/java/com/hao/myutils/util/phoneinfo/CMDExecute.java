package com.hao.myutils.util.phoneinfo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class CMDExecute {

    public synchronized String run(String[] cmd, String workdirectory)
            throws IOException {
        String result = "";
        try {
            ProcessBuilder builder = new ProcessBuilder(cmd);
            BufferedReader in = null;
            // 设置一个路径
            if (workdirectory != null) {
                builder.directory(new File(workdirectory));
                builder.redirectErrorStream(true);
                Process process = builder.start();
                process.waitFor();
                in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                char[] re = new char[1024];
                int readNum;
                while ((readNum = in.read(re, 0, 1024)) != -1) {
                    // re[readNum] = '\0';
                    result = result + new String(re, 0, readNum);
                }
            }
            if (in != null) {
                in.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public synchronized String run_su(String cmd)
            throws IOException {
        String result = "";
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataOutputStream os = new DataOutputStream(p.getOutputStream());
        BufferedReader is = new BufferedReader(new InputStreamReader(p
                .getInputStream()));
        try {
            String istmp;
            os.writeBytes(cmd + "\n");
            //os.writeBytes("exit\n");
            os.flush();

            //istmp=is.readLine();
            //result=istmp;
            /*while(istmp!= null)
			{
				result=istmp;
				istmp=is.readLine();
			}*/

            os.writeBytes("exit\n");
            os.flush();
            p.waitFor();
            is.close();
            os.close();
            p.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
}
