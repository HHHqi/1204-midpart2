package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class db {

    public List select(String path){
        List list=new ArrayList();
        try {
            BufferedReader br=new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null){
                list.add(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public boolean insert(Map<String,Object> params,String path){
        try {
            FileWriter write=new FileWriter(path,true);
            BufferedWriter bw=new BufferedWriter(write);
            bw.write(params.get("data").toString()+"\n");
            bw.close();
            write.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
