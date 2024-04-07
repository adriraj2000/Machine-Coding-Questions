package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class NewsFeedService {
    private UserService userService;
    private PostService postService;
    private static NewsFeedService instance;
    private final int feedSize;
    private final int pageSize;
    private static int postNumber;

    private NewsFeedService(UserService userService, PostService postService) {
        feedSize = 10;
        pageSize = 2;
        postNumber = 0;
        this.userService = userService;
        this.postService = postService;
    }

    public void createPost(String userId, String postId){
        postService.createPost(userId, postId, postNumber++);
    }

    public void deletePost(String userId, String postId){
        postService.deletePost(userId, postId);
    }

    public void follow(String followerId, String followeeId){
        userService.follow(followerId, followeeId);
    }

    public void unfollow(String followerId, String followeeId){
        userService.unfollow(followerId, followeeId);
    }

    private List<String> fetchTopNPosts(String userId, int N){
        List<Post> userPosts = postService.getPosts(userId);
        PriorityQueue<Post> recentPosts = new PriorityQueue<>((a,b)->b.getPostNumber()-a.getPostNumber());
        recentPosts.addAll(userPosts);
        for(String followee : userService.getFolloweeList(userId)){
            List<Post> followeePosts = postService.getPosts(followee);
            recentPosts.addAll(followeePosts);
        }
        int postsCount = 0;
        List<String> feed = new ArrayList<>();
        while(!recentPosts.isEmpty() && postsCount < N){
            feed.add(recentPosts.poll().getPostId());
            postsCount++;
        }
        return feed;
    }

    public List<String> getNewsFeed(String userId){
        System.out.println("Feed for user " + userId);
        List<String> feed = fetchTopNPosts(userId, feedSize);
        System.out.println(feed);
        return feed;
    }

    public List<String> getNewsFeedPaginated(String userId, int pageNumber){
        List<String> feed = fetchTopNPosts(userId, Integer.MAX_VALUE);
        int start = pageNumber*pageSize;
        int end = Math.min(start + pageSize, feed.size());
        if(start <= end){
            System.out.println("Page number " + pageNumber + " of user " + userId + " feed");
            System.out.println(feed.subList(start,end));
            return feed.subList(start, end);
        }
        System.out.println("Page number " + pageNumber + " of user " + userId + " feed");
        return new ArrayList<>();
    }

    public static NewsFeedService getInstance(UserService userService, PostService postService) {
        if(instance == null){
            instance = new NewsFeedService(userService, postService);
        }
        return instance;
    }
}
