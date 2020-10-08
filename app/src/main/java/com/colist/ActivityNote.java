package com.colist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityNote extends AppCompatActivity {

    private int noteID;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteID = getIntent().getIntExtra("id",0);
        Context context = getApplicationContext();
        Note n = Note.getByID(noteID, context);

        TextView title = findViewById(R.id.titleTextView);
        title.setText(n.getTitle());

        TextView note = findViewById(R.id.noteTextView);
        note.setText(n.getContent());
    }

    public void onEdit(View v) {
        Intent intent = new Intent(getApplicationContext(), ActivityNoteEdit.class);
        intent.putExtra("noteID", noteID);
        startActivityForResult(intent, 2);
    }

    public void onChangeClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), ActivityChangelog.class);
        intent.putExtra("noteID", noteID);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
        startActivity(getIntent());
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
