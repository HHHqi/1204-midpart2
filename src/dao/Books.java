package dao;

public class Books {
    private int bid;
    private String name;
    private String introduction;
    private double price;

    public Books() {
    }

    public Books(String name, String introduction, double price) {
        this.name = name;
        this.introduction = introduction;
        this.price = price;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Books{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", price=" + price +
                '}';
    }
}
