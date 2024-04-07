package org.example.validator;

import org.example.exception.UserException;
import org.example.model.Document;

public class UserValidator {
    public static void validateUser(String userName) throws UserException {
        if(userName == null) throw new UserException("User does not exists");
    }
    public static void validateAuthor(String authorName, Document document) throws UserException {
        if(!authorName.equals(document.getAuthor())) throw new UserException("Author is not matching with document author");
    }
}
