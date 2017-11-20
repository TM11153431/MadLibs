package com.example.timme.timmeijer_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Intent intent = getIntent();
        String storyDone = intent.getStringExtra("storyFinished");

        TextView textView = findViewById(R.id.textView3);
        textView.setText(storyDone);
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
