package dao;

import java.sql.Date;

public class Borrow {
    private int bid;
    private int uid;
    private String borrowing_time;

    public Borrow() {
    }

    public Borrow(int bid, int uid) {
        this.bid = bid;
        this.uid = uid;
        Date date=new Date(System.currentTimeMillis());
        java.util.Date date2=new java.util.Date();
        this.borrowing_time = date+" "+date2.getHours()+":"+date2.getMinutes()+":"+date2.getSeconds();
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getBorrowing_time() {
        return borrowing_time;
    }

    public void setBorrowing_time(String borrowing_time) {
        this.borrowing_time = borrowing_time;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "bid=" + bid +
                ", uid=" + uid +
                ", borrowing_time=" + borrowing_time +
                '}';
    }
}
