package library.model;

import library.enums.Category;
import library.enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class User extends Person {
    private static int nextId = 1;
    private List<Book> borrowedBooks;
    Scanner scan = new Scanner(System.in);
    private double balance;

    public User(String name, List<Book> borrowedBooks, double balance) {
        super(nextId, name);
        nextId++;
        this.borrowedBooks = borrowedBooks;
        this.balance = balance;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void getAllBooks(Map<Integer, Book> bookList) {
        for (Map.Entry<Integer, Book> entry : bookList.entrySet()) {
            Book book = entry.getValue();
            System.out.println(book);
        }
    }

    public void getBookWithAuthor(Map<Integer, Book> bookList) {
        System.out.println("Please insert an Author Name:");
        String author = scan.nextLine();
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : bookList.values()) {
            if (book.getAuthor().getName().contains(author)) {
                filteredBooks.add(book);
            }
        }
        if (filteredBooks.size() != 0) {
            for (Book book : filteredBooks) {
                System.out.println(book);
            }
        } else {
            System.out.println("Book/s not found.");
        }
    }

    public void getBookWithTitle(Map<Integer, Book> bookList) {
        System.out.println("Please insert a Title:");
        String title = scan.nextLine();
        List<Book> filteredBooks = new ArrayList<>();
        for (Book book : bookList.values()) {
            if ((book.getTitle()).contains(title)) {
                filteredBooks.add(book);
            }
        }
        if (filteredBooks.size() != 0) {
            for (Book book : filteredBooks) {
                System.out.println(book);
            }
        } else {
            System.out.println("Book/s not found.");
        }
    }

    public void getBookWithId(Map<Integer, Book> bookList) {
        System.out.println("Please insert an Id:");
        int id = scan.nextInt();
        Book findBook = bookList.get(id);
        if (findBook == null) {
            System.out.println("Book/s not found.");
        } else if (findBook.getId() == id) {
            System.out.println(findBook);
        }
    }

    public void getBooksWithCategory(Map<Integer, Book> bookList) {
        System.out.println("Please insert a Category: ");
        String userInput = scan.nextLine();

        Category selectedCategory = null;

        boolean flag = true;
        while (flag) {
            for (Category category : Category.values()) {
                if (category.name().contains(userInput)) {
                    selectedCategory = category;
                    flag = false;
                    break;
                }
            }
            if (selectedCategory != null) {
                List<Book> filteredBooks = new ArrayList<>();
                for (Book book : bookList.values()) {
                    if ((book.getCategory()).equals(selectedCategory)) {
                        filteredBooks.add(book);
                    }
                }
                if (filteredBooks.size() != 0) {
                    System.out.println("Books in selected category: ");
                    for (Book book : filteredBooks) {
                        System.out.println(filteredBooks);
                        break;
                    }
                } else {
                    System.out.println("Book/s not found.");
                }
            } else {
                System.out.println("Invalid category. Please try again..");
            }
        }
    }

    public int addBorrowedBook(Map<Integer, Book> bookList) {
        System.out.println("Please insert a BookId you want to Borrow: ");
        int bookId = scan.nextInt();
        Book findBook = bookList.get(bookId);
        if (findBook == null) {
            System.out.println("Book/s not found.");
        } else if (findBook.getId() == bookId) {
            return bookId;
        }
        return findBook == null ? 0 : bookId;
    }

    public int returnBorrowedBook(Map<Integer, Book> bookList, User loginUser) {
        System.out.println("Please insert a BookId you want to Return: ");
        int bookId = scan.nextInt();
        Book findBook = bookList.get(bookId);
        if (!loginUser.getBorrowedBooks().contains(findBook)) {
            System.out.println("The book you were looking for was not found.");
            return 0;
        } else if (findBook.getId() == bookId) {
            return bookId;
        }
        return bookId;
    }

    public void startText() {
        System.out.println("***************** Library Automation System *****************");
        System.out.println("[-] SEARCH IN LIBRARY:");
        System.out.println("\t[1] Get List of All Books in Library:");
        System.out.println("\t[2] Search by Book id:");
        System.out.println("\t[3] Search by Book Title:");
        System.out.println("\t[4] Search by Author Name:");
        System.out.println("\t[5] Find Book With Category");
        System.out.println("[-] PERSONAL INFORMATION:");
        System.out.println("\t[6] Find Out Your Balance:");
        System.out.println("\t[7] Books Borrowed from the Library:");
        System.out.println("[-] BORROW A BOOK:");
        System.out.println("\t[8] Borrow a Book with Book Id:");
        System.out.println("\t[9] Return the borrowed book to the library with 'Book Id':");
        System.out.println("[-] LOGOUT:");
        System.out.println("\t[10] Exit");
        System.out.println("...Choice:");
    }

    @Override
    public Role whoYouAre() {
        return Role.USER;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[*****] User Informations:" +
                "\n[*] User Id: " + super.getId() +
                "\n[*] User Name: " + super.getName() +
                "\n[*] Borrowed Books:");

        for (Book book : this.getBorrowedBooks()) {
            result.append("\n").append(book);
        }

        return result.toString();
    }
}
