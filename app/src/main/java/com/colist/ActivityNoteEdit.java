package com.colist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityNoteEdit extends AppCompatActivity {

    private int noteID;

    public void onSave(View v) {
        String title = ((TextView)findViewById(R.id.titleTextEdit)).getText().toString();
        String content = ((TextView)findViewById(R.id.noteTextEdit)).getText().toString();

        Context context = getApplicationContext();
        Note noteToSave = Note.getByID(noteID, context);
        noteToSave = noteToSave.UpdateTitle(title).UpdateContent(content);
        noteToSave.save(context);

        setResult(Activity.RESULT_OK);
        finish();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        noteID = getIntent().getIntExtra("noteID",0);

        Context context = getApplicationContext();
        Note n = Note.getByID(noteID, context);

        TextView title = findViewById(R.id.titleTextEdit);
        title.setText(n.getTitle());

        TextView note = findViewById(R.id.noteTextEdit);
        note.setText(n.getContent());
    }

    @Override
    public void onBackPressed() {
        TextView title = findViewById(R.id.titleTextEdit);
        TextView note = findViewById(R.id.noteTextEdit);

        if (title.getText().toString().trim().length() == 0 &&
            note.getText().toString().trim().length() == 0) {
            Context context = getApplicationContext();
            Note n = Note.getByID(noteID, context);
            n.delete(context);
        }

        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
