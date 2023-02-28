package service;

import dao.Books;

import java.util.List;

public interface BooksService {
    public List<Books> findAll();
    public Books findBooksByBid(int bid);
    public List<Books> findBooksByName(String name);
    public boolean addBooks(Books books);
}
