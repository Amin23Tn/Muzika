package tn.example.muzika.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface PostDao {

    @Query("SELECT * FROM Post")
    List<Post> getAll();


    @Query("SELECT * FROM Post WHERE userId == :userId ")
    List<Post> findById(String userId);

    @Insert
    void insertOne(Post post);

    @Delete
    void delete(Post post);

}
