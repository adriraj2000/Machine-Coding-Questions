package org.example;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class FileSystemNode {
    private String name;
    private Map<String, FileSystemNode> children;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public FileSystemNode(String name) {
        this.name = name;
        this.children = new HashMap<>();
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void addChild(String name, FileSystemNode child){
        children.put(name, child);
        this.modifiedAt = LocalDateTime.now();
    }

    public boolean hasChild(String name) {
        return this.children.containsKey(name);
    }

    public FileSystemNode getChild(String name){
        return this.children.get(name);
    }

    public Collection<FileSystemNode> getChildren() {
        return children.values();
    }

    public boolean removeChild(String name){
        if(children.containsKey(name)){
            children.remove(name);
            return true;
        }
        return false;
    }

    protected void updateModifiedTime() {
        this.modifiedAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    // Abstract methods for node operations
    public abstract boolean isFile();
    public abstract void display(int depth);
}
