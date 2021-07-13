package suanfa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuanhailong
 * @date 2021/3/21.
 */
public class ServerIps {

    public static List<String> SERVERLIST=new ArrayList<>();
    public static Map<String,Integer> WEIGHT_SERVERLIST=new HashMap<>();



    static {
        SERVERLIST.add("192.168.101.1");
        SERVERLIST.add("192.168.101.2");
        SERVERLIST.add("192.168.101.3");

        WEIGHT_SERVERLIST.put("192.168.101.1",50);
        WEIGHT_SERVERLIST.put("192.168.101.2",30);
        WEIGHT_SERVERLIST.put("192.168.101.3",20);
    }




}
