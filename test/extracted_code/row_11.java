import java.util.ArrayList;
import java.util.List;

public class User {
    private String userID;
    private String username;
    private String email;
    private List<Post> posts;
    private List<User> followers;

    public User(String userID, String username, String email) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.posts = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    // Methods to follow/unfollow another User and create/delete a post are omitted for brevity

    public void follow(User user) {
        if (!followers.contains(user)) {
            followers.add(user);
        }
    }

    public void unfollow(User user) {
        followers.remove(user);
    }

    // Getters and setters omitted for brevity
}
import java.util.ArrayList;
import java.util.List;

public class Post {
    private String postID;
    private String content;
    private long timePosted;
    private String userID;
    private List<String> likes;

    public Post(String postID, String content, long timePosted, String userID) {
        this.postID = postID;
        this.content = content;
        this.timePosted = timePosted;
        this.userID = userID;
        this.likes = new ArrayList<>();
    }

    public void like(String userID) {
        if (!likes.contains(userID)) {
            likes.add(userID);
        }
    }

    public void dislike(String userID) {
        likes.remove(userID);
    }

    // Getters and setters omitted for brevity
}
sql
CREATE TABLE Users (
    userID VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE Posts (
    postID VARCHAR(255) PRIMARY KEY,
    content TEXT,
    timePosted BIGINT,
    userID VARCHAR(255),
    FOREIGN KEY (userID) REFERENCES Users(userID)
);

CREATE TABLE Likes (
    userID VARCHAR(255),
    postID VARCHAR(255),
    PRIMARY KEY (userID, postID),
    FOREIGN KEY (userID) REFERENCES Users(userID),
    FOREIGN KEY (postID) REFERENCES Posts(postID)
);
import java.sql.*;

public class User {
    // Existing methods...

    public void save(Connection conn) throws SQLException {
        String query = "INSERT INTO Users (userID, username, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userID);
            stmt.setString(2, username);
            stmt.setString(3, email);
            stmt.executeUpdate();
        }
    }

    public void follow(Connection conn, User user) throws SQLException {
        String query = "INSERT INTO Follows (followerUserID, followingUserID) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.userID);
            stmt.setString(2, user.getUserID());
            stmt.executeUpdate();
        }
    }

    // Existing methods...
}
import java.sql.*;

public class Post {
    // Existing methods...

    public void save(Connection conn) throws SQLException {
        String query = "INSERT INTO Posts (postID, content, timePosted, userID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, postID);
            stmt.setString(2, content);
            stmt.setLong(3, timePosted);
            stmt.setString(4, userID);
            stmt.executeUpdate();
        }
    }

    public void like(Connection conn, String userID) throws SQLException {
        String query = "INSERT INTO Likes (userID, postID) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userID);
            stmt.setString(2, this.postID);
            stmt.executeUpdate();
        }
    }

    // Existing methods...
}