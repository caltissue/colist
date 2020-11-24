package com.colist;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityChangelog extends AppCompatActivity {

    private int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelog);

        noteID = getIntent().getIntExtra("noteID",0);
        Context context = getApplicationContext();
        //Entry n = Note.getByID(noteID, context);
/*
        LinearLayout historyScroll = findViewById(R.id.changelogLayout);
        // TODO: no no no no no no no... Note must provide the complete widget
        // TODO: in fact, Note should provide the ScrollView as well
        String logText = n.getChangeHistory();
        String[] logItems = logText.split("##");

        if (logItems.length > 0) {
            for (String s : logItems) {
                TextView t = new TextView(this);
                t.setText(s);
                historyScroll.addView(t);
            }
        }
*/
    }
}
