package org.example.validator;

import org.example.exception.UserManagementException;

public class UserValidator {
    public static void validateUser(String userId, String password, String name) throws UserManagementException {
        if (userId == null || password == null || name == null || userId.isEmpty() || password.isEmpty() || name.isEmpty()) {
            throw new UserManagementException("Invalid user details");
        }
    }
}