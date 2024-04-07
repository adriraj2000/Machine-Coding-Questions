package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostService {
    private Map<String,Post> postMap;
    private Map<String, List<String>> userPosts;
    public PostService(){
        userPosts = new HashMap<>();
        postMap = new HashMap<>();
    }

    public List<Post> getPosts(String userId){
       List<Post> posts = new ArrayList<>();
       for(String postId : userPosts.getOrDefault(userId, new ArrayList<>())){
           posts.add(postMap.get(postId));
       }
       return posts;
    }

    public void createPost(String userId, String postId, int postNumber){
        Post post = new Post(postId, postNumber);
        if(!postMap.containsKey(postId)) postMap.put(postId, post);
        List<String> postsByUser = userPosts.getOrDefault(postId, new ArrayList<>());
        postsByUser.add(postId);
        userPosts.put(userId,postsByUser);
        System.out.println("User " + userId + " creating post " + postId);
    }

    public void deletePost(String userId, String postId){
        postMap.remove(postId);
        List<String> postsByUser = userPosts.getOrDefault(postId, new ArrayList<>());
        postsByUser.remove(postId);
        userPosts.put(userId,postsByUser);
        System.out.println("User " + userId + " deleting post " + postId);
    }
}
