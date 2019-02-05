package com.nyxwolves.wannabuy.CustomDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;


import com.nyxwolves.wannabuy.R;
import com.nyxwolves.wannabuy.activities.ImageUpload;

public class MessageDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.ad_pay_title);
        builder.setMessage(R.string.ad_pay_dialog_text);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //startActivity(new Intent(getActivity(), ImageUpload.class));
                dialog.cancel();
            }
        });
        return builder.create();
    }
}
