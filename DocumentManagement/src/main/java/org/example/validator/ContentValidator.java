package org.example.validator;

import org.example.exception.DocumentManagementException;

public class ContentValidator {
    public static void validateContent(String content) throws DocumentManagementException {
        if (content == null || content.isEmpty()) {
            throw new DocumentManagementException("Invalid content");
        }
    }
}