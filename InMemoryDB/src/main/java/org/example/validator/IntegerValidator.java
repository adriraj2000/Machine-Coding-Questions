package org.example.validator;

public class IntegerValidator implements DataValidator<Integer>{
    private final int MIN_VALUE = -1024;
    private final int MAX_VALUE = 1024;
    private static IntegerValidator instance;
    @Override
    public boolean validate(Integer value) {
        return value >= MIN_VALUE && value <= MAX_VALUE;
    }

    public static IntegerValidator getInstance() {
        if(instance == null){
            instance = new IntegerValidator();
        }
        return instance;
    }
}
