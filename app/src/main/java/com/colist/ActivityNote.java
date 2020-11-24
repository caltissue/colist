package com.colist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.colist.Entry.Entry;
import com.colist.Entry.Note;

public class ActivityNote extends AppCompatActivity {

    private int id;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        id = getIntent().getIntExtra("id",0);
        Context context = getApplicationContext();
        View v = EntryManager.getEntry(id).getUI().getView(context);

        LinearLayout cont = findViewById(R.id.viewScreenContainer);
        cont.addView(v, 1);
    }

    public void onEdit(View v) {
        Intent intent = new Intent(getApplicationContext(), ActivityNoteEdit.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, 2);
    }

    public void onChangeClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), ActivityChangelog.class);
        intent.putExtra("id", id);
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
