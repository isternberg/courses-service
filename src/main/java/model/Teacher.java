package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Teacher {

    public Teacher() {
    }

    public Teacher(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Id
    @GeneratedValue
    @Column(name="TEACHER_ID")
    private Long id;

    private String firstname;

    private String lastname;

    @JsonManagedReference
    public Set<Course> getCourses() {
        return courses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    private Set<Course> courses;

}
