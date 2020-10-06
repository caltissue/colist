package com.colist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class ActivityNoteEdit extends AppCompatActivity {

    public void onSave(View v) throws JSONException {
        TextView title = findViewById(R.id.titleTextEdit);
        TextView note = findViewById(R.id.noteTextEdit);
/*
        try {
            JSONObject noteObject = new JSONObject();
            noteObject.put("title", title.getText().toString());
            noteObject.put("content", note.getText().toString());
            String noteString = noteObject.toString();

            Context context = getApplicationContext();
            File f = new File(context.getFilesDir(), "notes");
            FileWriter writer = new FileWriter(f, true);

            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(noteString);
            bufferedWriter.close();
        }
        catch (Exception e) {
            // TODO: handle
        }
*/
        finish();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        TextView title = findViewById(R.id.titleTextEdit);
        title.setText(getIntent().getStringExtra("title"));

        TextView note = findViewById(R.id.noteTextEdit);
        note.setText(getIntent().getStringExtra("content"));
    }
}
