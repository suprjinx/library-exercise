package controller;

import model.Book;
import model.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LibraryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RESTful Patron controller
 */
@Controller
@RequestMapping(value = "/patron")
public class PatronController extends BaseController {

    @Autowired
    LibraryService service;

    /**
     * List all patrons
     * @param model spring-mvc provided
     * @return List of patron attributes
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getView(Model model) {
        List<Patron> list = service.listPatrons();
        List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
        for (Patron b : list) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("id", b.getId());
            m.put("name", b.getName());
            m.put("email", b.getEmail());
            response.add(m);
        }
        return response;
    }

    /**
     * GETs the books checked out to a patron
     * @param id
     * @param model
     * @return List&lt;Book&gt;
     */
    @RequestMapping(value = "{id}/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> getView(@PathVariable Long id, Model model) {
        Patron patron = service.getPatron(id);
        return patron.getBooks();
    }

    /**
     * PUTs a book into the patron's at-home collection
     * @param id
     * @param bookId
     * @param model
     * @return Map&lt;String, Object&gt;
     */
    @RequestMapping(value = "{id}/books/{bookId}", method = RequestMethod.PUT)
    public @ResponseBody Map<String, Object> getView(@PathVariable Long id, @PathVariable Long bookId, Model model) {
        Book book = service.getBook(bookId);
        Patron patron = service.getPatron(id);
        service.checkoutBook(book, patron);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "ok");
        return map;
    }
}
