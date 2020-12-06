package com.example.notepad.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.notepad.Note;
import com.example.notepad.dao.NoteDAO;
import com.example.notepad.db.NoteDBHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.notepad.db.NoteDBHelper.CONTENT;
import static com.example.notepad.db.NoteDBHelper.ID;
import static com.example.notepad.db.NoteDBHelper.MODE;
import static com.example.notepad.db.NoteDBHelper.TABLE_NAME;
import static com.example.notepad.db.NoteDBHelper.TIME;

public class NoteDAOImpl implements NoteDAO {
    private String[] projections={
            CONTENT,
            MODE,
            TIME
    };
    private SQLiteOpenHelper noteDBHelper;
    private SQLiteDatabase db;
    private Context context;

    public NoteDAOImpl(Context context){
        this.context=context;
    }
    private void init(){
        noteDBHelper=new NoteDBHelper(context,null,null,1);
        db=noteDBHelper.getWritableDatabase();
    }

    private void close(){
        noteDBHelper.close();
        db.close();
    }
    @Override
    public Note addNote(Note note) {
        init();
        ContentValues values = getContentValues(note);
        long insertId = db.insert(TABLE_NAME, null, values);
        note.setId(insertId);
        close();
        return note;
    }

    @Override
    public Note getNote(long id) {
        init();
        Cursor cursor = db.query(TABLE_NAME, projections, ID + " = ?", new String[]{String.valueOf(id)},
                null, null, null, null);
        Note note=null;
        if (cursor!=null){
            cursor.moveToFirst();
            note=new Note(cursor.getString(cursor.getColumnIndex(CONTENT)),
                    cursor.getString(cursor.getColumnIndex(TIME)),
                    cursor.getString(cursor.getColumnIndex(MODE)));
        }
        close();
        return note;
    }

    @Override
    public int update(Note note) {
        init();
        ContentValues values = getContentValues(note);
        int update = db.update(TABLE_NAME, values, ID + "=?", new String[]{String.valueOf(note.getId())});
        close();
        return update;
    }

    @Override
    public int remove(Note note) {
        init();
        int delete = db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(note.getId())});
        close();
        return delete;
    }

    @Override
    public List<Note> getAllNote() {
        init();
        Cursor cursor = db.query(TABLE_NAME, projections, null, null,
                null, null, null, null);
        List<Note> notes=new ArrayList<>();
        if (cursor!=null){
            while (cursor.moveToNext()){
                notes.add(new Note(cursor.getString(cursor.getColumnIndex(CONTENT)),
                        cursor.getString(cursor.getColumnIndex(TIME)),
                        cursor.getString(cursor.getColumnIndex(MODE))));
            }
        }
        close();
        return notes;
    }

    private ContentValues getContentValues(Note note){
        ContentValues values =new ContentValues();
        values.put(CONTENT,note.getContent());
        values.put(TIME,note.getTime());
        values.put(MODE,note.getTag());
        return values;
    }
}
