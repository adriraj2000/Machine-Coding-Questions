package org.example.validator;

import java.util.List;

public class ValidatorFactory {
    private static ValidatorFactory instance;
    private StringValidator stringValidator;
    private IntegerValidator integerValidator;
    private ValidatorFactory(){
        stringValidator = StringValidator.getInstance();
        integerValidator = IntegerValidator.getInstance();
    }

    public boolean validate(List<Object> values){
        boolean isValid = true;
        for(Object val : values){
            if(val instanceof String){
                if(!stringValidator.validate((String) val)){
                    isValid = false;
                    break;
                }
            }
            else if(val instanceof Integer){
                if(!integerValidator.validate((Integer) val)){
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

    public static ValidatorFactory getInstance() {
        if(instance == null){
            instance = new ValidatorFactory();
        }
        return instance;
    }
}
