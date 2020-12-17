package rpc.rpc04;

import rpc.service.IUserServices;
import rpc.service.UserServiceImpl;

/**
 * Created by Administrator on 2020/12/11.
 */
public class Client {

    public static void main(String[] args) {
        IUserServices stub = (IUserServices) Stub.getStub(IUserServices.class);
        stub.findUserById(1);
    }

}
