package model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Course {

    @Id @GeneratedValue
    private Long id;

    private String title;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
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
