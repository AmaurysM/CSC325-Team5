package com.csc325Team5.payrollapplication.model;

import java.util.LinkedList;

public class NoteManager {

    private LinkedList<Note> notes = new LinkedList<>();

    public void addNote(User sender,String note,User receiver){
        notes.add(new Note(sender,note,sender.getCurrentTime(),receiver));
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public void removeNote(Note note){
        notes.remove(note);
    }

    public LinkedList<Note> getNotes(){
        return notes;
    }

}
