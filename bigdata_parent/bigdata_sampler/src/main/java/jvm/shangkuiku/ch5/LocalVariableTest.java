package jvm.shangkuiku.ch5;

import java.util.Date;

/**
 * @author yuanhailong
 * @date 2021/5/13.
 */
public class LocalVariableTest {

    private String local="局部变量表测试";


    public static void main(String[] args) {
        LocalVariableTest test=new LocalVariableTest();
        int count=100;
        System.out.println(count);
    }

    public static void testStatic(){
        LocalVariableTest test=new LocalVariableTest();
        Date date=new Date();
        int count=100;
        System.out.println(count);
    }

    public void test1(){
        int count=1;
        System.out.println(count);
    }
}
