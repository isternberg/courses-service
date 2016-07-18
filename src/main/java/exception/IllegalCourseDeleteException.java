package exception;

public class IllegalCourseDeleteException extends RuntimeException {
    public IllegalCourseDeleteException(String message) {
        super(message);
    }

    public IllegalCourseDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCourseDeleteException() {
    }
}
