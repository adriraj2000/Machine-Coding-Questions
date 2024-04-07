package org.example.validator;

import org.example.exception.DocumentManagementException;

public class DocumentValidator {
    public static void validateDocument(String documentId) throws DocumentManagementException {
        if (documentId == null || documentId.isEmpty()) {
            throw new DocumentManagementException("Invalid document ID");
        }
    }
}