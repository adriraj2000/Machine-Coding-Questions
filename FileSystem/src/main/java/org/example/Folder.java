package org.example;

import java.time.LocalDateTime;
import java.util.Map;

public class Folder extends FileSystemNode{
    public Folder(String name) {
        super(name);
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public void display(int depth) {
        String indent = " ".repeat(depth * 2);
        System.out.println(indent + "-> " + getName() + " (" + getChildren().size() + " items)");
        for(FileSystemNode node : getChildren()){
            node.display(depth+1);
        }
    }
}
