package controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Base API controller
 */
public class BaseController {

    /**
     * NPE handler, which most likely represents a not-found id
     * @param ex the exception
     * @param response the HttpServletResponse
     * @return Map to be rendered as JSON
     */
    @ExceptionHandler(NullPointerException.class)
    public Map<String, String> handleNullPointerException(Exception ex, HttpServletResponse response) {
        Map<String, String> m = new HashMap<String, String>();
        response.setStatus(404);
        m.put("error", "Resource not found");
        return m;
    }
    
    /**
     * General exception handler setting 500 status code
     * @param ex the exception
     * @param response the HttpServletResponse
     * @return Map to be rendered as JSON
     */
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleException(Exception ex, HttpServletResponse response) {
        Map<String, String> m = new HashMap<String, String>();
        response.setStatus(500);
        m.put("error", ex.toString());
        return m;
    }

}
