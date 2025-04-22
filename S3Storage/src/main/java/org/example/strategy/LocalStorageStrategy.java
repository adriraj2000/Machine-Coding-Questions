package org.example.strategy;

import org.example.entities.Bucket;
import org.example.entities.S3Object;

import java.util.HashMap;
import java.util.Map;

public class LocalStorageStrategy implements StorageStrategy{
    private Map<String, Bucket> bucketMap = new HashMap<>();
    @Override
    public void store(String bucket, String key, byte[] data) {
        bucketMap.get(bucket).addObject(key, data);
    }

    @Override
    public void delete(String bucket, String key) {
        bucketMap.getOrDefault(bucket, new Bucket()).deleteObject(key);
    }

    @Override
    public void createBucket(String bucket) {
        bucketMap.putIfAbsent(bucket, new Bucket());
    }

    @Override
    public byte[] retrieve(String bucket, String key) {
        S3Object obj = bucketMap.getOrDefault(bucket, new Bucket()).getObject(key);
        return obj != null ? obj.getData() : null;
    }
}
