package org.example.service;

import org.example.exception.AccessManagementException;
import org.example.model.Document;
import org.example.model.User;

public class AccessManagement  {

    public boolean hasAccess(User user, Document document) throws AccessManagementException {
        if (document != null && document.getAuthor().equals(user)) {
            return true;
        } else {
            throw new AccessManagementException("You are not authorized to access this document");
        }
    }
}