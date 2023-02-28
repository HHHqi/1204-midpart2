package service.impl;

import dao.Admin;
import dao.db;
import service.AdminService;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminServiceImpl implements AdminService {
    File directory = new File("");
    public String path=directory.getAbsolutePath()+"\\db\\user.txt";
    public dao.db db=new db();

    @Override
    public boolean addAdmin(Admin admin) {
        Map<String,Object> map=new HashMap<String,Object>();
        List list = db.select(path);
        map.put("data","id:"+list.size()+"/t"+"username:"+admin.getUsername()+"/t"+"password:"+admin.getPassword()+"/t"+"role:"+admin.isRole());
        return db.insert(map, path);
    }
}
