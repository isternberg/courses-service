package validation;

import model.AdvancedLecture;
import model.Lecture;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class LecturesValidator {



    public boolean validate(AdvancedLecture lecture) {
        List<Lecture> prerequisites = lecture.getPrerequisites();
        return doValidate(lecture, prerequisites);
    }


    private boolean doValidate(AdvancedLecture lecture, List<Lecture> prerequisites) {

        if (prerequisites.contains(lecture)) {
            return false;
        }

        if (!hasAdvancedPrerequisites(lecture)) {
            return true;
        }

        for (Lecture prerequisite : prerequisites) {
            if (prerequisite instanceof AdvancedLecture) {
                AdvancedLecture advancedCourse = (AdvancedLecture) prerequisite;
                if (!doValidate(lecture, advancedCourse.getPrerequisites())) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasAdvancedPrerequisites(AdvancedLecture lecture){
        return lecture.getPrerequisites().stream().filter(x-> x instanceof
                AdvancedLecture).count() > 0;
    }
}

