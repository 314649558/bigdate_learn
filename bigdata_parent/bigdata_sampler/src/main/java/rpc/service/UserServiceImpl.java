package rpc.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/12/11.
 */
public class UserServiceImpl implements IUserServices {

    private static List<User> userLst=new ArrayList<>();

    static {
        userLst.add(new User(1,"hailong"));
        userLst.add(new User(2,"yufei"));
        userLst.add(new User(3,"yucheng"));
        userLst.add(new User(4,"liuyao"));
    }

    @Override
    public User findUserById(int id) {
        for(User user:userLst){
            if(user.getId()==id){
                return user;
            }
        }
        return new User(id,"unknow");
    }
}
