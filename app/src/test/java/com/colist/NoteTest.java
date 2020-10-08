package com.colist;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NoteTest {
/*
    @Test
    public void ConstructorWorks() {
        Note n = new Note("the title", "the content");
        assertEquals("the title", n.getTitle());
        assertEquals("the content", n.getContent());

        Note n2 = new Note("new title", "new content");
        assertEquals("new title", n2.getTitle());
        assertEquals("new content", n2.getContent());
    }

    @Test
    public void SavedIDsIncrement() {
        Note n = new Note("a", "b");
        assertEquals(1, n.getID());
        n.save();

        Note n2 = new Note("x", "y");
        assertEquals(2, n2.getID());
        n2.save();

        Note n3 = new Note("z", "f");
        assertEquals(3, n3.getID());
    }

    @Test
    public void SavedNotesAreRetrievable() {
        Note n = new Note("a", "b");
        n.save();

        Note n2 = Note.getByID(n.getID());
        assertEquals(n2.getID(), n.getID());
        assertEquals(n2.getTitle(), n.getTitle());
        assertEquals(n2.getContent(), n.getContent());
    }

    @Test
    public void SaveOverwritesPrevious() {
        Note n = new Note("a", "b");
        n.save();

        n.UpdateTitle("c").UpdateContent("d").save();
        Note n2 = Note.getByID(n.getID());

        assertEquals("c", n2.getTitle());
        assertEquals("d", n2.getContent());

        n.UpdateContent("f").UpdateTitle("e").save();
        Note n3 = Note.getByID(n2.getID());

        assertEquals("e", n3.getTitle());
        assertEquals("f", n3.getContent());
    }

    @Test
    public void GetAllIsAccurate() {
        ArrayList<Note> Notes = Note.getAll();
        assertTrue(Notes.isEmpty());

        Note n = new Note("a","b");
        n.save();
        assertTrue(Notes.isEmpty());

        Notes = Note.getAll();
        assertFalse(Notes.isEmpty());

        Note n2 = Notes.get(0);
        assertEquals(1, n2.getID());
        assertEquals("a", n2.getTitle());
        assertEquals("b", n2.getContent());

        Note.clearAll();
        assertFalse(Notes.isEmpty());
        Notes = Note.getAll();
        assertTrue(Notes.isEmpty());
    }

    @Test
    public void getSaveString_Type_and_Filename() {
        Note n = new Note("a", "b");
        String s = n.getSaveString();
        assertEquals("a:b", s);
        assertEquals("note", n.getType());
        assertEquals("note_1", n.getFilename());
    }
*/
}
