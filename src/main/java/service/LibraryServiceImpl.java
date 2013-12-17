package service;

import model.Book;
import model.Branch;
import model.Patron;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;
import org.joda.time.DateTime;
import javax.annotation.PostConstruct;

/**
 * LibraryService implementation using in-memory data store. This 
 * could be replaced with a different storage implementation.
 */
@Service
public class LibraryServiceImpl implements LibraryService {
    
    List<Branch> branches = null;
    List<Book> books = null;
    List<Patron> patrons = null;
    
    Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);

    @PostConstruct
    public void initializeData() {
        
        // populate the branches
        branches = new ArrayList<Branch>();
        branches.add(new Branch(1L, "Temescal Branch", "123 Telegraph Ave., Oakland, CA"));
        branches.add(new Branch(2L, "Rockridge Branch", "123 College Ave., Oakland, CA"));
        branches.add(new Branch(3L, "Berkeley Branch", "123 Dwight Way., Berkeley, CA"));
        
        // populate the books
        books = new ArrayList<Book>();
        books.add(new Book(1L, "Master and Commander 1", "Patrick O'Brien", "1234567788"));
        books.add(new Book(2L, "Master and Commander 2", "Patrick O'Brien", "1234567789"));
        books.add(new Book(3L, "Master and Commander 3", "Patrick O'Brien", "1234567790"));
        books.add(new Book(4L, "Manufacturing Consent", "Noam Chomsky", "1234567791"));
        books.add(new Book(5L, "Blood Meridian", "Cormac McCarthy", "1234567792"));
        books.add(new Book(6L, "Telegraph Ave", "Michael Chabon", "1234567793"));
        
        // populate the patrons
        patrons = new ArrayList<Patron>();
        patrons.add(new Patron(1L, "Geoff Wilson", "gwilson@superjinky.com"));
        patrons.add(new Patron(2L, "Nick Parson", "nparson@superjinky.com"));
        patrons.add(new Patron(3L, "Dash Parson", "djp@superjinky.com"));
        
        // allocate books to branches
        branches.get(0).getBooks().add(books.get(0));
        branches.get(0).getBooks().add(books.get(1));
        branches.get(1).getBooks().add(books.get(2));
        branches.get(1).getBooks().add(books.get(3));
        branches.get(2).getBooks().add(books.get(4));
        branches.get(2).getBooks().add(books.get(5));
    } 

    /**
     * Obtain a list of branches in the system
     * @return List of Branch
     */
    public List<Patron> listPatrons() {
        logger.debug("Listing patrons");
        return patrons;
    }

    /**
     * Get a patron by Id
     * @param id
     * @return a Patron
     */
    public Patron getPatron(Long id) {
        logger.debug("Finding patron by id: " + id);
        for (Patron p : patrons) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Obtain a list of branches in the system
     * @return List of Branch
     */
    public List<Branch> listBranches() {
        logger.debug("Listing branches");
        return branches;
    }

    /**
     * Get a branch by id
     * @param id of a Branch
     * @return a Branch
     */
    public Branch getBranch(Long id) {
        logger.debug("Finding branch by id: " + id);
        for (Branch b : branches) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    /**
     * Get a book by id
     * @param id
     * @return a Book
     */
    public Book getBook(Long id) {
        logger.debug("Finding book by id: " + id);
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    /**
     * Checkout a book to a patron
     * @param book the book to checkout
     * @param patron the patron who checks it out
     */
    public void checkoutBook(Book book, Patron patron) {
        logger.debug("Checking out book to patron: " + patron.getId());
        Date dueDate = new DateTime(book.getDueDate()).plusDays(14).toDate();
        book.setDueDate(dueDate);
        patron.getBooks().add(book);
        findBranchHoldingBook(book).getBooks().remove(book);
    }

    /**
     * Renew a book currently checked-out to a patron by extending the due date
     * @param book the book to checkout
     */
    public void renewBook(Book book) {
        logger.debug("Renewing book: " + book.getId());
        Date newDueDate = new DateTime(book.getDueDate()).plusDays(14).toDate();
        book.setDueDate(newDueDate);
    }

    /**
     * Return a book to a given branch
     * @param book   the book to checkout
     * @param branch the branch to which it is returned
     */
    public void returnBook(Book book, Branch branch) {
        logger.debug("Returning book: " + book.getId());
        book.setDueDate(null);
        findPatronHoldingBook(book).getBooks().remove(book);
        branch.getBooks().add(book);
    }

    /**
     * Helper to locate Branch where book is held
     * @param book
     * @return a Branch
     */
    private Branch findBranchHoldingBook(Book book) {
        for (Branch b : branches) {
            if (b.getBooks().contains(book)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Helper to locate Patron where book is held
     * @param book
     * @return a Patron
     */
    private Patron findPatronHoldingBook(Book book) {
        for (Patron p : patrons) {
            if (p.getBooks().contains(book)) {
                return p;
            }
        }
        return null;
    }
}
