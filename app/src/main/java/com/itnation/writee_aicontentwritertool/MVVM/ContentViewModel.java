package com.itnation.writee_aicontentwritertool.MVVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContentViewModel extends AndroidViewModel {

    private ContentRepo contentRepo;
    private LiveData<List<Content>> contentList;

    public ContentViewModel(@NonNull Application application) {
        super(application);

        contentRepo = new ContentRepo(application);
        contentList= contentRepo.getAllData();
    }


    public void insert(Content content){

        contentRepo.insertData(content);
    }

    public void update(Content content){

        contentRepo.updateData(content);
    }

    public void delete(Content content){

        contentRepo.deleteData(content);
    }

    public LiveData<List<Content>> getAllContent(){

        return contentList;
    }



}
