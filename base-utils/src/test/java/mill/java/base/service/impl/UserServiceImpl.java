package mill.java.base.service.impl;

import mill.java.base.service.UserService;

/**
 * Created by Chief-Inof09 on 2017/3/20.
 */
public class UserServiceImpl implements UserService {

    public String getName(int id) {
        System.out.println("------getName------");
        return "Tom";
    }

    public Integer getAge(int id) {
        System.out.println("------getAge------");
        return 10;
    }
}
