package com.japsystem.bolivarsoberano;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AlertDialog;

/**
 * @author Adrian De León
 * @version 3/23/18.
 */

public class AboutDialog {

    private final AlertDialog.Builder mDialog;

    public AboutDialog(Context context) {

        String message = "Version " +
                context.getString(R.string.app_version_name) +
                " (" + context.getString(R.string.app_version_code) + ")\n";
        message += "©2018 - JAP System C.A." + "\n\n";

        message += "Android " + Build.VERSION.RELEASE + " API Level " + Build.VERSION.SDK_INT + "\n";
        message += "Incremental " + Build.VERSION.INCREMENTAL + "\n";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            message += "Security Patch " + Build.VERSION.SECURITY_PATCH + "\n\n";
        }

        message += "\n\nhttp://japsystem.com";

        mDialog = new AlertDialog.Builder(context);

        mDialog.setTitle(context.getString(R.string.app_name))
                .setMessage(message)
                .setPositiveButton(context.getString(android.R.string.ok), null);
        mDialog.create();
    }

    public void show() {
        mDialog.show();
    }
}

