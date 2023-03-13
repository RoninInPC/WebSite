package com.example.website;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class WaitRequest extends BroadcastReceiver {

    public void SendIntent(Context context, String Title){
        Intent sendIntent = new Intent();
        sendIntent.setAction("com.example.SendRequest");
        sendIntent.setData(Uri.parse("nothanks:"+Title));
        //sendIntent.putExtra("TITLE_NO_THANKS", Title);
        //sendIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.sendBroadcast(sendIntent,"andro.jf.mysecondpermission");
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        //String Title = intent.getStringExtra("Title");
        SendIntent(context, "Title");
        Toast.makeText(context, "Пришёл запрос на Title = " +
                        intent.getStringExtra("TITLE_NO_THANKS"),
                Toast.LENGTH_LONG).show();
    }
}