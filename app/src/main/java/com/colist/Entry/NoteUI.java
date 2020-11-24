package com.colist.Entry;

import android.content.Context;
import android.view.View;
import android.widget.*;
import android.view.ViewGroup.LayoutParams;

public class NoteUI implements EntryUI {
    private final Note note;
    private final LayoutParams mLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

    public NoteUI(Note note) { this.note = note; }
    public Note getNote() {
        return note;
    }

    @Override
    public View getPreview(Context context) {
        TextView v = new TextView(context);
        v.setId(note.getId());

        int subLength = Math.min(note.getContent().length(), 30);
        String s = note.getTitle() + "\n" + note.getContent().substring(0, subLength);
        v.setText(s);

        v.setLayoutParams(mLayoutParams);

        v.setLines(2);
        v.setPadding(50,40,50,10);

        return v;
    }

    @Override
    public View getView(Context context) {
        TextView titleView = new TextView(context);
        titleView.setText(note.getTitle());
        titleView.setLayoutParams(mLayoutParams);

        TextView contentView = new TextView(context);
        contentView.setText(note.getContent());
        contentView.setLayoutParams(mLayoutParams);

        LinearLayout l2 = new LinearLayout(context);
        l2.setOrientation(LinearLayout.VERTICAL);
        l2.setPadding(40,50,40,50);
        l2.addView(titleView, 0);
        l2.addView(contentView, 1);

        ScrollView sv = new ScrollView(context);
        sv.setLayoutParams(new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT, 98
        ));
        sv.addView(l2);

        return sv;
    }

    @Override
    public View getEditableView(Context context) {
        EditText titleEdit = new EditText(context);
        titleEdit.setText(note.getTitle());
        titleEdit.setLayoutParams(mLayoutParams);

        EditText contentEdit = new EditText(context);
        contentEdit.setText(note.getContent());
        contentEdit.setLayoutParams(mLayoutParams);

        LinearLayout l2 = new LinearLayout(context);
        l2.setOrientation(LinearLayout.VERTICAL);
        l2.setPadding(40,50,40,50);
        l2.addView(titleEdit, 0);
        l2.addView(contentEdit, 1);

        ScrollView sv = new ScrollView(context);
        sv.setLayoutParams(new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT, 98
        ));
        sv.addView(l2);

        return sv;
    }
}
