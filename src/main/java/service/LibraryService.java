package service;

import model.Book;
import model.Branch;
import model.Patron;
import java.util.List;

/**
 * The library application service facade
 */
public interface LibraryService {

    /**
     * Obtain a list of patrons with library cards
     * @return List of Patron
     */
    public List<Patron> listPatrons();

    /**
     * Get a patron by id
     * @param id of a Patron
     * @return a Patron
     */
    public Patron getPatron(Long id);

    /**
     * Obtain a list of branches in the system
     * @return List of Branch
     */
    public List<Branch> listBranches();

    /**
     * Get a branch by id
     * @param id of a Branch 
     * @return a Branch
     */
    public Branch getBranch(Long id);

    /**
     * Get a book by id
     * @param id
     * @return a Book
     */
    public Book getBook(Long id);
    
    /**
     * Checkout a book to a patron
     * @param book the book to checkout
     * @param patron the patron who checks it out
     */
    public void checkoutBook(Book book, Patron patron);

    /**
     * Renew a book currently checked out to a patron
     * @param book the book to checkout
     */
    public void renewBook(Book book);

    /**
     * Return a book to a given branch
     * @param book the book to checkout
     * @param branch the branch to which it is returned
     */
    public void returnBook(Book book, Branch branch);
}
