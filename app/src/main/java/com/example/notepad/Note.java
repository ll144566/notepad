package com.example.notepad;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private long id;
    private String content;
    private String time;
    private String tag;

    public Note( String content, String time, String tag) {
        this.content = content;
        this.time = time;
        this.tag = tag;
    }

    protected Note(Parcel in) {
        id = in.readLong();
        content = in.readString();
        time = in.readString();
        tag = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(content);
        dest.writeString(time);
        dest.writeString(tag);
    }
}
