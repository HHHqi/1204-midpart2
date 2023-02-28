package service.impl;

import dao.Borrow;
import dao.db;
import service.BorrowService;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.db;
public class BorrowServiceImpl implements BorrowService {
    File directory = new File("");
    public String path=directory.getAbsolutePath()+"\\db\\borrow.txt";
    public db db=new db();

    public List<Borrow> selectAll(){
        List<Borrow> borrowList=new ArrayList<Borrow>();
        try{
            List list = db.select(path);
            for(int i=0;i<list.size();i++){
                String text = list.get(i).toString();
                String[] splits = text.split("/t");
                int bid=Integer.parseInt(splits[0].split("bid:")[1]);
                int uid=Integer.parseInt(splits[1].split("uid:")[1]);
                String borrowing_time=splits[2].split("borrowing_time:")[1];
                Borrow borrow=new Borrow(bid,uid);
                borrow.setBorrowing_time(borrowing_time);
                borrowList.add(borrow);
            }
        }catch (Exception e){
            return borrowList;
        }
        return borrowList;
    }

    @Override
    public boolean addBorrow(Borrow borrow) {
        Map<String,Object> map=new HashMap<String,Object>();
        List list = db.select(path);
        map.put("data","bid:"+borrow.getBid()+"/t"+"uid:"+borrow.getUid()+"/t"+"borrowing_time:"+borrow.getBorrowing_time());
        return db.insert(map, path);
    }

    @Override
    public List<Borrow> findBorrowByUid(int uid) {
        List<Borrow> borrowList=new ArrayList<Borrow>();
        for (Borrow borrow : selectAll()) {
            if(borrow.getUid()==uid){
                borrowList.add(borrow);
            }
        }
        return borrowList;
    }
}
