package com.example.pbetkows.wms.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.pbetkows.wms.R;

public class Alerts {

    public static void createAlert(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setPositiveButton("Save", (dialog, id) -> {
            // User clicked OK button
        });
        builder.setNegativeButton("Cancel", (dialog, id) -> {
            // User cancelled the dialog
        });
        builder.setTitle("Confirm");

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


