package library.model;

import java.util.Map;

public interface SearchLibrary {

    void getAllBooks(Map<Integer, Book> bookList);

    void getBookWithAuthor(Map<Integer, Book> bookList);

    void getBookWithTitle(Map<Integer, Book> bookList);

    void getBookWithId(Map<Integer, Book> bookList);

    void getBooksWithCategory(Map<Integer, Book> bookList);

}
