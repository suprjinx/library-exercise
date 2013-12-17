package controller;

import model.Book;
import model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.LibraryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RESTful Branch controller
 */
@Controller
@RequestMapping(value = "/branch")
public class BranchController extends BaseController {

    @Autowired
    LibraryService service;

    /**
     * List all branches
     *
     * @param model spring-mvc provided
     * @return List of branch attributes
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Map<String, Object>> getView(Model model) {
        List<Branch> list = service.listBranches();
        List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
        for (Branch b : list) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("id", b.getId());
            m.put("name", b.getName());
            m.put("address", b.getAddress());
            response.add(m);
        }
        return response;
    }

    /**
     * List books at a given branch
     *
     * @param id    the branch id
     * @param model spring-mvc provided
     * @return List of Book
     */
    @RequestMapping(value = "{id}/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> getView(@PathVariable Long id, Model model) {
        Branch branch = service.getBranch(id);
        return branch.getBooks();
    }

    /**
     * PUTs a book into the branch's holdings
     * @param id the branch id
     * @param bookId the book id
     * @param model spring-mvc provided
     * @return
     */                                  
    @RequestMapping(value = "{id}/books/{bookId}", method = RequestMethod.PUT)
    public @ResponseBody Map<String, Object> getView(@PathVariable Long id, @PathVariable Long bookId, Model model) {
        Book book = service.getBook(bookId);
        Branch branch = service.getBranch(id);
        service.returnBook(book, branch);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "ok");
        return map;
    }
}
