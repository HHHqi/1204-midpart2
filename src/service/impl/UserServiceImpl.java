package service.impl;

import dao.User;
import dao.db;
import service.UserService;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    File directory = new File("");
    public String path=directory.getAbsolutePath()+"\\db\\user.txt";
    public dao.db db=new db();

    @Override
    public boolean addUser(User user) {
        Map<String,Object> map=new HashMap<String,Object>();
        List list = db.select(path);
        System.out.println(user.getUsername());
        map.put("data","id:"+list.size()+"/t"+"username:"+user.getUsername()+"/t"+"password:"+user.getPassword()+"/t"+"role:"+user.isRole());
        return db.insert(map, path);
    }
}
