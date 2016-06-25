package model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class AdvancedCourse extends Course {

    @ManyToMany
    private List<Course> prerequisites;

    public List<Course> getPrerequisites() {
        return prerequisites;
    }


}
