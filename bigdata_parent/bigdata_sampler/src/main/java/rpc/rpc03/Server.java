package rpc.rpc03;

import rpc.service.IUserServices;
import rpc.service.User;
import rpc.service.UserServiceImpl;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2020/12/11.
 */
public class Server {
    private static boolean running=true;
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket=new ServerSocket(9999);
        System.out.println("---------->: server start...");
        while (running) {
            Socket socket = serverSocket.accept();
            process(socket);
            socket.close();
        }
        serverSocket.close();
    }

    public static void process(Socket s) throws Exception{
        InputStream in=s.getInputStream();
        OutputStream out=s.getOutputStream();

        ObjectInputStream ois=new ObjectInputStream(in);
        DataOutputStream dos=new DataOutputStream(out);

        String methodName=ois.readUTF();
        Class[] paramTypes=(Class[])ois.readObject();
        Object[] args=(Object[]) ois.readObject();
        System.out.println("---调用方法名:"+methodName);
        for (int i=0;i<paramTypes.length;i++){
            System.out.println("参数类型:"+paramTypes[i]+ "  参数值:"+args[i].toString());
        }
        IUserServices services=new UserServiceImpl();
        Method method = services.getClass().getMethod(methodName, paramTypes);
        User user = (User) method.invoke(services, args);
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
    }
}
