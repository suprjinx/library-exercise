package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A library branch
 */
public class Branch {
    
    private Long id = null;
    private String name = null;
    private String address = null;
    private List<Book> books = null;

    public Branch(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
