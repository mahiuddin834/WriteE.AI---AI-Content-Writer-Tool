package com.itnation.writee_aicontentwritertool.MVVM;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContentRepo {

    private ContentDao contentDao;
    private LiveData<List<Content>> contentList;

    public ContentRepo(ContentDao contentDao, LiveData<List<Content>> contentList) {
        this.contentDao = contentDao;
        this.contentList = contentList;
    }

    public ContentRepo(Application application) {

        ContentDataBase contentDataBase = ContentDataBase.getInstance(application);
        contentDao=contentDataBase.contentDao();
        contentList= contentDao.getAllData();
    }


    public void insertData(Content content){ new InsertTask(contentDao).execute(content); }
    public void updateData(Content content){ new UpdatetTask(contentDao).execute(content); }
    public void deleteData(Content content){ new DeletetTask(contentDao).execute(content); }
    public LiveData<List<Content>> getAllData()
    {
        return contentList;
    }


    public static class InsertTask extends AsyncTask<Content, Void, Void>{

        private ContentDao contentDao;

        public InsertTask(ContentDao contentDao) {
            this.contentDao = contentDao;
        }

        @Override
        protected Void doInBackground(Content... contents) {
            contentDao.insert(contents[0]);
            return null;
        }
    }

      public static class UpdatetTask extends AsyncTask<Content, Void, Void>{

        private ContentDao contentDao;

        public UpdatetTask(ContentDao contentDao) {
            this.contentDao = contentDao;
        }

        @Override
        protected Void doInBackground(Content... contents) {
            contentDao.update(contents[0]);
            return null;
        }
    }

      public static class DeletetTask extends AsyncTask<Content, Void, Void>{

        private ContentDao contentDao;

        public DeletetTask(ContentDao contentDao) {
            this.contentDao = contentDao;
        }

        @Override
        protected Void doInBackground(Content... contents) {
            contentDao.delete(contents[0]);
            return null;
        }
    }





}
