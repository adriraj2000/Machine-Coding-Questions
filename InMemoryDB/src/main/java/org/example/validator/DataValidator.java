package org.example.validator;

public interface DataValidator<T> {
    public boolean validate(T value);
}
