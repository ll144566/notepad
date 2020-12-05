package com.example.notepad.services.impl;

import com.example.notepad.Note;
import com.example.notepad.dao.NoteDAO;
import com.example.notepad.dao.impl.NoteDAOImpl;
import com.example.notepad.services.NoteService;

public class NoteServiceImpl implements NoteService {
    private NoteDAO noteDAO=new NoteDAOImpl();
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
}
