package com.easv.myfriends.myfriends.service;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by miesz on 23.03.2018.
 */

public class MessageService {
    public static void message(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
