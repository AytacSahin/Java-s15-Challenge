package library.model;

import java.util.ArrayList;
import java.util.Map;

public interface Management {

    Map<Integer, Book> putTheInitialBooks();

    void addNewBook(Map<Integer, Book> bookList);

    void updateBook(Map<Integer, Book> bookList);

    void removeBook(Map<Integer, Book> bookList);

    void addBorrowedBook(int bookId, User loginUser, Map<Integer, Book> bookList);

     void returnBorrowedBook(int bookId, User loginUser, Map<Integer, Book> bookList);

     void startText();

     void getAllUserInfo(ArrayList<User> userList);


}
