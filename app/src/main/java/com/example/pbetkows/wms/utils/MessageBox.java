package com.example.pbetkows.wms.utils;

import android.content.Context;
import android.widget.Toast;

public class MessageBox {

    public static void Show(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
