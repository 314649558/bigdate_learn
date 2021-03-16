package utils;

/**
 * Created by Administrator on 2021/1/27.
 */
public class ThreadUtils {

    public static void sleep(long time){
        try {
            Thread.sleep(Math.abs(time));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
