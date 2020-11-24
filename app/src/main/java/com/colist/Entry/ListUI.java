package com.colist.Entry;

import android.content.Context;
import android.view.View;
import android.widget.*;
import android.view.ViewGroup.LayoutParams;

public class ListUI implements EntryUI {
    private final List list;

    public ListUI(List list) { this.list = list; }
    public List getList() { return list; }

    @Override
    public View getPreview(Context context) {
        TextView v = new TextView(context);
        v.setText("List title\nlist item, list item, etc...");

        LayoutParams p = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        v.setLayoutParams(p);

        v.setLines(2);
        v.setPadding(50,40,50,10);

        return v;
    }

    @Override
    public View getView(Context context) {
        ScrollView v = new ScrollView(context);
        return v;
    }

    @Override
    public View getEditableView(Context context) {
        ScrollView sv = new ScrollView(context);
        return sv;
    }
}
