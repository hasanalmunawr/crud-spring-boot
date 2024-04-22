package hasanalmunawr.Dev.springboot3jwtsecurity.exception;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String message) {
        super(message);
    }
}
