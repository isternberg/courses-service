package model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Lecture {

    @Id
    @GeneratedValue
    @Column(name="LECTURE_ID")
    private Long id;

    private String title;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private BigDecimal price;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEACHER_ID")
    private Teacher teacher;

    @JsonBackReference
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Lecture(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public Lecture(String title, BigDecimal price, Teacher teacher) {
        this.title = title;
        this.price = price;
        this.teacher = teacher;
    }

    public Lecture() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
