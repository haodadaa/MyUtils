package com.hao.myutils;

import org.junit.Test;

public class BitOperationTest {

    @Test
    public void opTest() {
        boolean bool = false; // 取值范围 true,false
        short s = -123; // 取值范围 -32768~32767
        int i = 123; // 取值范围 -2147483648~2147483647
        long l = 123; // 取值范围 -9223372036854775808~9223372036854775807
        float f = 0.777f; // 取值范围 1.4E-45~3.4028235E38
        double d = 0.777777d; // 取值范围 4.9E-324~1.7976931348623157E308
        char c = 'c'; // 取值范围 \u0000~\uFFFF（unicode编码） 0～65535
        byte b = 123; // 取值范围 -128～127

        int so = s >> 7;
        System.out.println(s);
        System.out.println(so);
        int io = i << 30;
        System.out.println(i);
        System.out.println(io);
        long lo = l << 30;
        System.out.println(l);
        System.out.println(lo);
        int co = c << 30;
        System.out.println(c);
        System.out.println(co);
        int bo = b << 30;
        System.out.println(b);
        System.out.println(bo);
    }
}
