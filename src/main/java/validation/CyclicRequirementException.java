package validation;

public class CyclicRequirementException extends RuntimeException {

    public CyclicRequirementException() {

    }

    public CyclicRequirementException(String message) {
        super (message);
    }
}
