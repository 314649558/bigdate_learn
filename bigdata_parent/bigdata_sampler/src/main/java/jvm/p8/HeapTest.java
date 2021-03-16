package jvm.p8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2021/3/9.
 *
 * 利用jvisualvm观察对象在虚拟机每个区域的分配和内存分配
 *  java -Xms3G -Xmx3G -Xmn2G -Xss1M -XX:MetaspaceSize=512M -XX:MaxMetaspaceSize=512M -jar run.jar
 */
public class HeapTest {

    byte[] a=new byte[1024*100]; //100KB

    public static void main(String[] args) throws Exception {
        List<HeapTest> list=new ArrayList<>();

        for (;;){
            list.add(new HeapTest());
            Thread.sleep(10);
        }

    }

}
