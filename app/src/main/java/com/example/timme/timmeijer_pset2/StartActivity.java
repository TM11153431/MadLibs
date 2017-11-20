package com.example.timme.timmeijer_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class StartActivity extends AppCompatActivity {

    // defining middles of story
    String[] storyChoices = {"0_simple", "1_tarzan", "2_university", "3_clothes", "4_dance"};
    String randomStory;
    public static final String key = "storyChosen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


    }

    public void startMadLibs(View view) {
        // choose random MadLibs story
        Random rand = new Random();
        int n = rand.nextInt(5);
        randomStory = "madlib" + storyChoices[n] + ".txt";

        // start next activity with story chosen
        Intent intent = new Intent(this, InputActivity.class);
        intent.putExtra("storyChosen", randomStory);
        startActivity(intent);
    }
}
