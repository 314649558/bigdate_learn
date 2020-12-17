package rpc.rpc03;

import rpc.service.IUserServices;

/**
 * Created by Administrator on 2020/12/11.
 */
public class Client {

    public static void main(String[] args) {
        IUserServices stub = Stub.getStub();
        stub.findUserById(1);
    }

}
