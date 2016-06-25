package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Course {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private BigDecimal price;


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

    public Course() {
    }
}
