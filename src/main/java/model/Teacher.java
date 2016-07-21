package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
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

    @Getter @Setter private String firstname;

    @Getter @Setter private String lastname;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "teacher")
    @Setter private Set<Course> courses;

    @JsonManagedReference
    public Set<Course> getCourses() {
        return courses;
    }



}
