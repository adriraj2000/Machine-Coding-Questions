package org.example.validator;

import org.example.exception.DocumentException;

public class DocumentValidator {
    public static void validateDocument(String documentName) throws DocumentException {
        if(documentName == null) throw new DocumentException("Document does not exists");
    }
}
