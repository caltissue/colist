package com.colist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout noteList = findViewById(R.id.notelist);

        String[][] y = {
                {"note title one", "here's the text content of note 1, titled 'note title one', for the household to read etc."},
                {"my 2nd NOTE!!", "this is note 2, which I wrote AFTER note 1 for ya'll, hope you read it etc."},
                {"note example", "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x"},
                {"note example", "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x"},
                {"note example", "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x"},
                {"note example", "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x"},
                {"note example", "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x"},
                {"note example", "x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x x"}
        };
/*
        String allNotes = "";
        Context context = getApplicationContext();
        File f = new File(context.getFilesDir(), "notes");
        try {
            f.createNewFile();
            FileReader reader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                sb.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            allNotes = sb.toString();
        }
        catch (Exception e) {
            // TODO: handle IOException, SecurityException, FileNotFoundException
        }

        TextView test = new TextView(this);
        test.setText(allNotes);
        noteList.addView(test);
*/
        for (int i = 0; i < y.length; i++) {
            TextView newView = new TextView(this);
            String title = y[i][0];
            String content = y[i][1];

            final SpannableString full = new SpannableString(title + "\n" + content);

            full.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, title.length(), 0);
            full.setSpan(new RelativeSizeSpan(2f), 0, title.length(), 0);
            full.setSpan(new StyleSpan(Typeface.BOLD), 0, title.length(), 0);

            //SpannableString content = new SpannableString();
            full.setSpan(new ForegroundColorSpan(Color.LTGRAY), title.length(), full.length(), 0);
            full.setSpan(new StyleSpan(Typeface.ITALIC), title.length(), full.length(), 0);

            newView.setText(full);
            newView.setLayoutParams(
                    new ViewGroup.LayoutParams(
                            LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            newView.setId(123450 + i);

            newView.setLines(2);
            newView.setLineSpacing(10,1);
            newView.setPadding(50,40,50,0);
            newView.setEllipsize(TextUtils.TruncateAt.END);

            newView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ActivityNote.class);
                    String[] titleAndContent = ((TextView) v).getText().toString().split("\n");
                    intent.putExtra("title", titleAndContent[0]);
                    intent.putExtra("content", titleAndContent[1]);
                    startActivity(intent);
                }
            });

            noteList.addView(newView);
        }
    }

}