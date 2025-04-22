package org.example.strategy;

public interface StorageStrategy {
    void store(String bucket, String key, byte[] data);
    void delete(String bucket, String key);
    void createBucket(String bucket);
    byte[] retrieve(String bucket,String key);
}
