package service.impl;

import dao.Books;
import dao.db;
import service.BooksService;

import java.awt.print.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksServiceImpl implements BooksService {
    File directory = new File("");
    public String path=directory.getAbsolutePath()+"\\db\\books.txt";
    public db db=new db();

    public List<Books> selectAll(){
        List list = db.select(path);
        List<Books> booksList=new ArrayList<Books>();
        for(int i=0;i<list.size();i++){
            String text = list.get(i).toString();
            String[] splits = text.split("/t");
            int bid=Integer.parseInt(splits[0].split("bid:")[1]);
            String name=splits[1].split("name:")[1];
            String introduction=splits[2].split("introduction:")[1];
            double price=new Double(splits[3].split("price:")[1]);
            Books book=new Books(name,introduction,price);
            book.setBid(bid);
            booksList.add(book);
        }
        return booksList;
    }

    @Override
    public List<Books> findAll() {
        return selectAll();
    }


    @Override
    public Books findBooksByBid(int bid) {
        List<Books> booksList = selectAll();
        Books now_books=null;
        for(Books books:booksList){
            if(books.getBid()==bid){
                now_books=books;
            }
        }
        return now_books;
    }

    @Override
    public List<Books> findBooksByName(String name) {
        List<Books> booksList = selectAll();
        List<Books> now_booksList = new ArrayList<Books>();
        for(Books books:booksList){
            if(books.getName().indexOf(name)!=-1){
                now_booksList.add(books);
            }
        }
        return now_booksList;
    }

    @Override
    public boolean addBooks(Books books) {
        Map<String,Object> map=new HashMap<String,Object>();
        List list = db.select(path);
        map.put("data","bid:"+list.size()+"/t"+"name:"+books.getName()+"/t"+"introduction:"+books.getIntroduction()+"/t"+"price:"+books.getPrice());
        return db.insert(map, path);
    }

}
