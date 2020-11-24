package com.colist.Entry;

import java.util.HashMap;

public class Note implements Entry {
    private final int id;
    private final String title;
    private final String content;

    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Note(HashMap<String, Object> args) throws IllegalArgumentException {
        String[] myArgs = {"id", "title", "content"};
        for (String myArg : myArgs)
            if (!args.containsKey(myArg)) throw new IllegalArgumentException();

        id = (int) args.get("id");
        title = args.get("title").toString();
        content = args.get("content").toString();
    }

    @Override
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }

    public Note updateTitle(String newTitle) { return new Note(id, newTitle, content); }
    public Note updateContent(String newContent) { return new Note(id, title, newContent); }

    @Override
    public NoteUI getUI() { return new NoteUI(this); }

    @Override
    public HashMap<String, Object> getSaveObject() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("type", "note");
        data.put("id", id);
        data.put("title", title);
        data.put("content", content);
        return data;
    }
}
