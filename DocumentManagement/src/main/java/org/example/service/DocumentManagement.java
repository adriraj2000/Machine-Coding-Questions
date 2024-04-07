package org.example.service;

import org.example.exception.DocumentManagementException;
import org.example.model.Document;
import org.example.model.User;
import org.example.validator.ContentValidator;
import org.example.validator.DocumentValidator;

import java.util.HashMap;
import java.util.Map;

public class DocumentManagement {
    private Map<String, Document> documents;

    public DocumentManagement() {
        this.documents = new HashMap<>();
    }

    public Document getDocument(String documentId) throws DocumentManagementException {
        DocumentValidator.validateDocument(documentId);
        Document document = documents.get(documentId);
        if (document != null) {
            return document;
        } else {
            throw new DocumentManagementException("Document not found");
        }
    }

    public void createDocument(String documentId, User author) throws DocumentManagementException {
        DocumentValidator.validateDocument(documentId);
        if (!documents.containsKey(documentId)) {
            Document newDocument = new Document(documentId, author);
            documents.put(documentId, newDocument);
            System.out.println("Document created successfully");
        } else {
            throw new DocumentManagementException("Document ID already exists");
        }
    }

    // t1 version1 -> update -> version2 ,t2 are trying to db version
    // concurrent read -> exclusive lock, concurrent lock ->

    public void updateDocument(String documentId, User user, String content) throws DocumentManagementException {
        DocumentValidator.validateDocument(documentId);
        ContentValidator.validateContent(content);
        Document document = documents.get(documentId);
        if (document != null) {
            document.addVersion(content);
            System.out.println("Document updated successfully");
        } else {
            throw new DocumentManagementException("Document not found");
        }
    }

    public void revertToVersion(String documentId, int versionIndex) throws DocumentManagementException {
        DocumentValidator.validateDocument(documentId);
        Document document = documents.get(documentId);
        if (document != null) {
            document.revertToVersion(versionIndex);
            System.out.println("Document reverted to version " + versionIndex);
        } else {
            throw new DocumentManagementException("Document not found");
        }
    }
}