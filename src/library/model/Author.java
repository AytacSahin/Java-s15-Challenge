package library.model;

import library.enums.Role;

public class Author extends Person {
    private static int nextId = 1;

    public Author(String name) {
        super(nextId, name);
        nextId++;
    }

    public int getId() {
        return super.getId();
    }

    @Override
    public Role whoYouAre() {
        return Role.AUTHOR;
    }

}
