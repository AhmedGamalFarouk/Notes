package android.example.sandbox;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity (tableName = "notes")
public class NotesEntity implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "details")
    private String details;

    public NotesEntity(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
