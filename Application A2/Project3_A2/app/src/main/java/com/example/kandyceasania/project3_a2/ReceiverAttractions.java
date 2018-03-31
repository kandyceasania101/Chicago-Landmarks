package com.example.kandyceasania.project3_a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*
Attractions Receiver
*/

public class ReceiverAttractions extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AttractionsActivity.class);
        context.startActivity(i);
    }


}
