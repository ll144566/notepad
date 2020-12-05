package com.example.notepad.dao;

import com.example.notepad.Note;

public interface NoteDAO {
    Note addNote(Note note);
    Note getNote(long id);
    int update(Note note);
    int remove(Note note);
}
