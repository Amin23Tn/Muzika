package tn.example.muzika.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.example.muzika.models.user;


@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<user> getAll();

    @Query("SELECT * FROM user WHERE NOT username == :username")
    List<user> getAllExept(String username);


    @Query("SELECT * FROM user WHERE username == :username and password == :mdp")
    user login(String username,String mdp);

    @Query("SELECT * FROM user WHERE id == :id")
    user findbyId(String id);

    @Query("SELECT * FROM user WHERE username == :username ")
    user findbyusername(String username);


    @Query("SELECT * FROM user WHERE email == :email LIMIT 1")
    user findByEmail(String email);

    @Insert
    void insertOne(user usr);

    @Delete
    void delete(user user);

}
