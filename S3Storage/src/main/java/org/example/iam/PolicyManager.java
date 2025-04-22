package org.example.iam;

import java.util.*;

public class PolicyManager {
    Map<String, Map<String, Set<PermissionType>>> accessLevelMap = new HashMap<>(); // bucket, {user,permission}

    public void grantPermission(String bucket, String userId, PermissionType permission) {
        accessLevelMap
                .computeIfAbsent(bucket, b -> new HashMap<>())
                .computeIfAbsent(userId, u -> new HashSet<>())
                .add(permission);
    }

    public boolean hasPermission(String bucketId, String userId, PermissionType permissionType){
        return accessLevelMap.getOrDefault(bucketId, Collections.emptyMap())
                .getOrDefault(userId, Collections.emptySet())
                .contains(permissionType);
    }
}
