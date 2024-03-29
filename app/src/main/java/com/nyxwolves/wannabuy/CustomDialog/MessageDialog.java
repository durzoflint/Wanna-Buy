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

    public static int REQ_DIALOG = 2;
    public static int AD_DIALOG = 3;

    public MessageDialog(){}

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        int option = (int)getArguments().get("OPTION");

        builder.setTitle(R.string.ad_pay_title);
        if(option == REQ_DIALOG){
            builder.setMessage(R.string.req_pay_dialog_text);
        }else if(option == AD_DIALOG){
            builder.setMessage(R.string.ad_pay_dialog_text);
        }

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CustomDialogListener  listener =  (CustomDialogListener) getActivity();
                listener.onOkButtonClicked();
                dialog.cancel();
            }
        });
        return builder.create();
    }

    public interface CustomDialogListener {
        void onOkButtonClicked();
    }
}
