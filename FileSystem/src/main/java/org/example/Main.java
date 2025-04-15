package org.example;

public class Main {
    public static void main(String[] args) {
        FileSystemManager fs = FileSystemManager.getInstance();
        fs.createPath("/a/b");
        fs.createPath("/a/b/sample1.txt");
        fs.createPath("/a/b/sample2.txt");
        fs.createPath("/a/b/c");
        fs.setFileContent("/a/b/sample1.txt", "Hello World");
        fs.setFileContent("/a/b/sample2.txt", "Second file");

        fs.display("/a/b");
    }
}