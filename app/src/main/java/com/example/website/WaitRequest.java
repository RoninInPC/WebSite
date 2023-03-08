package com.example.website;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WaitRequest extends BroadcastReceiver {

    public void SendIntent(Context context, String Title){
        Intent sendIntent = new Intent();
        sendIntent.setAction("ru.alexanderklimov.action.TITLE");
        sendIntent.putExtra("TITLE_NO_THANKS", Title);
        sendIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        context.sendBroadcast(sendIntent);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getStringExtra("TITLE_PLEASE").equals("PLEASE_I_NEED_TITLE")) {
            String Title = intent.getStringExtra("Title");
            SendIntent(context, Title);
        }
    }
}