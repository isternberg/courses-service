package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Course { 

    @Id @GeneratedValue
    private Long id;

    private String title;

    private BigDecimal price;

    @ManyToMany
    private List<Course> requirements; //TODO How to avoid A->B, B->C, C->A

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<Course> getRequirements() {
        return requirements;
    }

    public Course(String title, List<Course> requirements, BigDecimal price) {
        this.title = title;
        this.requirements = requirements;
        this.price = price;
    }

    public Course() {
    }
}
