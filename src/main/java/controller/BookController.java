package controller;

import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.LibraryService;
import java.util.HashMap;
import java.util.Map;

/**
 * RESTful book controller
 */
@Controller
@RequestMapping(value = "/book")
public class BookController extends BaseController {

    @Autowired
    LibraryService service;

    /**
     * PUT to renew a book
     * @param id the book id
     * @param model spring-mvc provided
     * @return
     */
    @RequestMapping(value = "{id}/renew", method = RequestMethod.PUT)
    public @ResponseBody Map<String, Object> getView(@PathVariable Long id, Model model) {
        Book book = service.getBook(id);
        service.renewBook(book);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "ok");
        map.put("newDueDate", book.getDueDate());
        return map;
    }
}
