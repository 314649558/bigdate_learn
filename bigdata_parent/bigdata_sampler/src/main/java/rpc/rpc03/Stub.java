package rpc.rpc03;

import rpc.service.IUserServices;
import rpc.service.User;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Created by Administrator on 2020/12/11.
 */
public class Stub {
    public static IUserServices getStub(){
        InvocationHandler h=new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket s=new Socket("127.0.0.1",9999);
                ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
                Class[] paramTypes=method.getParameterTypes();
                oos.writeUTF(method.getName());
                oos.writeObject(paramTypes);
                oos.writeObject(args);
                oos.flush();

                DataInputStream dis=new DataInputStream(s.getInputStream());
                int id=dis.readInt();
                String name=dis.readUTF();
                User user=new User(id,name);
                System.out.println(user.toString());
                oos.close();
                s.close();
                return user;
            }
        };
        IUserServices userServices=(IUserServices)Proxy.newProxyInstance(IUserServices.class.getClassLoader(),
                new Class[]{IUserServices.class},
                h);
        return userServices;

    }
}
