package com.colist;

import com.colist.Entry.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EntryManagement {

    @Before
    public void CreateDirectory() {
        ReadWriter.setFilepath("/Users/cal/Desktop/CoList/app/Entries");
        ReadWriter.makeDirectory();
    }

    @Test
    public void SaveAndRetrieve_Note() {
        Entry en = new Note(1, "title", "content of note");
        EntryManager.saveEntry(en);
        Entry en2 = EntryManager.getEntry(en.getId());

        assertEquals(en.getId(), en2.getId());
        assertEquals(en.getSaveObject(), en2.getSaveObject());
    }

    @Test
    public void SaveAndRetrieve_List() {
        ArrayList<String> s = new ArrayList<>();
        s.add("a");
        s.add("b");

        Entry en = new List(2, "title", s);
        EntryManager.saveEntry(en);
        Entry en2 = EntryManager.getEntry(en.getId());

        assertEquals(en.getId(), en2.getId());
        assertEquals(en.getSaveObject(), en2.getSaveObject());
    }

    @Test
    public void GetAll() {
        ArrayList<Entry> entries = EntryManager.getAllEntries();
        //assertEquals(2, entries.size());
        Entry en = new Note(1, "title", "content of note");


        assertEquals(en.getSaveObject(), entries.get(0).getSaveObject());
    }

}
