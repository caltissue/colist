package com.colist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityNote extends AppCompatActivity {

    public void onEdit(View v) {
        Intent intent = new Intent(getApplicationContext(), ActivityNoteEdit.class);

        TextView title = findViewById(R.id.titleTextView);
        intent.putExtra("title", title.getText().toString());

        TextView note = findViewById(R.id.noteTextView);
        intent.putExtra("content", note.getText().toString());

        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        TextView title = findViewById(R.id.titleTextView);
        title.setText(getIntent().getStringExtra("title"));

        TextView note = findViewById(R.id.noteTextView);
        note.setText(getIntent().getStringExtra("content"));
    }
}
