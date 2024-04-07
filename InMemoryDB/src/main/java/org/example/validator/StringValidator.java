package org.example.validator;

public class StringValidator implements DataValidator<String>{
    private final int MAX_LENGTH = 20;
    private static StringValidator instance;
    @Override
    public boolean validate(String value) {
        return value.length() <= MAX_LENGTH;
    }

    public static StringValidator getInstance() {
        if(instance == null){
            instance = new StringValidator();
        }
        return instance;
    }
}
