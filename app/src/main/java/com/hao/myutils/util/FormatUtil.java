package com.hao.myutils.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;


/**
 *
 * Java基础数据的字节长度：
 *
 *  boolean：1 位，范围仅仅为0和1，其字面量为true和false
 *  byte：1 个字节
 *  short：2 个字节
 *  char：2 个字节
 *  int：4 个字节
 *  float：4 个字节
 *  long：8 个字节
 *  double：8 个字节
 *
 * */
public class FormatUtil {

    private FormatUtil() {

    }

    public static IntBuffer transformBuffer(int[] arr) {
        IntBuffer mBuffer;
        // 初始化ByteBuffer，长度为arr数组的长度*4，因为一个int占4个字节
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(arr.length * 4);
        // 数组排列用nativeOrder
        byteBuffer.order(ByteOrder.nativeOrder());
        mBuffer = byteBuffer.asIntBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }

    public static FloatBuffer transformBuffer(float[] arr) {
        FloatBuffer mBuffer;
        // 初始化ByteBuffer，长度为arr数组的长度*4，因为一个float占4个字节
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(arr.length * 4);
        // 数组排列用nativeOrder
        byteBuffer.order(ByteOrder.nativeOrder());
        mBuffer = byteBuffer.asFloatBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }

    public static ShortBuffer transformBuffer(short[] arr) {
        ShortBuffer mBuffer;
        // 初始化ByteBuffer，长度为arr数组的长度*2，因为一个short占2个字节
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(arr.length * 2);
        // 数组排列用nativeOrder
        byteBuffer.order(ByteOrder.nativeOrder());
        mBuffer = byteBuffer.asShortBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }
}
