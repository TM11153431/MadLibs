package com.example.timme.timmeijer_pset2;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class InputActivity extends AppCompatActivity {

    public static final String key2 = "storyFinished";

    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        if (story == null){
            Intent intent = getIntent();
            String chosenStory = intent.getStringExtra(StartActivity.key);



            // get context, open AM and then inputstream
            Context context = getApplicationContext();
            AssetManager am = context.getAssets();
            try {
                InputStream is = am.open(chosenStory);
                story = new Story(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        EditText editText = findViewById(R.id.editText);
        editText.setHint(story.getNextPlaceholder());

        TextView textView = findViewById(R.id.textView2);
        textView.setText(Integer.toString(story.getPlaceholderRemainingCount()) + " word remaining");


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("story", story);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        story = (Story) savedInstanceState.getSerializable("story");

        EditText editText = findViewById(R.id.editText);
        editText.setHint(story.getNextPlaceholder());

        TextView textView = findViewById(R.id.textView2);
        textView.setText(Integer.toString(story.getPlaceholderRemainingCount()) + " word remaining");

    }

    public void enterWord(View view) {
        // add text
        EditText editText = findViewById(R.id.editText);
        String word = editText.getText().toString();
        Log.d("word", word);
        if (!word.isEmpty()) {
            story.fillInPlaceholder(word);
            if (story.isFilledIn()) {
                Intent intenteroni = new Intent(this, EndActivity.class);
                intenteroni.putExtra(key2, story.toString());
                startActivity(intenteroni);
            }
            editText.setText("");
            editText.setHint(story.getNextPlaceholder());

            // change text for how many words left
            TextView textView = findViewById(R.id.textView2);
            textView.setText(Integer.toString(story.getPlaceholderRemainingCount()) + " word remaining");
        }
    }
}
