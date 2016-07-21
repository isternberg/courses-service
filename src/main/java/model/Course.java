package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Course {

    @Id
    @GeneratedValue
    @Column(name="COURSE_ID")
    private Long id;

    @Getter @Setter private String title;

    @Getter @Setter private BigDecimal price;

    @ManyToOne()
    @JoinColumn(name="TEACHER_ID")
    @Setter private Teacher teacher;

    @JsonBackReference
    public Teacher getTeacher() {
        return teacher;
    }

    public Course(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public Course(String title, BigDecimal price, Teacher teacher) {
        this.title = title;
        this.price = price;
        this.teacher = teacher;
    }

    public Course() {
    }


}
