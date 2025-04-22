package org.example.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bucket {
    private String name;
    private Map<String,List<S3Object>> s3ObjectMap = new HashMap<>(); // key, s3obj

    public void addObject(String key, byte[] data){
        s3ObjectMap.computeIfAbsent(key, k-> new ArrayList<>()).add(new S3Object(data));
    }

    public S3Object getObject(String key){
        List<S3Object> s3Objects = s3ObjectMap.get(key);
        if (s3Objects.isEmpty())return null;
        return s3Objects.get(s3Objects.size()-1);
    }

    public void deleteObject(String key){
        s3ObjectMap.remove(key);
    }
}
