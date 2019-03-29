package vcx.admin.com.myroomdb.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "user_table")
public class User implements Serializable {

    @NonNull
    @PrimaryKey
    int id;
    @ColumnInfo(name = "name")
    String fullname;

    public User(int id, String fullname) {
        this.id = id;
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

}
