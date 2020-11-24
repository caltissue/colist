package com.colist.Entry;

import android.content.Context;
import android.view.View;

public interface EntryUI {
    View getPreview(Context context);
    View getView(Context context);
    View getEditableView(Context context);
    //static String extractObjectStringFromView(View v);
}
