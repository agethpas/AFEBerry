package com.aget.AFEBerry;

/**
 * Created by Pascal on 15.07.2016.
 */
public class Note {
    private String title, message;
    private long noteId, dateCreatedMilli;





    public Note(String title, String message) {
        this.title = title;
        this.message = message;
        this.noteId = 0;
        this.dateCreatedMilli = 0;
    }

    public Note(String title, String message, long noteId, long dateCreatedMilli ) {
        this.title = title;
        this.message = message;
        this.noteId = noteId;
        this.dateCreatedMilli = dateCreatedMilli;

    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }




    public long getDateCreatedMilli() {
        return dateCreatedMilli;
    }

    public long getNoteId() {
        return noteId;
    }

    @Override
    public String toString() {
        return "ID: "+ noteId +  "Title=" + title + "Message" + message
                +  "IconID: " +  "Date: " + dateCreatedMilli;
    }





}
