package com.example.layout2019.rateUsAlert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kplayout2019.R;
import com.theme.junky.pushnotificationlib.ManagerPush;


public class RateUsWhy {

    private static RateUsWhy INSTANCE = null;
    private Activity mContext;
    private View mDialogView;
    private AlertDialog mAlert;

    public static RateUsWhy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RateUsWhy();
        }
        return (INSTANCE);
    }

    public RateUsWhy() {

    }

    public void showRateWhy(Activity nContext) {

        mContext = nContext;

        AlertDialog.Builder mDialog = new AlertDialog.Builder(nContext, R.style.AlertDialogTheme);

        LayoutInflater inflater = nContext.getLayoutInflater();
        mDialogView = inflater.inflate(R.layout.dialog_rateus_why, null);

        mDialogView.findViewById(R.id.mLate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog(v);

            }
        });
        mDialogView.findViewById(R.id.mSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(v);
            }
        });

        mDialog.setView(mDialogView);
        mDialog.setCancelable(false);

        mAlert = mDialog.create();

        if (!mAlert.isShowing()) {
            mAlert.show();
        }
    }

    private void submit(View v) {

        EditText mFeedback = mDialogView.findViewById(R.id.mFeedback);

        if (mFeedback!=null && mFeedback.getText().toString().length()>0) {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:support@keyboard-plus.com"));
            intent.putExtra(Intent.EXTRA_EMAIL, "support@keyboard-plus.com");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback - Rate Us");
            intent.putExtra(Intent.EXTRA_TEXT, mFeedback.getText().toString());

            mContext.startActivity(Intent.createChooser(intent, "Choose an email client"));
            mAlert.dismiss();
        } else {
            Toast.makeText(mContext,"Please enter your feedback",Toast.LENGTH_LONG).show();
        }
    }


    private void closeDialog(View v) {
        new ManagerPush().setPushNotification(true,mContext.getResources().getString(R.string.text_push_notification_title),mContext.getResources().getString(R.string.text_push_notification_subtitle),R.mipmap.ic_launcher,
                 mContext.getResources().getInteger(R.integer.time_rate_notification1), mContext.getResources().getInteger(R.integer.time_rate_notification2),mContext.getResources().getInteger(R.integer.time_rate_notification3),"testPush");
        mAlert.dismiss();
    }
}