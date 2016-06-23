package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import repository.CourseRepository;

@Controller
public class CourseController {

    @Autowired
    CourseRepository repository;

    @RequestMapping(value = "/courses", method = RequestMethod.GET, produces= MediaType.TEXT_HTML_VALUE)
    public ModelAndView getCourses(){
        return new ModelAndView("courses").addObject("courses", repository.findAll());
    }
}
