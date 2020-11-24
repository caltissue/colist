package com.colist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.colist.Entry.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class EntryManager {
    // get Entry from Edit View (when saving)

    final private static String filePrefix = "CoList-Entry-";
    final private static String changelogPrefix = "CoList-Changelog-";

    public static void saveEntry(Entry entry) {
        HashMap<String, Object> objectMap = entry.getSaveObject();
        String filename = filePrefix + objectMap.get("id");
        String changelogname = changelogPrefix + objectMap.get("id");
        Date d = new Date();

        try {
            ReadWriter.saveEntry(filename, objectMap);
            ReadWriter.updateFile(changelogname, "Updated by cal on " + d.toString() + "\n");
        }
        catch (IOException io) {
            // TODO
        }
    }

    public static Entry getEntry(int id) {
        HashMap<String, Object> objectMap = new HashMap<>();
        try {
            objectMap = ReadWriter.getEntry(filePrefix + id);
        }
        catch (Exception e) {
            // TODO
        }

        Entry entry;
        String type = objectMap.get("type").toString();

        if (type.equals("note")) entry = new Note(objectMap);
        else if (type.equals("list")) entry = new List(objectMap);
        else entry = new Note(objectMap); // TODO: this will one day cause you immense confusion

        return entry;
    }

    public static void deleteEntry(int id) {
        String filename = filePrefix + id;
        ReadWriter.deleteFile(filename);
    }

    public static ArrayList<Entry> getAllEntries() {
        ArrayList<Entry> entries = new ArrayList<>();
        File[] files;
        try {
            files = ReadWriter.fileList();

            for (File f : files) {
                if(f.getName().contains(filePrefix)) {
                    String idString = f.getName().substring(filePrefix.length());
                    int id = Integer.parseInt(idString);
                    Entry entry = getEntry(id);
                    entries.add(entry);
                }
            }
        }
        catch (IOException io) {
            // TODO
        }

        return entries;
    }

    public static ArrayList<View> getAllPreviews(Context context) {
        ArrayList<Entry> entries = getAllEntries();
        ArrayList<View> views = new ArrayList<>();

        for (Entry e : entries) {
            views.add(e.getUI().getPreview(context));
        }

        return views;
    }

    // TODO: delete this
    public static View getFrontPage(Context context) {
        LinearLayout l2 = new LinearLayout(context);
        l2.setOrientation(LinearLayout.VERTICAL);

        ArrayList<Entry> entries = getAllEntries();
        for (Entry e : entries) {
            l2.addView(e.getUI().getPreview(context));
        }

        ScrollView sv = new ScrollView(context);
        sv.addView(l2);

        return sv;
    }

    public static View getChangelogView(Context context, int id) {
        String filename = changelogPrefix + id;
        String contents = "";
        try {
            contents = ReadWriter.readFile(filename);
        }
        catch (IOException io) {
            // TODO
        }

        TextView t = new TextView(context);
        t.setText(contents);
        t.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ScrollView sv = new ScrollView(context);
        sv.addView(t);

        return sv;
    }
}
