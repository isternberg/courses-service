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

    public AdvancedCourse() {}

    @ManyToMany
    private List<Course> prerequisites;

    /*
     * This class uses getters and setters instead of LOMBOK's @Data annotatation,
     * since it is accessed by a class written in Kotlin,
     * which is currently not compatible with LOMBOK.
     */
    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }

}
