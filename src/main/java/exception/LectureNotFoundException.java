package exception;

public class LectureNotFoundException extends RuntimeException {
    public LectureNotFoundException(String message) {
        super (message);
    }
    public String getMessage(Long lectureId){
        return "The Lecture with id: " + lectureId + " could not be found.";
    }
}
