package rpc.service;

import rpc.service.User;

/**
 * Created by Administrator on 2020/12/11.
 */
public interface IUserServices {
    User findUserById(int id);
}
