package library.model;

import library.enums.Role;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class LibraryApp {
    private String libraryName;
    private Librarian librarian;
    private ArrayList<User> userList;
    private User loginUser;
    private Map<Integer, Book> bookList;
    Scanner scan = new Scanner(System.in);

    public LibraryApp() {
        this.libraryName = "Great Istanbul Library";
        this.librarian = new Librarian(1, "admin");
        this.userList = new ArrayList<>();
    }

    public void setBookList(Map<Integer, Book> bookList) {
        this.bookList = bookList;
    }

    private void initialLibraryInstallation() {
        if (this.bookList == null) {
            setBookList(this.librarian.putTheInitialBooks());
        }
    }

    public void run() {
        initialLibraryInstallation();
        System.out.println("🟢 Welcome to " + this.libraryName + " 🟢");
        System.out.println("Please Enter Your Username:");
        String userName = scan.nextLine();
        while (userName.length() < 3) {
            System.out.println("Please enter a name with at least 3 characters: ");
            userName = scan.nextLine();
        }
        if ((userName.equals("admin") && (librarian.whoYouAre() == Role.ADMIN))) {
            System.out.println("Admin, please insert your password:");
            int password = scan.nextInt();
            if (password == 1234) startLibrarian();
            else {
                System.out.println("Wrong password..");
                startUser();
            }
        }
        if (!userName.equals("admin") && this.userList == null || containsUserWithName(userName) == null) {
            loginUser = new User(userName, new ArrayList<>(), 25.0);
            this.userList.add(loginUser);
            System.out.println("Dear " + loginUser.getName() + ", welcome to '" + this.libraryName + "' user system.");
            startUser();
        } else if (!userName.equals("admin") && containsUserWithName(userName) != null) {
            loginUser = containsUserWithName(userName);
            System.out.println("Dear " + loginUser.getName() + ", welcome back to '" + this.libraryName + "' user system.");
            startUser();
        }
    }

    public void startUser() {
        loginUser.startText();
        int choice001 = scan.nextInt();
        while (1 > choice001 || choice001 > 10) {
            System.out.println("Please enter a valid choice: ");
            choice001 = scan.nextInt();
        }
        switch (choice001) {
            case 1:
                loginUser.getAllBooks(bookList);
                startUser();
                break;
            case 2:
                loginUser.getBookWithId(bookList);
                startUser();
                break;
            case 3:
                loginUser.getBookWithTitle(bookList);
                startUser();
                break;
            case 4:
                loginUser.getBookWithAuthor(bookList);
                startUser();
                break;
            case 5:
                loginUser.getBooksWithCategory(bookList);
                startUser();
                break;
            case 6:
                System.out.println("Your Balance: " + loginUser.getBalance());
                startUser();
                break;
            case 7:
                System.out.println("The books you borrowed: " + loginUser.getBorrowedBooks());
                startUser();
                break;
            case 8:
                int addBookId = loginUser.addBorrowedBook(bookList);
                librarian.addBorrowedBook(addBookId, loginUser, bookList);
                startUser();
                break;
            case 9:
                int returnBookId = loginUser.returnBorrowedBook(bookList, loginUser);
                librarian.returnBorrowedBook(returnBookId, loginUser, bookList);
                startUser();
                break;
            case 10:
                System.out.println("🔴 We hope you come again...🔴\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                run();
                break;
            default:
                System.out.println("Invalid choice. Please try again. Sorry...");
                startUser();
                break;
        }
    }

    public void startLibrarian() {
        librarian.startText();
        int choice002 = scan.nextInt();
        while (1 > choice002 || choice002 > 5) {
            System.out.println("Please insert a valid value: ");
            choice002 = scan.nextInt();
        }
        switch (choice002) {
            case 1:
                librarian.addNewBook(bookList);
                System.out.println(bookList);
                startLibrarian();
                break;
            case 2:
                librarian.updateBook(bookList);
                startLibrarian();
                break;
            case 3:
                librarian.removeBook(bookList);
                startLibrarian();
                break;
            case 4:
                librarian.getAllUserInfo(userList);
                startLibrarian();
                break;
            case 5:
                System.out.println("🔴 We hope you come again...🔴\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                run();
            default:
                System.out.println("Invalid choice. Please try again. Sorry...");
                startLibrarian();
                break;
        }
    }

    private User containsUserWithName(String username) {
        for (User user : userList) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }
}



