package com.example.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

public class NoteAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<Note> backList;
    private List<Note> noteList;
    public NoteAdapter(Context context,List<Note> noteList){
        this.context=context;
        this.noteList=noteList;
    }
    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView==null){
            view= LayoutInflater.from(context).inflate(R.layout.note_layout,parent,false);
        }else {
            view=convertView;
        }
        ((TextView)view.findViewById(R.id.tv_time)).setText(noteList.get(position).getTime());
        ((TextView)view.findViewById(R.id.tv_content)).setText(noteList.get(position).getContent());

        return view;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public void changeList(List list){
        noteList=list;
        notifyDataSetChanged();
    }
}
