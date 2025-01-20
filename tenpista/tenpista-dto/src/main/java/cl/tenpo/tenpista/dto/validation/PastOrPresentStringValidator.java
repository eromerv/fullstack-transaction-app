package cl.tenpo.tenpista.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PastOrPresentStringValidator implements ConstraintValidator<PastOrPresentString, String> {

    private String pattern;

    @Override
    public void initialize(PastOrPresentString annotation) {
        this.pattern = annotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true; // Let @NotNull handle null checks if needed
        }

        try {
            LocalDateTime dateTime = LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern));
            return !dateTime.isAfter(LocalDateTime.now());
        } catch (DateTimeParseException e) {
            return false; // Invalid format
        }
    }
}
