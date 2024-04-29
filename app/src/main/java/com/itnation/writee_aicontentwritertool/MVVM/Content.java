package com.itnation.writee_aicontentwritertool.MVVM;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_contents")
public class Content {

    String title;
    String paragraph;
    @PrimaryKey(autoGenerate = true)
    int id;



    public Content(String title, String paragraph) {
        this.title = title;
        this.paragraph = paragraph;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }
}
