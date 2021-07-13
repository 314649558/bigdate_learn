package jvm.shangkuiku.ch2;

import sun.misc.Launcher;

import java.net.URL;
import java.util.Arrays;

/**
 * @author yuanhailong
 * @date 2021/4/29.
 */
public class ClassLoaderTest1 {

    public static void main(String[] args) {
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("-------------getBootstrapClassPath-------------------");
        for(URL url:urLs){
            System.out.println(url.toExternalForm());
        }

        System.out.println("-------扩展类加载器-----------");
        String property = System.getProperty("java.ext.dirs");
        String[] split = property.split(";");
        Arrays.stream(split).forEach(s-> System.out.println(s));

    }

}
