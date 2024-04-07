package org.example.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Document {
    private String documentId;
    private User author;
    private List<String> versions;
    private int currentVersionIndex;

    // author -> creates a document -> accessLevel (Private, Public, Selected emails)

    public Document(String documentId, User author) {
        this.documentId = documentId;
        this.author = author;
        this.versions = new ArrayList<>();
        this.currentVersionIndex = -1;
    }

    public void addVersion(String content) {
        versions.add(content);
        currentVersionIndex = versions.size() - 1;
    }

    public String getLatestVersion() {
        if (currentVersionIndex != -1) {
            return versions.get(currentVersionIndex);
        }
        return null;
    }

    public void revertToVersion(int versionIndex) {
        if (versionIndex >= 0 && versionIndex < versions.size()) {
            currentVersionIndex = versionIndex;
        } else {
            System.out.println("Invalid version index");
        }
    }
}
