package suanfa;

import java.util.Random;

/**
 * 负载均衡之随机算法
 * @author yuanhailong
 * @date 2021/3/21.
 */
public class RadomDemo {

    /**
     * 简单随机
     * @return
     */
    public static String getServerIp(){
        Random random=new Random();
        int i = random.nextInt(ServerIps.SERVERLIST.size());
        return ServerIps.SERVERLIST.get(i);
    }


    public static String getWeightServerIp(){
        //计算所有权重和
        Integer totalWeight=0;
        for(Integer weight:ServerIps.WEIGHT_SERVERLIST.values()){
            totalWeight +=weight;
        }

        Random random=new Random();
        int pos=random.nextInt(totalWeight);
        for(String ip:ServerIps.WEIGHT_SERVERLIST.keySet()){
            int weight=ServerIps.WEIGHT_SERVERLIST.get(ip);

            if(pos<=weight){
                return ip;
            }
            pos=pos-weight;
        }

        return "";

    }


    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            System.out.println(getWeightServerIp());
        }
    }

}
