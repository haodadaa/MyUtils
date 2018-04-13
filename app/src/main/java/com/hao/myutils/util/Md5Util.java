package com.hao.myutils.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    private Md5Util(){}

    public static String getFileMD5String(File file) throws IOException {
        InputStream fis;
        MessageDigest messageDigest = null;

        fis = new FileInputStream(file);
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (messageDigest == null) {
            return null;
        }
        byte[] buffer = new byte[1024];
        int numRead;
        while ((numRead = fis.read(buffer)) > 0) {
            messageDigest.update(buffer, 0, numRead);
        }
        fis.close();
        return bufferToHex(messageDigest.digest());

//        MessageDigest digest = MessageDigest.getInstance("MD5");
//        FileInputStream inputStream=new FileInputStream("test.txt");
//        //对于大文件或者网络文件使用输入流的形式要比字节数组方便很多也节省内存
//        DigestInputStream dis=new DigestInputStream(inputStream, degest);
//        byte[] buffer=new byte[8986];
//        ByteArrayOutputStream fileOutput=new ByteArrayOutputStream();
//        while((dis.read(buffer))!=-1){//跟读普通输入流是一样的，原理就是需要将输入流读完后，再调用digest方法才能获取整个文件的MD5
//            fileOutput.write(buffer);
//        }
//        String fileContent=fileOutput.toString();//读取到的文件的内容
//        byte[] summary=digest.digest();
//        StringBuffer strBuffer = new StringBuffer();
//        for (int i = 0; i < summary.length; i++) {
//　　String tmp=Integer.toHexString(summary[i]&0xff);
//　　if(tmp.length()==1)//如果这个字节的值小于16，那么转换的就只有一个字符，所以需要手动添加一个字符“0”，
//　　　　tmp="0"+tmp;
//            strBuffer.append(tmp);
//        }
//        System.out.println(strBuffer.toString());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    /**
     * 默认的密码字符串组合，用来将字节转换成 16 进制表示的字符,apache校验下载的文件的正确性用的就是默认的这个组合
     */
    private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

}
