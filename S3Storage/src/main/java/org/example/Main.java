package org.example;

import org.example.entities.User;
import org.example.iam.PermissionType;
import org.example.iam.PolicyManager;
import org.example.strategy.LocalStorageStrategy;
import org.example.strategy.StorageStrategy;

public class Main {
    public static void main(String[] args) {
        StorageStrategy storageStrategy = new LocalStorageStrategy();
        PolicyManager policyManager = new PolicyManager();
        policyManager.grantPermission("bucket1", "Alice", PermissionType.READ);
        policyManager.grantPermission("bucket1", "Bob", PermissionType.WRITE);
        policyManager.grantPermission("bucket1", "Alice", PermissionType.DELETE);
        BlobStorageFacade blobStorageFacade = BlobStorageFacade.getInstance(storageStrategy, policyManager);

        User u1 = new User("Alice");
        User u2 = new User("Bob");
        blobStorageFacade.addUser(u1);
        blobStorageFacade.addUser(u2);

        blobStorageFacade.addBucket("bucket1");

        blobStorageFacade.uploadObject("bucket1","Bob","file.txt","Hello".getBytes());

        byte[] downloaded = blobStorageFacade.downloadObject("bucket1","Alice","file.txt");
        System.out.println("Downloaded: " + new String(downloaded));

        blobStorageFacade.deleteObject("bucket1", "Alice", "file.txt");

        try {
            blobStorageFacade.downloadObject("bucket1", "Alice", "file.txt");
        } catch (Exception e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }
}