package jvm.shangkuiku.ch5;

/**
 * @author yuanhailong
 * @date 2021/5/11.
 */
public class StackTest {
    public static void main(String[] args) {
        StackTest stackTest=new StackTest();
        stackTest.methodA();
    }


    public void methodA(){
        int i=10;
        int j=20;
        methodB();
    }

    public void methodB(){
        int i=30;
        int j=40;
    }

}
