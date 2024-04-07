package org.example;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        PostService postService = new PostService();
        NewsFeedService newsFeed = NewsFeedService.getInstance(userService, postService);
        newsFeed.follow("1", "2");
        newsFeed.follow("1", "3");
        newsFeed.follow("1", "4");
        newsFeed.follow("1", "5");
        newsFeed.follow("1", "6");
        newsFeed.follow("1", "7");
        newsFeed.follow("1", "8");
        newsFeed.follow("1", "9");
        newsFeed.follow("1", "10");
        newsFeed.follow("1", "11");
        newsFeed.follow("1", "12");
        newsFeed.follow("1", "13");
        newsFeed.createPost("1", "1000");
        newsFeed.createPost("2", "1002");
        newsFeed.createPost("3", "1003");
        newsFeed.createPost("4", "1004");
        newsFeed.createPost("5", "1005");
        newsFeed.createPost("6", "1006");
        newsFeed.createPost("7", "1007");
        newsFeed.createPost("8", "1008");
        newsFeed.createPost("9", "1009");
        newsFeed.createPost("10", "1010");
        newsFeed.createPost("11", "1011");
        newsFeed.createPost("12", "1012");
        newsFeed.createPost("13", "1013");
        newsFeed.getNewsFeed("1");
        newsFeed.unfollow("1", "13");
        newsFeed.getNewsFeed("1");
        newsFeed.deletePost("12", "1012");
        newsFeed.getNewsFeed("1");
        newsFeed.getNewsFeedPaginated("1", 2);
        newsFeed.getNewsFeedPaginated("1", 5);
    }

}
