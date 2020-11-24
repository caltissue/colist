package com.colist;

import com.colist.Entry.*;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        ReadWriter.setFilepath(context.getFilesDir().toString());

        LinearLayout previewHolder = new LinearLayout(context);
        previewHolder.setOrientation(LinearLayout.VERTICAL);

        ArrayList<View> previews = EntryManager.getAllPreviews(context);
        for (View v : previews) {
            v.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ActivityNote.class);
                    intent.putExtra("id", v.getId());
                    startActivityForResult(intent, 10);
                }
            });

            previewHolder.addView(v);
        }

        ScrollView sv = new ScrollView(context);
        sv.addView(previewHolder);

        LinearLayout main = findViewById(R.id.mainActivityLinearLayout);
        main.addView(sv, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
        startActivity(getIntent());
    }

    public void onNewClick(View v) {

    }

}