package com.colist;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Note {
    private String title;
    private String content;
    private int id;
    private Context context;
    private String type = "note";

    // CONSTRUCTOR
    public Note(String title, String content, Context context)
    {
        this.title = title;
        this.content = content;
        this.id = myID();
        this.context = context;
    } // should probably be private

    private Note(int id, String title, String content)
    {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // PUBLIC INTERFACE (INSTANCE)
    public String getTitle() { return title; } // no
    public String getContent() { return content; } // no
    public int getID() { return id; } // no: getByID logic should be private

    // definitely should be private shit
    public Note UpdateContent(String newContent) { return new Note(id, title, newContent); }
    public Note UpdateTitle(String newTitle) { return new Note(id, newTitle, content); }
    // matter fact, the public interface should deal with requesting/receiving UI elements
    // mediated by some manager class which deals with these guys
    // so we'll see what should be public... not a lot
    public void delete() {
        ReadWriter.deleteFile(getFilename(), context);
        ReadWriter.deleteFile(getChangelogFilename(), context);
    }

    public void save()
    {
        try {
            ReadWriter.writeSave(context, getFilename(), getFileContents());
            // TODO: find out if no existing file for changelog; if so write created message
            ReadWriter.writeSave(context, getChangelogFilename(), getChangelogContents(), true);
        }
        catch (IOException e) {
            // TODO: handle
        }
    }

    public String getChangeHistory() {
        String changeLogText = "";
        try {
            changeLogText = ReadWriter.readLoad(context, getChangelogFilename());
        }
        catch (IOException e) {
            // TODO: handle
        }
        return changeLogText;
    }

    // MY PERSONAL SHIT
    private int myID ()
    {
        File[] files = ReadWriter.fileList(context);
        ArrayList<Integer> ids = new ArrayList<>();

        for (File f : files) {
            if (f.getName().matches("note_\\d+")) {
                ids.add(Integer.parseInt(f.getName().split("_")[1]));
            }
        }

        int tallestID = 0;
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) > tallestID)
                tallestID = ids.get(i);
        }

        return tallestID + 1;
    }

    private String getFilename() { return type + "_" + id; }
    private String getFileContents() { return title + ":" + content; }

    private String getChangelogFilename() { return type + "_changelog_" + id; }
    private String getChangelogContents() { return "##cal updated " + new Date().toString(); }

    // STATIC
    public static ArrayList<Note> getAll(Context context)
    {
        ArrayList<Note> allNotes = new ArrayList<>();
        File[] files = ReadWriter.fileList(context);

        for (File f : files) {
            if (f.getName().matches("note_\\d+")) {
                String s = ":";
                int id = Integer.parseInt(f.getName().split("_")[1]);
                try {
                    s = ReadWriter.readLoad(context, f.getName());
                }
                catch (IOException e) {
                    // TODO: handle
                }
                finally {
                    String[] data = s.split(":");
                    if (data.length == 0)
                        new Note(id, "", "").delete();
                    else
                        allNotes.add(new Note(id, data[0], data[1]));
                }
            }
        }
        Note f = new Note("","", context);
        String g = f.title;
        String h = f.content;
        int i = f.id;
        return allNotes;
    }

    public static Note getByID(int id, Context context)
    {
        String filename = "note_" + id;
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
}
