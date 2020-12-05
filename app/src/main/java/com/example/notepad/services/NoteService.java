package com.example.notepad.services;

import com.example.notepad.Note;

public interface NoteService {
    Note addNote(Note note);
    Note getNote(long id);
    int update(Note note);
    int remove(Note note);
}
