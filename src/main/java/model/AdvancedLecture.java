package model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class AdvancedLecture extends Lecture {

    @ManyToMany
    private List<Lecture> prerequisites;

    public List<Lecture> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Lecture> prerequisites) {
        this.prerequisites = prerequisites;
    }
}
