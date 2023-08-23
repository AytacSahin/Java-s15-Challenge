package library.model;

import library.enums.Category;

import java.util.Objects;

public class Book {
    private static int nextId = 1;
    private int id;
    private String title;
    private Author author;
    private Category category;
    private boolean available;

    public Book(String title, Author author, Category category, boolean available) {
        this.id = nextId;
        nextId++;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "[------------------------Book------------------------]" +
                "\nBook Id: " + getId() +
                "\nTitle: " + getTitle() +
                "\nAuthor: " + getAuthor() +
                "\nCategory: " + getCategory() +
                "\nAvailable: " + isAvailable();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
