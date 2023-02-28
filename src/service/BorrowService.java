package service;

import dao.Borrow;

import java.util.List;

public interface BorrowService {
    public boolean addBorrow(Borrow borrow);
    public List<Borrow> findBorrowByUid(int uid);
}
