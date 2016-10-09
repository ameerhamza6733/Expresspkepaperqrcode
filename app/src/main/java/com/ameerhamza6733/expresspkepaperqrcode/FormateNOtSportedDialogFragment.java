package com.ameerhamza6733.expresspkepaperqrcode;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by AmeerHamza on 10/9/2016.
 */

public class FormateNOtSportedDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("QR Code Format Not Supported")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        getActivity().finish();
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}

