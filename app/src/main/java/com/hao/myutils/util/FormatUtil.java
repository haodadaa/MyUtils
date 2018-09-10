package com.hao.myutils.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

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
        // 初始化ByteBuffer，长度为arr数组的长度*4，因为一个int占4个字节
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
