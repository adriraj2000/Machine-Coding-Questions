package org.example.service;

import org.example.exception.DocumentException;
import org.example.exception.UserException;
import org.example.model.Document;
import org.example.model.User;
import org.example.model.UserAccess;
import org.example.validator.DocumentValidator;
import org.example.validator.UserValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DocumentService {
    private static DocumentService instance;
    private Map<String, Document> documentMap;
    private UserService userService;
    private DocumentService(UserService userService){
        documentMap = new HashMap<>();
        this.userService = userService;
    }

    public List<Document> getDocuments(){
        return documentMap.values().stream().toList();
    }

    public void createDocument(String authorName, String documentName, String content){
        try {
            User author = userService.getUser(authorName);
            UserValidator.validateUser(authorName);
            Document document = new Document(documentName, content, authorName);
            documentMap.put(documentName, document);
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteDocument(String documentName, String authorName){
        try {
            User author = userService.getUser(authorName);
            UserValidator.validateUser(authorName);
            Document document = documentMap.get(documentName);
            UserValidator.validateAuthor(authorName, document);
            documentMap.remove(documentName);
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
    }

    public Document readDocument(String userName, String documentName){
        try {
            UserValidator.validateUser(userName);
            Document document = documentMap.get(documentName);
            DocumentValidator.validateDocument(documentName);
            if(!document.getAccessibleUsers().containsKey(userName)){
                System.out.println("User does not have access");
                return null;
            }
            return document;
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (UserException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void editDocument(String userName, String documentName, String content){
        try {
            UserValidator.validateUser(userName);
            Document document = documentMap.get(documentName);
            DocumentValidator.validateDocument(documentName);
            if(!document.getAccessibleUsers().containsKey(userName) ||
                    document.getAccessibleUsers().get(userName).equals(UserAccess.READER)){
                System.out.println("User does not have access");
                return;
            }
            document.setContent(content);
            documentMap.put(documentName, document);
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
    }

    public void grantAccess(String authorName, String documentName, Map<String, UserAccess> accessibleUsers){
        try {
            User user = userService.getUser(authorName);
            UserValidator.validateUser(user.getName());
            Document document = documentMap.get(documentName);
            DocumentValidator.validateDocument(documentName);
            UserValidator.validateAuthor(authorName, document);
            for(String username : accessibleUsers.keySet()){
                UserAccess accessLevel = accessibleUsers.get(username);
                document.getAccessibleUsers().put(username, accessLevel);
            }
            documentMap.put(documentName, document);

        } catch (UserException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public static DocumentService getInstance(UserService userService) {
        if(instance == null){
            instance = new DocumentService(userService);
        }
        return instance;
    }
}
