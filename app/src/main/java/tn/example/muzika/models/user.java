package tn.example.muzika.models;

import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
@Entity (tableName = "user")
public class user {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "token")
    private String token;
    @ColumnInfo(name = "spotifyToken")
    private String spotifyToken;
    @ColumnInfo(name = "image")
    private String image;

    public user() {
    }

    public user(int id, String username, String email, String token, String spotifyToken, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.token = token;
        this.spotifyToken = spotifyToken;
    }
    public user(String username, String email, String token, String spotifyToken, String image) {
        this.username = username;
        this.email = email;
        this.token = token;
        this.spotifyToken = spotifyToken;
    }
    public user(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }



    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public String getSpotifyToken() {
        return spotifyToken;
    }

    public void setSpotifyToken(String spotifyToken) {
        this.spotifyToken = spotifyToken;
    }

    @Override
    public String toString() {
        return "user{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", spotifyToken='" + spotifyToken + '\'' +
                '}';
    }
}
