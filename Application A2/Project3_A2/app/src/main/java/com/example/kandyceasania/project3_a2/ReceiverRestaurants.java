package com.example.kandyceasania.project3_a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*
Restaurants Receiver
*/

public class ReceiverRestaurants extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, RestaurantsActivity.class);
        context.startActivity(i);
    }
}
