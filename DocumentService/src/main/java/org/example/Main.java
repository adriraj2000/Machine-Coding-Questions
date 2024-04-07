package org.example;

import org.example.model.UserAccess;
import org.example.service.DocumentService;
import org.example.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        DocumentService documentService = DocumentService.getInstance(userService);
        userService.addUser("u1");
        userService.addUser("u2");
        userService.addUser("u3");
        documentService.createDocument("u1","d1","Hello");
        Map<String, UserAccess> accessMap = new HashMap<>();
        accessMap.put("u2",UserAccess.READER);
        accessMap.put("u3",UserAccess.EDITOR);
        documentService.grantAccess("u1","d1",accessMap);
        documentService.editDocument("u2","d1","Thanks");
    }
}