package com.colist;

import android.content.Context;
import android.view.View;

import com.colist.Entry.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class InterfaceImplementation {
    private Note n;
    private List l;

    @Before
    public void Note() {
        n = new Note(1,"a", "b");

        ArrayList<String> lList = new ArrayList<>();
        lList.add("f");
        l = new List(3, "a", lList);
    }

    @Test
    public void Entry_InterfaceImplemented() {
        Entry note1 = new Note(1,"a","b");
        Entry list1 = new List(1,"a", new ArrayList<String>());

        EntryUI ui = note1.getUI();
        EntryUI ui1 = list1.getUI();

        HashMap<String, Object> s = note1.getSaveObject();
        HashMap<String, Object> s1 = list1.getSaveObject();
    }

    @Test
    public void Note_HasSaveObject() {
        HashMap<String, Object> s = n.getSaveObject();
        assertEquals(n.getId(), s.get("id"));
        assertEquals(n.getTitle(), s.get("title"));
        assertEquals(n.getContent(), s.get("content"));
        assertEquals("note", s.get("type"));
    }

    @Test
    public void List_HasSaveObject() {
        HashMap<String, Object> s = l.getSaveObject();
        assertEquals(l.getId(), s.get("id"));
        assertEquals(l.getTitle(), s.get("title"));
        assertEquals(l.getItemList(), s.get("list"));
        assertEquals("list", s.get("type"));
    }


    /******* EntryUI *******/
    @Test
    public void EntryUI_InterfaceImplemented() {
        EntryUI noteUI1 = new NoteUI(n);
        EntryUI listUI1 = new ListUI(l);
    }

    @Test
    public void NoteUI_HasNote() {
        NoteUI nu = new NoteUI(n);
        NoteUI nuFromNote = n.getUI();
        assertEquals(nuFromNote.getNote(), nu.getNote());
    }

    @Test
    public void ListUI_HasList() {
        ListUI lu = new ListUI(l);
        ListUI luFromList = l.getUI();
        assertEquals(lu.getList(), luFromList.getList());
    }
}
