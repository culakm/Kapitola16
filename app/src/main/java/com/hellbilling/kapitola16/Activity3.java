package com.hellbilling.kapitola16;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Activity3 extends Activity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_activity3);
    }

    public void showAlert(View view) {
        new AlertDialog.Builder(this)
                .setTitle("MessageDemo")
                .setMessage("Let's raise a toast!")
                .setNeutralButton("Here, here Neutral!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {
                        Toast
                                .makeText(Activity3.this, "<clink, clink>",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .setPositiveButton("Here, here positive!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int sumthin) {
                        Toast mojToast = new Toast(Activity3.this);
                        final View addView=getLayoutInflater().inflate(R.layout.toast, null);
                        mojToast.setView(addView);
                        mojToast.setDuration(Toast.LENGTH_LONG);
                        mojToast.show();

                    }
                })
                .show();
    }
}