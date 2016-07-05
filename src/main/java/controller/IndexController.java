package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping("/test")
    public String index() throws RuntimeException{
        String foo = "null";
        if (foo.equals(null)){
            throw new RuntimeException("some error");
        }
        return "hello";
    }

    @ExceptionHandler
    public String handleException(HttpServletRequest req, Exception exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "error";
    }
}
