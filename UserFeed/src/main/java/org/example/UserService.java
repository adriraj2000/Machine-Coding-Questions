package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class UserService {
    private Map<String, User> userMap;
    private Map<String, HashSet<String>> following;

    public UserService(){
        following = new HashMap<>();
        userMap = new HashMap<>();
    }
    public HashSet<String> getFolloweeList(String userId) {
        return following.getOrDefault(userId, new HashSet<>());
    }

    public void follow(String followerId, String followeeId){
        User follower = new User(followerId);
        User followee = new User(followeeId);
        userMap.put(followerId, follower);
        userMap.put(followeeId, followee);
        HashSet<String> followeeList = following.getOrDefault(followerId, new HashSet<>());
        followeeList.add(followeeId);
        following.put(followerId, followeeList);
        System.out.println("User "+followerId + " following " + followeeId);
    }

    public void unfollow(String followerId, String followeeId){
        HashSet<String> followeeList = following.getOrDefault(followerId, new HashSet<>());
        followeeList.remove(followeeId);
        following.put(followerId, followeeList);
        System.out.println("User "+followerId + " unfollowing " + followeeId);
    }
}
