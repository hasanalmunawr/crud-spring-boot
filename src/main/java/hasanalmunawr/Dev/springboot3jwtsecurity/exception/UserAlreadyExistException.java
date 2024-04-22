package hasanalmunawr.Dev.springboot3jwtsecurity.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
