package validation;

import model.AdvancedCourse;
import model.Course;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CoursesValidatorTest {

    CourseValidator courseValidator;

    @Before
    public void setup(){
        courseValidator = new CourseValidator();
    }

    private static Course C(String name, double price) {
        return new Course(name, new BigDecimal(price));
    }

    private static AdvancedCourse A(String name, double price, Course... prerequisites) {
        AdvancedCourse course = new AdvancedCourse();
        course.setPrice(new BigDecimal(price));
        course.setTitle(name);
        course.setPrerequisites(Arrays.asList(prerequisites));
        return course;
    }
    @Test
    public void shouldReturnFalseIfDependDirectlyOnEachOther(){
        AdvancedCourse c1 = A("adv1", 42.0);
        AdvancedCourse c2 = A("adv2", 52.0);
        c1.setPrerequisites(Arrays.asList(c2));
        c2.setPrerequisites(Arrays.asList(c1));
        assertThat(courseValidator.mayAdd(c1), is(false));
        assertThat(courseValidator.mayAdd(c2), is(false));
    }

    @Test
    public void shouldReturnFalseIfContainsCyclicDependency(){
        Course c1 = C("foo", 23.23);
        AdvancedCourse c2 = A("adv1", 42.0);
        AdvancedCourse c3 = A("adv2", 41.0, c2);
        AdvancedCourse c4 = A("adv3", 40.0, c3);
        c2.setPrerequisites(Arrays.asList(c1, c4));
        assertThat(courseValidator.mayAdd(c2), is(false));
        assertThat(courseValidator.mayAdd(c3), is(false));
        assertThat(courseValidator.mayAdd(c3), is(false));
    }

    @Test
    public void shouldReturnTrueWhenPrerequisitesValid(){
        Course c1 = C("basic1", 23.23);
        AdvancedCourse c2 = A("adv1", 42.0,c1);
        AdvancedCourse c3 = A("adv2", 41.0, c2);
        assertThat(courseValidator.mayAdd(c2), is(true));
        assertThat(courseValidator.mayAdd(c3), is(true));
    }

    @Test
    public void shouldReturnTrueWhenHavingNoPrerequisites(){
        AdvancedCourse c3 = A("adv2", 41.0);
        assertThat(courseValidator.mayAdd(c3), is(true));
    }

}