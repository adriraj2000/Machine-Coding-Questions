package org.example;

public class FileSystemManager {
    private static FileSystemManager instance;
    private FileSystemNode root;

    private FileSystemManager(){
        root = new Folder("/");
    }

    public boolean isValidPath(String path){
        return !path.isEmpty() && path.startsWith("/");
    }

    public boolean createPath(String path){
        if(!isValidPath(path))return false;
        String[] components = path.split("/");

        FileSystemNode current = root;
        for(int i = 1;i<components.length-1;i++){
            String component = components[i];
            if(!current.hasChild(component)){
                current.addChild(component, new Folder(component));
            }

            FileSystemNode child = current.getChild(component);
            if (child.isFile()) {
                return false;
            }

            current = child;
        }

        String lastComponent = components[components.length - 1];
        if (current.hasChild(lastComponent)) {
            return false;
        }

        FileSystemNode newNode;
        if (lastComponent.contains(".")) {
            newNode = new File(lastComponent);
        } else {
            newNode = new Folder(lastComponent);
        }
        current.addChild(lastComponent, newNode);
        return true;
    }

    public FileSystemNode getNode(String path){
        if (!isValidPath(path))
            return null;

        String[] components = path.split("/");
        FileSystemNode current = root;

        for (int i = 1; i < components.length; i++) {
            String component = components[i];
            if(!current.hasChild(component))return null;

            current = current.getChild(component);
        }

        return current;
    }

    public void display(String path) {
        FileSystemNode current = root;
        String[] components = path.split("/");

        for(int i = 1;i<components.length;i++){
            String component = components[i];
            if(!current.hasChild(component))return;

            current = current.getChild(component);
        }

        current.display(0);
    }

    // Set content for file
    public boolean setFileContent(String path, String content) {
        FileSystemNode node = getNode(path);
        if (node == null || !node.isFile())
            return false;
        File file = (File) node;
        file.setContent(content);
        return true;
    }

    // Get content from file
    public String getFileContent(String path) {
        FileSystemNode node = getNode(path);
        if (node == null || !node.isFile())
            return null;
        File file = (File) node;
        return file.getContent();
    }

    public static FileSystemManager getInstance() {
        if(instance == null){
            synchronized (FileSystemManager.class){
                if(instance == null){
                    instance = new FileSystemManager();
                }
            }
        }
        return instance;
    }
}
