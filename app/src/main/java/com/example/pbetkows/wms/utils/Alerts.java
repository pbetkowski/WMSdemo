package com.example.pbetkows.wms.utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;

import com.example.pbetkows.wms.R;

public class Alerts {

    public static void createAlert(Activity activity, Runnable runnable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setPositiveButton("Save", (dialog, id) -> {
            runnable.run();
        });
        builder.setNegativeButton("Cancel", (dialog, id) -> {
            MessageBox.show(activity, "Transaction cancelled");
        });
        builder.setTitle("Confirm transaction ?");
        builder.setIcon(R.drawable.ic_save_black_24dp);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


