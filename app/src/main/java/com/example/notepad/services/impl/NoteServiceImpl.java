package com.example.notepad.services.impl;

import android.content.Context;

import com.example.notepad.Note;
import com.example.notepad.dao.NoteDAO;
import com.example.notepad.dao.impl.NoteDAOImpl;
import com.example.notepad.services.NoteService;

import java.util.List;

public class NoteServiceImpl implements NoteService {
    private NoteDAO noteDAO;
    public NoteServiceImpl(Context context){
        noteDAO = new NoteDAOImpl(context);
    }
    @Override
    public Note addNote(Note note) {
        return noteDAO.addNote(note);
    }

    @Override
    public Note getNote(long id) {
        return noteDAO.getNote(id);
    }

    @Override
    public int update(Note note) {
        return noteDAO.update(note);
    }

    @Override
    public int remove(Note note) {
        return noteDAO.remove(note);
    }

    @Override
    public List<Note> getAllNote() {
        return noteDAO.getAllNote();
    }
}
