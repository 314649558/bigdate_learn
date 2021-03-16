package jvm.p8;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2021/3/13.
 */
public class UnSafeTest {

    private static Unsafe unsafe;

    private int i;

    private static int I_OFFSET;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe  = (Unsafe) field.get(null);
            long I_OFFSET = unsafe.objectFieldOffset(UnSafeTest.class.getDeclaredField("i"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }



}
