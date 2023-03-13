package com.example.website;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {

    protected String Title = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    protected String GetTitleFromWebSite(String url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        try{
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            Scanner scanner = new Scanner(in);
            String responseBody = scanner.useDelimiter("\\A").next();
            return responseBody.substring(responseBody.indexOf("<title>") + 7, responseBody.indexOf("</title>"));
        }catch(Exception exception){
            return exception.toString();
        }
    }
    public void GetWebSiteString(View view) throws IOException {
        EditText edit = findViewById(R.id.et1);
        String url = edit.getText().toString();
        Callable task = ()->{return GetTitleFromWebSite(url);};
        FutureTask<String> future = new FutureTask<>(task);
        new Thread(future).start();
        try {
            Title = future.get();
            //Intent intent = new Intent(this, WaitRequest.class);
            //intent.putExtra("Title",Title);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Intent intent = new Intent(this, TitleActivity.class);
        intent.putExtra("Title",Title);
        startActivity(intent);
    }

}