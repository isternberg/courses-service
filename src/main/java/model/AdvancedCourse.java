package model;



import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class AdvancedCourse extends Course {
    public AdvancedCourse(String title, BigDecimal price, Teacher teacher, List<Course> prerequisites) {
        super(title, price, teacher);
        this.prerequisites = prerequisites;
    }

    @ManyToMany
    private List<Course> prerequisites;

    public AdvancedCourse() {

    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }
}
