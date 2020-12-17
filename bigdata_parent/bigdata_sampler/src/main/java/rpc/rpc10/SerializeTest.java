package rpc.rpc10;

import rpc.service.User;

/**
 * Created by Administrator on 2020/12/11.
 */
public class SerializeTest {
    public static void main(String[] args) throws Exception{

        User user=new User(1,"hailong");
        System.out.println("JDK序列化后的长度:"+JDKSerializeUtils.serialize(user).length);
        System.out.println("Hessian序列化后的长度:"+HessianUtils.serialize(user).length);


    }
}
