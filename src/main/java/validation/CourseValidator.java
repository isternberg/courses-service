package validation;
import model.AdvancedCourse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseValidator {

    List<AdvancedCourse> totalPrerequisites;

    public CourseValidator() {
        this.totalPrerequisites = new ArrayList<>();
    }

    public boolean validate(AdvancedCourse course) {
        List<AdvancedCourse> advancedPrerequisites = getAdvancedPrerequisites(course);
        advancedPrerequisites.forEach(this::validate);
        totalPrerequisites.addAll(advancedPrerequisites);

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
