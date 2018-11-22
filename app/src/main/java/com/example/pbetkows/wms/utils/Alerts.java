package com.example.pbetkows.wms.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

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
        builder.setMultiChoiceItems(R.array.izolator,null, (dialog, which, isChecked) -> {

        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void errorAlert(Activity activity, String errorMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Error ");
        builder.setMessage(errorMessage);
        builder.setIcon(R.drawable.ic_error_black_24dp);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}


