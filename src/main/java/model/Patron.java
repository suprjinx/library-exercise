package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A user of the library
 */
public class Patron {
    
    private Long id = null;
    private String name = null;
    private String email = null;
    private List<Book> books = null;

    public Patron(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.books = new ArrayList<Book>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
