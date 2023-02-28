package service.impl;

import dao.*;
import service.AdminAndUserMiddleService;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminAndUserMiddleServiceImpl implements AdminAndUserMiddleService {
    File directory = new File("");
    public String path=directory.getAbsolutePath()+"\\db\\user.txt";
    public dao.db db=new db();

    public List<AdminAndUserMiddle> selectAll(){
        List list = db.select(path);
        List<AdminAndUserMiddle> middleArrayList=new ArrayList<AdminAndUserMiddle>();
        for(int i=0;i<list.size();i++){
            String text = list.get(i).toString();
            String[] splits = text.split("/t");
            int id=Integer.parseInt(splits[0].split("id:")[1]);
            String username=splits[1].split("username:")[1];
            String password=splits[2].split("password:")[1];
            boolean role= Boolean.parseBoolean(splits[3].split("role:")[1]);
            if(role){
                AdminAndUserMiddle adminAndUserMiddle=new Admin(id,username,password,role);
                middleArrayList.add(adminAndUserMiddle);
            }else{
                AdminAndUserMiddle adminAndUserMiddle=new User(id,username,password,role);
                middleArrayList.add(adminAndUserMiddle);
            }
        }
        return middleArrayList;
    }

    @Override
    public AdminAndUserMiddle findUserNameAndPassword(AdminAndUserMiddle adminAndUserMiddle) {
        List<AdminAndUserMiddle> middleList = selectAll();
        AdminAndUserMiddle now_userList=null;
        for(AdminAndUserMiddle andUserMiddle:middleList){
            if(adminAndUserMiddle.getPassword().equals(andUserMiddle.getPassword()) && adminAndUserMiddle.getUsername().equals(andUserMiddle.getUsername())){
                if(andUserMiddle instanceof User){
                    User user= (User) andUserMiddle;
                    now_userList=user;
                }else if(andUserMiddle instanceof Admin){
                    Admin admin=(Admin)andUserMiddle;
                    now_userList=admin;
                }
            }
        }
        return now_userList;
    }
}
