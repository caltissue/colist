package com.colist.Entry;

import java.util.ArrayList;
import java.util.HashMap;

public class List implements Entry {
    private final int id;
    private final String title;
    private final ArrayList<String> list;

    public List(int id, String title, ArrayList<String> list) {
        this.id = id;
        this.title = title;
        this.list = list;
    }

    public List(HashMap<String, Object> args) {
        String[] myArgs = {"id", "title", "list"};
        for (String myArg : myArgs)
            if (!args.containsKey(myArg)) throw new IllegalArgumentException();

        id = (int) args.get("id");
        title = args.get("title").toString();
        list = (ArrayList<String>) args.get("list");
    }

    @Override
    public int getId() { return id; }
    public String getTitle() { return title; }
    public ArrayList<String> getItemList() { return list; }

    public List updateTitle(String newTitle) { return new List(id, newTitle, list); }
    public List updateList(ArrayList<String> newList) { return new List(id, title, newList); }

    @Override
    public ListUI getUI() { return new ListUI(this); }

    @Override
    public HashMap<String, Object> getSaveObject() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("type", "list");
        data.put("id", id);
        data.put("title", title);
        data.put("list", list);
        return data;
    }
}
