package data;

import model.Course;

import java.util.List;

public class MockedDataBase implements CourseService {


//    private Map<Integer, Course> initDB(){
//        Map<Integer, Course> courses = new HashMap<>();
//        Course musicTheory = new Course("Music Theory", Collections.emptyList(), new BigDecimal(99.99));
//        Course impro101 = new Course("Impro 101", Arrays.asList(musicTheory), new BigDecimal(99.99));
//        courses.put(1, musicTheory);
//        courses.put(2, impro101);
//        return courses;
//    }


    @Override
    public List<Course> getCourses() {
        return null;
    }
}
