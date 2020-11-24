package com.colist.Entry;

import java.util.HashMap;

public interface Entry {
    int getId();
    EntryUI getUI();
    HashMap<String, Object> getSaveObject();

    //Entry update(String objectString);
    //String getObjectString();
}
