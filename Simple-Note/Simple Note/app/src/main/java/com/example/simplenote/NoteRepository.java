package com.example.simplenote;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDataAccessObjects noteDao;
    private LiveData<List<Note>> allNotes;
    //to assign this two variable we need this type constructor
    public NoteRepository (Application application){

        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();

    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void  update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public void deleteAllNotes(){
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public static class InsertNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDataAccessObjects noteDao;
        private InsertNoteAsyncTask(NoteDataAccessObjects noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    public static class UpdateNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDataAccessObjects noteDao;
        private UpdateNoteAsyncTask(NoteDataAccessObjects noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    public static class DeleteNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDataAccessObjects noteDao;
        private DeleteNoteAsyncTask(NoteDataAccessObjects noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    public static class DeleteAllNoteAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDataAccessObjects noteDao;
        private DeleteAllNoteAsyncTask(NoteDataAccessObjects noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}




/*



 */