import dao.*;
import service.*;
import service.impl.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static AdminService adminService=new AdminServiceImpl();
    public static BooksService booksService=new BooksServiceImpl();
    public static UserService userService=new UserServiceImpl();
    public static AdminAndUserMiddleService adminAndUserMiddleService=new AdminAndUserMiddleServiceImpl();
    public static BorrowService borrowService=new BorrowServiceImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String reg = "^[0-9]+(.[0-9]+)?$";
        boolean bl=true;
        while(bl){
            System.out.println("-----------Welcome to the library management system-----------");
            System.out.println("-------(Please enter what you want to do according to the serial number)-------");
            System.out.println("\t 1.Log in");
            System.out.println("\t 2.register");
            System.out.println("\t 3.Exit system");
            String choose_one=scanner.next();
            if(choose_one.matches(reg)){
                int choose_one_int = Integer.parseInt(choose_one);
                switch (choose_one_int){
                    case 1:
                        System.out.print("\t Please enter the user name：");
                        String username=scanner.next();
                        if(username.equals("")){
                            System.out.println("The user name cannot be empty！");
                        }else{
                            System.out.print("\t Please enter password：");
                            String password=scanner.next();
                            if(password.equals("")){
                                System.out.println("The password cannot be empty ！");
                            }else{
                                AdminAndUserMiddle andUserMiddle=new AdminAndUserMiddle(username,password);
                                AdminAndUserMiddle now_andUserMiddle = adminAndUserMiddleService.findUserNameAndPassword(andUserMiddle);
                                if(now_andUserMiddle!=null){
                                    System.out.println("-------Login success-------");
                                    if(now_andUserMiddle instanceof User){
                                        User users= (User) now_andUserMiddle;
                                        boolean bl_two=true;
                                        while(bl_two){
                                            System.out.println("-------Welcome to the book lending website-------");
                                            System.out.println("\t 1.Rental book");
                                            System.out.println("\t 2.Query book");
                                            System.out.println("\t 3.Query all borrowed books of the current user");
                                            System.out.println("\t 4.Exit the website");
                                            String choose_two=scanner.next();
                                            if(choose_two.matches(reg)){
                                                int choose_two_int = Integer.parseInt(choose_two);
                                                switch (choose_two_int){
                                                    case 1:
                                                        System.out.print("\t Please enter the id of the book you want to rent:");
                                                        String leas_bid=scanner.next();
                                                        if(leas_bid.matches(reg)){
                                                            Books booksByBid = booksService.findBooksByBid(Integer.parseInt(leas_bid));
                                                            if(booksByBid!=null){
                                                                Borrow borrow1=new Borrow(Integer.parseInt(leas_bid),users.getId());
                                                                borrowService.addBorrow(borrow1);
                                                                System.out.println("Successful book rental！");
                                                            }else{
                                                                System.out.println("The information of the book with this number is not queried！");
                                                            }
                                                        }else{
                                                            System.out.println("Please enter integer data, do not enter other types of data！");
                                                        }
                                                        break;
                                                    case 2:
                                                        System.out.print("\t Please enter the name of the book you want to query (can be fuzzy query):");
                                                        String bookName=scanner.next();
                                                        List<Books> booksByName = booksService.findBooksByName(bookName);
                                                        if(booksByName.size()>0){
                                                            System.out.println("-----The book information is as follows：-----");
                                                            for(Books books:booksByName){
                                                                System.out.println("\t "+books);
                                                            }
                                                        }else{
                                                            System.out.println("-----No and"+bookName+"--Relevant book information--");
                                                        }
                                                        break;
                                                    case 3:
                                                        List<Borrow> borrowByUid = borrowService.findBorrowByUid(users.getId());
                                                        if(borrowByUid.size()>0){
                                                            System.out.println("-----The relevant rental information is as follows：-----");
                                                            for(Borrow borrow:borrowByUid){
                                                                System.out.println(borrow);
                                                            }
                                                        }else{
                                                            System.out.println("----The current user does not rent books-----");
                                                        }
                                                        break;
                                                    case 4:
                                                        bl_two=false;
                                                        System.out.println("-----------Exit the website successfully-----------");
                                                        break;
                                                }
                                            }else{
                                                System.out.println("Please enter integer data, do not enter other types of data！");
                                            }
                                        }
                                    }else if(now_andUserMiddle instanceof Admin){
                                        Admin admin=(Admin)now_andUserMiddle;
                                        boolean bl_two=true;
                                        while(bl_two){
                                            System.out.println("-------Welcome to the background management website-------");
                                            System.out.println("\t 1.Query information about all books");
                                            System.out.println("\t 2.Exit management website");
                                            String choose_two=scanner.next();
                                            if(choose_two.matches(reg)){
                                                int choose_two_int = Integer.parseInt(choose_two);
                                                switch (choose_two_int){
                                                    case 1:
                                                        System.out.println("----All book information is：----");
                                                        List<Books> all = booksService.findAll();
                                                        for(Books books:all){
                                                            System.out.println("\t"+books);
                                                        }
                                                        break;
                                                    case 2:
                                                        bl_two=false;
                                                        System.out.println("-----------Successfully exit the management website. Procedure-----------");
                                                        break;
                                                }
                                            }else{
                                                System.out.println("Please enter integer data, do not enter other types of data！");
                                            }
                                        }
                                    }
                                }else{
                                    System.out.println("The user name or password is incorrect！");
                                }
                            }
                        }
                        break;
                    case 2:
                        System.out.print("\t Please enter the user name：");
                        String username2=scanner.next();
                        System.out.print("\t Please enter password：");
                        String password=scanner.next();
                        User user=new User(username2,password);
                        boolean addUser = userService.addUser(user);
                        if(addUser){
                            System.out.println("Successful registration！");
                        }else{
                            System.out.println("Registration failure！");
                        }
                        break;
                    case 3:
                        bl=false;
                        System.out.println("-----------Look forward to your next visit to the library management system-----------");
                        break;
                    default:
                        System.out.println("Please enter the correct serial number (1-3)");
                }
            }else{
                System.out.println("Please enter integer data, do not enter other types of data！");
            }
        }
    }

}