package tn.example.muzika.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface FriendsDao {

    @Query("SELECT * FROM friendships")
    List<friendships> getAll();


    @Query("SELECT * FROM friendships WHERE iduser == :iduser ")
    List<friendships> findfriends(String iduser);

    @Query("SELECT * FROM friendships WHERE iduser == :iduser AND idfriend=:idfriend")
    friendships findtheId(String iduser,String idfriend);

    @Query("SELECT * FROM friendships WHERE iduser == :iduser and idfriend == :idfriend ")
    friendships exist(String iduser,String idfriend);

    @Insert
    void insertOne(friendships frnd);

    @Delete
    void delete(friendships frnd);

}
