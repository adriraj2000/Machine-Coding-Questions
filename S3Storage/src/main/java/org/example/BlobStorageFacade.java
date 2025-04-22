package org.example;

import org.example.entities.User;
import org.example.iam.PermissionType;
import org.example.iam.PolicyManager;
import org.example.strategy.StorageStrategy;

import java.util.HashMap;
import java.util.Map;

public class BlobStorageFacade {
    StorageStrategy storageStrategy;
    PolicyManager policyManager;
    Map<String, User> users;
    private static BlobStorageFacade instance;

    private BlobStorageFacade(StorageStrategy storageStrategy, PolicyManager policyManager){
        this.storageStrategy = storageStrategy;
        this.policyManager = policyManager;
        users = new HashMap<>();
    }

    public void addUser(User user){
        users.put(user.getId(), user);
    }

    public void addBucket(String bucket){
        storageStrategy.createBucket(bucket);
    }

    public void uploadObject(String bucket, String userId, String key, byte[] data){
        if(!policyManager.hasPermission(bucket,userId, PermissionType.WRITE)){
            throw new RuntimeException("Following user does not have write access");
        }
        storageStrategy.store(bucket,key,data);
    }

    public byte[] downloadObject(String bucket, String userId,String key){
        if(!policyManager.hasPermission(bucket,userId, PermissionType.READ)){
            throw new RuntimeException("Following user does not have read access");
        }
        return storageStrategy.retrieve(bucket,key);
    }

    public void deleteObject(String bucket, String userId, String key){
        if(!policyManager.hasPermission(bucket,userId, PermissionType.DELETE)){
            throw new RuntimeException("Following user does not have delete access");
        }
        storageStrategy.delete(bucket,key);
    }

    public static BlobStorageFacade getInstance(StorageStrategy storageStrategy,
                                                PolicyManager policyManager) {
        if(instance == null){
            synchronized (BlobStorageFacade.class){
                if(instance == null){
                    instance = new BlobStorageFacade(storageStrategy, policyManager);
                }
            }
        }
        return instance;
    }
}
