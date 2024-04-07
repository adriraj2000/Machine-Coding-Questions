package org.example;

import org.example.exception.AccessManagementException;
import org.example.exception.DocumentManagementException;
import org.example.exception.UserManagementException;
import org.example.model.Document;
import org.example.model.User;
import org.example.service.AccessManagement;
import org.example.service.DocumentManagement;
import org.example.service.UserManagement;

import java.util.HashMap;
import java.util.Map;

public class DocumentManagementSystem {
    private static DocumentManagementSystem instance;
    private Map<String, Document> documents;
    private UserManagement userManagement;
    private AccessManagement accessManagement;
    private DocumentManagement documentManagement;

    private DocumentManagementSystem(UserManagement userManagement, AccessManagement accessManagement, DocumentManagement documentManagement) {
        this.documents = new HashMap<>();
        this.userManagement = userManagement;
        this.accessManagement = accessManagement;
        this.documentManagement = documentManagement;
    }

    // Singleton getInstance method
    public static DocumentManagementSystem getInstance(UserManagement userManagement,
                                                       AccessManagement accessManagement, DocumentManagement documentManagement) {
        if (instance == null) {
            instance = new DocumentManagementSystem(userManagement, accessManagement,documentManagement);
        }
        return instance;
    }

    public void createUser(String userId, String password, String name) throws UserManagementException {
        userManagement.registerUser(userId, password, name);
    }

    public User loginUser(String userId, String password) throws UserManagementException {
        return userManagement.loginUser(userId, password);
    }

    public Document getDocument(String documentId) throws DocumentManagementException {
        return documentManagement.getDocument(documentId);
    }

    public void createDocument(String documentId, User author) throws DocumentManagementException {
        documentManagement.createDocument(documentId, author);
    }

    public void updateDocument(String documentId, User user, String content) throws DocumentManagementException, AccessManagementException {
        if(accessManagement.hasAccess(user, documentManagement.getDocument(documentId)))
            documentManagement.updateDocument(documentId, user, content);
    }

    public void revertToVersion(String documentId, User user, int versionIndex) throws DocumentManagementException, AccessManagementException {
        if(accessManagement.hasAccess(user, documentManagement.getDocument(documentId)))
            documentManagement.revertToVersion(documentId, versionIndex);
    }
}
