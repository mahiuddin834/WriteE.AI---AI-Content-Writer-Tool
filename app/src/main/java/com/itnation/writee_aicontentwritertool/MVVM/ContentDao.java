package com.itnation.writee_aicontentwritertool.MVVM;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContentDao {

    @Insert
    public void insert(Content content);
    @Update
    public void update(Content content);
    @Delete
    public void delete(Content content);
    @Query("SELECT * FROM my_contents")
    public LiveData<List<Content>> getAllData();
}
