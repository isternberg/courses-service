package validation;

import model.AdvancedCourse;
import model.Course;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CoursesValidator {



    public boolean validate(AdvancedCourse course) {
        List<Course> prerequisites = course.getPrerequisites();
        return doValidate(course, prerequisites);
    }


    private boolean doValidate(AdvancedCourse course, List<Course> prerequisites) {

        if (prerequisites.contains(course)) {
            return false;
        }

        if (!hasAdvancedPrerequisites(course)) {
            return true;
        }

        for (Course prerequisite : prerequisites) {
            if (prerequisite instanceof AdvancedCourse) {
                AdvancedCourse advancedCourse = (AdvancedCourse) prerequisite;
                if (!doValidate(course, advancedCourse.getPrerequisites())) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasAdvancedPrerequisites(AdvancedCourse course){
        return course.getPrerequisites().stream().filter(x-> x instanceof
                AdvancedCourse).count() > 0;
    }
}

