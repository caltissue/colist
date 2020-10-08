package com.colist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout noteList = findViewById(R.id.notelist);

        ArrayList<Note> notes = Note.getAll(getApplicationContext());

        for (Note n : notes)  {
            TextView newView = new TextView(this);
            String title = n.getTitle();
            String content = n.getContent();
            String[] contentLines = content.split("\n");
            content = contentLines.length > 0 ? contentLines[0] : content;

            int id = n.getID();
            newView.setId(id);

            final SpannableString full = new SpannableString(title + "\n" + content);

            full.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, title.length(), 0);
            full.setSpan(new RelativeSizeSpan(2f), 0, title.length(), 0);
            full.setSpan(new StyleSpan(Typeface.BOLD), 0, title.length(), 0);

            full.setSpan(new ForegroundColorSpan(Color.LTGRAY), title.length(), full.length(), 0);
            full.setSpan(new StyleSpan(Typeface.ITALIC), title.length(), full.length(), 0);

            newView.setText(full);
            newView.setLayoutParams(
                    new ViewGroup.LayoutParams(
                            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            newView.setLines(2);
            newView.setLineSpacing(10,1);
            newView.setPadding(50,40,50,0);
            newView.setEllipsize(TextUtils.TruncateAt.END);

            newView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ActivityNote.class);
                    intent.putExtra("id", v.getId());
                    startActivityForResult(intent, 1);
                }
            });

            noteList.addView(newView);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
        startActivity(getIntent());
    }

    public void onNewClick(View v) {
        Context context = getApplicationContext();
        Note n = new Note("","", context);
        n.save(context);

        Intent intent = new Intent(getApplicationContext(), ActivityNoteEdit.class);
        intent.putExtra("noteID", n.getID());
        startActivityForResult(intent, 2);
    }

}