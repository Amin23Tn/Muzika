package tn.example.muzika.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "friendships")
public class friendships {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "iduser")
    private String iduser;
    @ColumnInfo(name = "idfriend")
    private String idfriend;

    public friendships() {
    }
    public friendships(int id, String iduser, String idfriend) {
        this.id = id;
        this.iduser = iduser;
        this.idfriend = idfriend;
    }
    public friendships( String iduser, String idfriend) {
        this.iduser = iduser;
        this.idfriend = idfriend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIdfriend() {
        return idfriend;
    }

    public void setIdfriend(String idfriend) {
        this.idfriend = idfriend;
    }

    @Override
    public String toString() {
        return "friendships{" +
                "id=" + id +
                ", iduser='" + iduser + '\'' +
                ", idfriend='" + idfriend + '\'' +
                '}';
    }
}
