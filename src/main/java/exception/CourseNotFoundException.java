package exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super (message);
    }
    public String getMessage(Long courseId){
        return "The Course with id: " + courseId + " could not be found.";
    }
}
