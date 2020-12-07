package com.example.notepad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.notepad.services.NoteService;
import com.example.notepad.services.impl.NoteServiceImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    public final static  int ADD_NOTE_CONTENT=0;
    public final static  int EDIT_NOTE_CONTENT=1;
    private static final String TAG = "10086";
    private NoteService noteService;
    private List<Note> noteList;
    private FloatingActionButton floatingActionButton;
    private ListView lv;
    private NoteAdapter adapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.tb_main_title);
        noteService = new NoteServiceImpl(this);
        noteList=new ArrayList<>();
        lv=findViewById(R.id.lv);
        adapter=new NoteAdapter(this,noteList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,EditActivity.class),ADD_NOTE_CONTENT);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ADD_NOTE_CONTENT:
                Note note = new Note(data.getStringExtra("content"),
                        data.getStringExtra("time"), "1");



                noteService.addNote(note);

                break;

        }
    }
    private void refresh(){
        noteList=noteService.getAllNote();
        adapter.changeList(noteList);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Note note = (Note) noteList.get(position);
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("note",note);
        startActivityForResult(intent,EDIT_NOTE_CONTENT);
    }
}