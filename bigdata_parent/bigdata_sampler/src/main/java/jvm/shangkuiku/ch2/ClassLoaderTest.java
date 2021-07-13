package jvm.shangkuiku.ch2;

/**
 * @author yuanhailong
 * @date 2021/4/29.
 */
public class ClassLoaderTest {

    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);

        ClassLoader bootClassLoader = extClassLoader.getParent();
        System.out.println(bootClassLoader);


        //用户自定义类来说是哪个类加载器：引用类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);


        //String 由引导类加载器进行加载，Java核心类库都是使用引导类加载器进行加载
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);
    }

}
