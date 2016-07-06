package validation;

import model.AdvancedCourse;
import model.Course;

import java.util.List;

public class CourseValidator {



    public boolean validate(AdvancedCourse course) {
        List<Course> prerequisites = course.getPrerequisites();
        doValidate(course, prerequisites);
        return false;
    }


    private boolean doValidate(AdvancedCourse course, List<Course> prerequisites) {

        if (prerequisites.contains(course)) {
            return false;
        }

        if (!hasAdvancedPrerequisites(course)) {
            return true;
        }

        for (Course prerequisite : prerequisites) {
            if (course instanceof AdvancedCourse) {
                AdvancedCourse advancedCourse = (AdvancedCourse) prerequisite;
                if (!doValidate(course, advancedCourse.getPrerequisites())) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasAdvancedPrerequisites(AdvancedCourse course){
        return course.getPrerequisites().stream().filter(x -> x.getClass()
                .isInstance(AdvancedCourse.class)).count() > 0;
    }
}
