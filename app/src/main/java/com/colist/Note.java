package com.colist;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Note {
    private String title;
    private String content;
    private int id;

    public Note(String title, String content, Context context)
    {
        this.title = title;
        this.content = content;
        this.id = myID(context);
    }

    private int myID (Context context)
    {
        File[] files = ReadWriter.fileList(context);
        ArrayList<Integer> ids = new ArrayList<>();

        for (File f : files) {
            if (f.getName().matches("note_\\d+")) {
                ids.add(getFileID(f.getName()));
            }
        }

        int tallestID = 0;
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) > tallestID)
                tallestID = ids.get(i);
        }

        return tallestID + 1;
    }

    private Note(int id, String title, String content)
    {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static Note getByID(int id, Context context)
    {
        String filename = getFilename(id);
        String noteString = "" ;
        try {
            noteString = ReadWriter.readLoad(context, filename);
        }
        catch (IOException e) {
            // TODO: hhhhh
        }
        String[] info = noteString.split(":");
        if (info.length == 0)
            return new Note(id, "", "");
        else
            return new Note(id, info[0], info[1]);
    }

    public String getTitle() { return title; }
    public String getContent() { return content; }
    public int getID() { return id; }

    public Note UpdateContent(String newContent) { return new Note(id, title, newContent); }
    public Note UpdateTitle(String newTitle) { return new Note(id, newTitle, content); }

    public void delete(Context context) {
        ReadWriter.deleteFile(getFilename(), context);
    }
    public void save(Context context)
    {
        try {
            ReadWriter.writeSave(context, getFilename(), getSaveString());
        }
        catch (IOException e) {
            // TODO: handle
        }
    }

    public String getType() { return "note"; }
    public String getSaveString() { return title + ":" + content; }
    public String getFilename() { return getType() + "_" + getID(); }

    private static String getFilename(int id) { return "note" + "_" + id; }
    private static int getFileID(String f) { return Integer.parseInt(f.split("_")[1]); }

    public static ArrayList<Note> getAll(Context context)
    {
        ArrayList<Note> allNotes = new ArrayList<>();
        File[] files = ReadWriter.fileList(context);

        for (File f : files) {
            if (f.getName().matches("note_\\d+")) {
                String s = ":";
                int id = getFileID(f.getName());
                try {
                    s = ReadWriter.readLoad(context, f.getName());
                }
                catch (IOException e) {
                    // TODO: handle
                }
                finally {
                    String[] data = s.split(":");
                    if (data.length == 0)
                        new Note(id, "", "").delete(context);
                    else
                        allNotes.add(new Note(id, data[0], data[1]));
                }
            }
        }
        return allNotes;
    }
}
