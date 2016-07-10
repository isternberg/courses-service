package validation;

public class CourseValidatorTest {
//
//    LecturesValidator courseValidator;
//
//    @Before
//    public void setup(){
//        courseValidator = new LecturesValidator();
//    }
//
//    private static Lecture C(String name, double price) {
//        return new Lecture(name, new BigDecimal(price));
//    }
//
//    private static AdvancedLecture A(String name, double price, Lecture... prerequisites) {
//        AdvancedLecture course = new AdvancedLecture();
//        course.setPrice(new BigDecimal(price));
//        course.setTitle(name);
//        course.setPrerequisites(Arrays.asList(prerequisites));
//        return course;
//    }
//    @Test
//    public void shouldReturnFalseIfContainsCyclicDependency(){
//        Lecture c1 = C("foo", 23.23);
//        AdvancedLecture c2 = A("adv1", 42.0);
//        AdvancedLecture c3 = A("adv2", 41.0, c2);
//        AdvancedLecture c4 = A("adv3", 40.0, c3);
//        c2.setPrerequisites(Arrays.asList(c1, c4));
//        assertThat(courseValidator.validate(c2), is(false));
//        assertThat(courseValidator.validate(c3), is(false));
//        assertThat(courseValidator.validate(c3), is(false));
//    }
//
//    @Test
//    public void shouldReturnFalseIfDependDirectlyOnEachOther(){
//        AdvancedLecture c1 = A("adv1", 42.0);
//        AdvancedLecture c2 = A("adv2", 52.0);
//        c1.setPrerequisites(Arrays.asList(c2));
//        c2.setPrerequisites(Arrays.asList(c1));
//        assertThat(courseValidator.validate(c1), is(false));
//        assertThat(courseValidator.validate(c2), is(false));
//    }
//
//    @Test
//    public void shouldReturnTrueWhenPrerequisitesValid(){
//        Lecture c1 = C("basic1", 23.23);
//        AdvancedLecture c2 = A("adv1", 42.0,c1);
//        AdvancedLecture c3 = A("adv2", 41.0, c2);
//        assertThat(courseValidator.validate(c2), is(true));
//        assertThat(courseValidator.validate(c3), is(true));
//    }

}