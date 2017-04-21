package com.example.davidandres.capitulo92a;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveNoteActivity extends Activity{

    private EditText editText;
    private Button saveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_note);
        saveNote = (Button) findViewById(R.id.saveNote);
        editText = (EditText) findViewById(R.id.editText);

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long unixTime = System.currentTimeMillis() / 1000L;
                String nameFile = String.valueOf(unixTime);
                saveIntoFile(nameFile);
            }
        });
    }

    private void saveIntoFile( String namefile){
        try {
            FileOutputStream outputStream = openFileOutput(namefile, Context.MODE_PRIVATE);
            outputStream.write(editText.getText().toString().getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
