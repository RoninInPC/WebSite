package com.example.website;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TitleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_title);
        String Title = getIntent().getStringExtra("Title");
        setTitle(Title);
    }
    public void FinishThisActivity(View view){
        this.finish();
    }
}
