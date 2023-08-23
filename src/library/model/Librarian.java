package library.model;

import library.enums.Category;
import library.enums.Role;

import java.util.*;

public class Librarian extends Person {
    private Map<Integer, Author> authorsList;
    Scanner scan = new Scanner(System.in);

    public Librarian(int id, String name) {
        super(id, name);
    }

    public Map<Integer, Book> putTheInitialBooks() {

        Author author1 = new Author("John Doe");
        Author author2 = new Author("Jane Doe");
        Author author3 = new Author("Baby Doe");
        authorsList = new HashMap<>();
        authorsList.put(author1.getId(), author1);
        authorsList.put(author2.getId(), author2);
        authorsList.put(author3.getId(), author3);

        Book[] books = {
                new Book("What is Death?", author1, Category.HISTORY, true),
                new Book("Madonna's Life", author2, Category.BIOGRAPHY, true),
                new Book("Take me Out", author1, Category.NON_FICTION, true),
                new Book("Ordinary People", author1, Category.MYSTERY, true),
                new Book("Myth", author3, Category.TECHNICAL, true),
                new Book("Construction me Engineering", author3, Category.ROMANCE, true),
                new Book("Ottoman Empire", author3, Category.HISTORY, true),
                new Book("Go on!", author2, Category.HORROR, true),
                new Book("Soil Mechanics", author1, Category.TECHNICAL, true),
                new Book("Afraid Of", author2, Category.HORROR, true)
        };

        Map<Integer, Book> initialBooksMap = new HashMap<>();

        for (Book book : books) {
            initialBooksMap.put(book.getId(), book);
        }
        return initialBooksMap;
    }

    protected void addNewBook(Map<Integer, Book> bookList) {
        try {
            System.out.println("Title of Book:");
            String nTitle = scan.nextLine();

            System.out.println("Author Name:");
            String nAuthorName = scan.nextLine();

            Category selectedCategory = null;

            boolean flag = true;
            while (flag) {
                System.out.println("Category of Book: ");
                String userInput = scan.nextLine();
                for (Category category : Category.values()) {
                    if (category.name().contains(userInput)) {
                        selectedCategory = category;
                        flag = false;
                        break;
                    }
                }
                if (selectedCategory != null) {
                    System.out.println("Selected category: " + selectedCategory);
                } else {
                    System.out.println("Invalid category. Please try again:");
                }
            }

            boolean authorExists = false;

            for (Map.Entry<Integer, Author> authorEntry : authorsList.entrySet()) {
                if (authorEntry.getValue().getName().equals(nAuthorName)) {
                    authorExists = true;
                    System.out.println("This author has already been added.");

                    Book addBook = new Book(nTitle, authorEntry.getValue(), selectedCategory, true);
                    bookList.put(addBook.getId(), addBook);
                    System.out.println("The new book successfully added..");
                    break;
                }
            }

            if (!authorExists) {
                Author newAuthor = new Author(nAuthorName);
                authorsList.put(newAuthor.getId(), newAuthor);
                Book addBook = new Book(nTitle, newAuthor, selectedCategory, true);
                bookList.put(addBook.getId(), addBook);
                System.out.println("The new book successfully added..");
            }

        } catch (
                Exception ex) {
            System.out.println("Error!: " + ex.getMessage());
        }

    }

    protected void updateBook(Map<Integer, Book> bookList) {
        try {
            boolean isExist = false;
            while (!isExist) {
                System.out.println("Please Insert an Id:");
                int id = scan.nextInt();
                Book findBook = bookList.get(id);

                if (findBook == null) {
                    System.out.println("The book was not found. It may not be a valid id.");
                } else if (findBook.getId() == id) {
                    isExist = true;
                    System.out.println("****** Book To Be Updated: ******\n" + findBook);
                    scan.nextLine();
                    System.out.println("New Title of Book: " + "(id: " + findBook.getId() + ")");
                    String nTitle = scan.nextLine();

                    System.out.println("New Author Name of Book: " + "(id: " + findBook.getId() + ")");
                    String nAuthorName = scan.nextLine();

                    Category selectedCategory = null;

                    boolean flag = true;
                    while (flag) {
                        System.out.println("New Category of Book: " + "(id: " + findBook.getId() + ")");
                        String userInput = scan.nextLine();
                        for (Category category : Category.values()) {
                            if (category.name().contains(userInput)) {
                                selectedCategory = category;
                                flag = false;
                                break;
                            }
                        }
                        if (selectedCategory != null) {
                            System.out.println("Selected category: " + selectedCategory);
                        } else {
                            System.out.println("Invalid category. Please try again:");
                        }
                    }
                    boolean authorExists = false;
                    for (Map.Entry<Integer, Author> authorEntry : authorsList.entrySet()) {
                        if (authorEntry.getValue().getName().equals(nAuthorName)) {
                            authorExists = true;
                            findBook.setTitle(nTitle);
                            findBook.setAuthor(authorEntry.getValue());
                            findBook.setCategory(selectedCategory);
                            System.out.println("Updated successfully.");
                            break;
                        }
                    }
                    if (!authorExists) {
                        Author newAuthor = new Author(nAuthorName);
                        authorsList.put(newAuthor.getId(), newAuthor);
                        findBook.setTitle(nTitle);
                        findBook.setAuthor(newAuthor);
                        findBook.setCategory(selectedCategory);
                        System.out.println("Updated successfully.");
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Error!: " + ex.getMessage());
        }
    }

    protected void removeBook(Map<Integer, Book> bookList) {
        try {
            boolean isExist = false;
            while (!isExist) {
                System.out.println("Please Insert an Id:");
                int id = scan.nextInt();
                Book findBook = bookList.get(id);
                if (findBook == null) {
                    System.out.println("The book was not found. It may not be a valid id.");
                } else if (findBook.getId() == id) {
                    isExist = true;
                    System.out.println(findBook + "\n = The book is removed from library...");
                    bookList.remove(id);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error!: " + ex.getMessage());
        }
    }

    public void addBorrowedBook(int bookId, User loginUser, Map<Integer, Book> bookList) {
        if (bookId != 0) {
            if (loginUser.getBorrowedBooks().size() == 5) {
                System.out.println("You can not borrowed more than 5 books..");
            }
            if (loginUser.getBalance() < 0) {
                System.out.println("Your balance must be at least 5.");
            }
            if (!bookList.get(bookId).isAvailable()) {
                System.out.println("The book you want to borrow is not available.");
            }
            if (loginUser.getBorrowedBooks().size() < 6 &&
                    loginUser.getBalance() >= 5 &&
                    bookList.get(bookId).isAvailable()) {
                List<Book> allBooks = loginUser.getBorrowedBooks();
                allBooks.add(bookList.get(bookId));
                loginUser.setBorrowedBooks(allBooks);
                bookList.get(bookId).setAvailable(false);
                loginUser.setBalance(loginUser.getBalance() - 5);
                System.out.println("Dear, " + loginUser.getName() + " the book has been successfully given to you.");
            }
        } else {
            System.out.println("Book id is invalid...");
        }
    }

    public void returnBorrowedBook(int bookId, User loginUser, Map<Integer, Book> bookList) {
        if (bookId != 0) {
            List<Book> newUserBooks = loginUser.getBorrowedBooks();
            newUserBooks.remove(bookList.get(bookId));
            loginUser.setBorrowedBooks(newUserBooks);
            bookList.get(bookId).setAvailable(true);
            loginUser.setBalance(loginUser.getBalance() + 5);
            System.out.println("Dear, " + loginUser.getName() + ", we got the book back from you.");
        } else {
            System.out.println("Book id is invalid...");
        }
    }

    protected void startText() {
        System.out.println("***************** Library Admin Panel *****************");
        System.out.println("[-] ADMIN PROCESSES:");
        System.out.println("\t[1] Add a New Book:");
        System.out.println("\t[2] Update Existing Book Informations (Note: enter a valid ID..)");
        System.out.println("\t[3] Delete Existing Book Informations (Note: enter a valid ID..)");
        System.out.println("[-] USERS:");
        System.out.println("\t[4] Get All Users Informations");
        System.out.println("[-] LOGOUT:");
        System.out.println("\t[5] Exit");
        System.out.println("...Choice:");
    }

    public void getAllUserInfo(ArrayList<User> userList) {
        System.out.println(userList);
    }

    @Override
    public Role whoYouAre() {
        return Role.ADMIN;
    }
}
