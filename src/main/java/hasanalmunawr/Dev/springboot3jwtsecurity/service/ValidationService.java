package hasanalmunawr.Dev.springboot3jwtsecurity.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@RequiredArgsConstructor
public class ValidationService {

    private final Validator validator;

    public void validate(Object request) {
        Set<ConstraintViolation<Object>> violations = validator.validate(request);
        if (violations.size() != 0) {
            throw new ConstraintViolationException(violations);
        }
    }
}
