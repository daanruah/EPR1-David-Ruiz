package com.example.davidandres.capitulo92a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

public class NoteActivity extends Activity{

    private Button notesButton;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notas);

        linearLayout = (LinearLayout) findViewById(R.id.layoutNotes);
        notesButton = (Button) findViewById(R.id.buttonCreate);
        notesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SaveNoteActivity.class);
                startActivity(i);
            }
        });
    }

    private void readAndDisplayNotes(){
        File dirFiles = getFilesDir();
        for (String strFile : dirFiles.list()){
            TextView mTextView = new TextView(this);
            mTextView.setText(strFile);
            mTextView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

            linearLayout.addView(mTextView);
        }
    }
}
