package com.example.word_lookup_firthowsa_abdikadir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<String> kiswahili_words, english_word;
    private Button exit_button;
    private Button translate_button;
    private EditText inserted_word;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kiswahili_words = new ArrayList<>();
        english_word    = new ArrayList<>();

        exit_button      = findViewById(R.id.exit_btn);
        translate_button = findViewById(R.id.translate_btn);
        inserted_word = findViewById(R.id.inserted_word);
        message       = findViewById(R.id.message);

        loadDatabase();

        translate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateText();
            }
        });
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

    }

    private void loadDatabase(){
        InputStream inputStream = getResources().openRawResource(R.raw.database);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            while ((line = reader.readLine()) != null){
                try {
                    String[] words = line.split(",");

                    if (words.length == 2) {
                        kiswahili_words.add(words[0]);
                        english_word.add(words[1]);
                    }


                }catch (Exception e){
                    Log.e("Error", e.toString());
                }
            }

        }catch (IOException io){
            Log.e("Error", io.toString());
        }
    }

    private void translateText(){

        String word =  inserted_word.getText().toString().trim();

        String translation_message = "";

        ArrayList<String> words = new ArrayList<>();

        if (english_word.contains(word)){

            translation_message ="Translations of the word "+word;

            for (int i = 0; i < english_word.size(); i++) {
                if (english_word.get(i).equals(word))
                    words.add(kiswahili_words.get(i));

            }
        }else {
            translation_message = "word not in dictionary";
        }

        message.setText(translation_message);

        CustomAdapter adapter = new CustomAdapter(words, this);
        RecyclerView view = findViewById(R.id.recycler_view);
        view.setAdapter(adapter);
        view.setLayoutManager(new LinearLayoutManager(this));

    }
}