package tn.example.muzika.models;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Entity(tableName = "Post")
public class Post {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "postContent")
    String postContent;
    @ColumnInfo(name = "playlistId")
    String playlistId;
    @ColumnInfo(name = "userId")
    String userId;

    public Post() {
    }

    public Post(int id, String postContent, String playlistId, String userId) {
        this.id = id;
        this.postContent = postContent;
        this.playlistId = playlistId;
        this.userId = userId;
    }

    public Post(String postContent, String playlistId, String userId) {
        this.postContent = postContent;
        this.playlistId = playlistId;
        this.userId = userId;
    }
/*

    public static ArrayList<Post> postFromJson(JSONArray object) throws JSONException {
        Post post = new Post();
        ArrayList<Post> array = new ArrayList<>();
        Log.d("TAG", "postFromJson: " + object.toString());
        for (int i = 0; i < object.length(); i++) {
            JSONObject o = (JSONObject) object.getJSONObject(i);
            post.id = o.getString("id");
            post.postContent = o.getString("postContent");
            post.playlistId = o.getString("playlistId");
            post.userId = o.getString("user_id");
            Log.d("User details class : ", "PostsFromJson: " + post.toString());
            array.add(post);
        }
        return array;
    }

     */

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", postContent='" + postContent + '\'' +
                ", playlistId='" + playlistId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
