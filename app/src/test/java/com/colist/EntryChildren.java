package com.colist;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;
import com.colist.Entry.*;

public class EntryChildren {
    private Note n, n1;
    private List l, l2;

    @Before
    public void Note() {
        n = new Note(1,"a", "b");
        n1 = new Note(2,"w", "x");

        ArrayList<String> lList = new ArrayList<>();
        lList.add("f");
        l = new List(3, "a", lList);

        ArrayList<String> l2List = new ArrayList<>();
        l2List.add("f");
        l2List.add("g");
        l2 = new List(4, "w", l2List);
    }


    /****** NOTE ******/
    @Test
    public void NoteHasTitle() {
        assertEquals("a", n.getTitle());
        assertEquals("w", n1.getTitle());
    }

    @Test
    public void NoteHasContent() {
        assertEquals("b", n.getContent());
        assertEquals("x", n1.getContent());
    }

    @Test
    public void NoteHasId() {
        assertEquals(1, n.getId());
        assertEquals(2, n1.getId());
    }

    @Test
    public void NoteUpdatesTitle() {
        Note n2 = n.updateTitle("aa");
        assertEquals("aa", n2.getTitle());
        assertNotEquals(n.getTitle(), n2.getTitle());
        assertEquals(n.getId(), n2.getId());
        assertEquals(n.getContent(), n2.getContent());

        Note n3 = n1.updateTitle("ww");
        assertEquals("ww", n3.getTitle());
        assertNotEquals(n1.getTitle(), n3.getTitle());
        assertEquals(n1.getId(), n3.getId());
        assertEquals(n1.getContent(), n3.getContent());
    }

    @Test
    public void NoteUpdatesContent() {
        Note n2 = n.updateContent("bb");
        assertEquals("bb", n2.getContent());
        assertNotEquals(n.getContent(), n2.getContent());
        assertEquals(n.getId(), n2.getId());
        assertEquals(n.getTitle(), n2.getTitle());

        Note n3 = n1.updateContent("xx");
        assertEquals("xx", n3.getContent());
        assertNotEquals(n1.getContent(), n3.getContent());
        assertEquals(n1.getId(), n3.getId());
        assertEquals(n1.getTitle(), n3.getTitle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Note_HashMapConstructor_RequiresArgs() {
        Note n0 = new Note(new HashMap<String, Object>());
    }

    @Test
    public void Note_HashMapConstructor() {
        Note n0 = new Note(n.getSaveObject());
        assertEquals(n.getId(), n0.getId());
        assertEquals(n.getTitle(), n0.getTitle());
        assertEquals(n.getContent(), n0.getContent());
    }

    /****** LIST ******/
    @Test
    public void List_HasTitle() {
        assertEquals("a", l.getTitle());
        assertEquals("w", l2.getTitle());
    }

    @Test
    public void List_HasListOfItems() {
        assertEquals(1, l.getItemList().size());
        assertEquals(2, l2.getItemList().size());
    }

    @Test
    public void List_HasId() {
        assertEquals(3, l.getId());
        assertEquals(4, l2.getId());
    }

    @Test
    public void List_UpdatesItemList() {
        ArrayList<String> copy = new ArrayList<>(l.getItemList());
        copy.add("e");

        List l3 = l.updateList(copy);
        assertEquals(2, l3.getItemList().size());
        assertEquals(1, l3.getItemList().indexOf("e"));
    }

    @Test
    public void List_UpdatesTitle() {
        List l3 = l.updateTitle("12345");
        assertEquals(l.getId(), l3.getId());
        assertEquals("12345", l3.getTitle());
        assertEquals(l.getItemList(), l3.getItemList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void List_HashMapConstructor_RequiresArgs() {
        List l0 = new List(new HashMap<String, Object>());
    }

    @Test
    public void List_HashMapConstructor() {
        List l0 = new List(l.getSaveObject());
        assertEquals(l.getId(), l0.getId());
        assertEquals(l.getTitle(), l0.getTitle());
        assertEquals(l.getItemList(), l0.getItemList());
    }

}
