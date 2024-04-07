package org.example.model;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class Document {
    private String name;
    private String content;
    private String author;
    private Map<String,UserAccess> accessibleUsers;
    public Document(String name, String content,String author){
        this.name = name;
        this.content = content;
        this.author = author;
        accessibleUsers = new HashMap<>();
    }
}
