package org.example;

public class File extends FileSystemNode{
    private String content;
    private String extension;
    public File(String name) {
        super(name);
        this.extension = createExtension(name);
    }

    private String createExtension(String name) {
        int dotIndex = name.lastIndexOf('.');
        return (dotIndex > 0) ? name.substring(dotIndex + 1) : "";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        updateModifiedTime();
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public void display(int depth) {
        String indent = " ".repeat(depth * 2);
        System.out.println(indent + "-> " + getName());
    }
}
