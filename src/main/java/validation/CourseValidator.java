package validation;

import model.AdvancedCourse;
import model.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseValidator {

    private List<AdvancedCourse> totalPrerequisites;
    private Course toValidate;

    public CourseValidator(Course toValidate) {
        this.totalPrerequisites = new ArrayList<>();
        this.toValidate = toValidate;
    }

    public boolean validate(AdvancedCourse course) {
        List<AdvancedCourse> advancedPrerequisites = getAdvancedPrerequisites(course);
        advancedPrerequisites.forEach(this::validate);
        totalPrerequisites.addAll(advancedPrerequisites);
        if (totalPrerequisites.contains(toValidate)){
            //TODO
        }

        return false;
    }

    private List<AdvancedCourse> getAdvancedPrerequisites(AdvancedCourse course) {
        return course.getPrerequisites().stream().
                filter(x -> x.getClass().isInstance(AdvancedCourse.class)).map(x ->(AdvancedCourse)x)
                .collect(Collectors.toList());
    }

    private boolean hasAdvancedPrerequisites(AdvancedCourse course){
        return course.getPrerequisites().stream().filter(x -> x.getClass()
                .isInstance(AdvancedCourse.class)).count() > 0;
    }
}
