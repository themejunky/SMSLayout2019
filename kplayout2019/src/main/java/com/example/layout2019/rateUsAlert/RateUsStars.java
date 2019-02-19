package com.example.layout2019.rateUsAlert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.kplayout2019.R;
import com.theme.junky.pushnotificationlib.ManagerPush;


public class RateUsStars {

    private static RateUsStars INSTANCE = null;
    private Activity mContext;
    private View mDialogView;
    private AlertDialog mAlert;
    private int mCurrentRateStarsNo = 0;

    public static RateUsStars getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RateUsStars();
        }
        return(INSTANCE);
    }

    public RateUsStars() {

    }

    public void showRate(Activity nContext) {

        mContext = nContext;

        AlertDialog.Builder mDialog = new AlertDialog.Builder(nContext, R.style.AlertDialogTheme);

        LayoutInflater inflater = nContext.getLayoutInflater();
        mDialogView = inflater.inflate(R.layout.dialog_rate_us, null);

        mDialogView.findViewById(R.id.mStar1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWasClicked(v);
            }
        });
        mDialogView.findViewById(R.id.mStar2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWasClicked(v);
            }
        });
        mDialogView.findViewById(R.id.mStar3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWasClicked(v);
            }
        });
        mDialogView.findViewById(R.id.mStar4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWasClicked(v);
            }
        });
        mDialogView.findViewById(R.id.mStar5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWasClicked(v);
            }
        });

        mDialogView.findViewById(R.id.mLate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog(v);
            }
        });
        mDialogView.findViewById(R.id.mRate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rate(v);
            }
        });

        mDialog.setView(mDialogView);
        mDialog.setCancelable(false);

        mAlert = mDialog.create();

        if (!mAlert.isShowing()) {
            mAlert.show();
        }
    }

    private void rate(View v) {

        if (mCurrentRateStarsNo==0) {
            Toast.makeText(mContext,"First please vote",Toast.LENGTH_LONG).show();
        } else if (mCurrentRateStarsNo==5) {
            goRate();
            mAlert.dismiss();
        } else {
            mAlert.dismiss();
            new RateUsWhy().getInstance().showRateWhy(mContext);
        }
    }


    private void closeDialog(View v) {
        new ManagerPush().setPushNotification(true,mContext.getResources().getString(R.string.text_push_notification_title),mContext.getResources().getString(R.string.text_push_notification_subtitle),R.mipmap.ic_launcher,
                mContext.getResources().getInteger(R.integer.time_rate_notification1), mContext.getResources().getInteger(R.integer.time_rate_notification2),mContext.getResources().getInteger(R.integer.time_rate_notification3),"testPush");
        mAlert.dismiss();
    }

    private void startWasClicked(View v) {
        setStars(mCurrentRateStarsNo = Integer.parseInt(v.getTag().toString()));
    }

    private void setStars(final int nCurrentStar) {

        new Thread() {
            public void run() {
                for (int i = 5; i>nCurrentStar; i--) {
                    try {
                        final int finalI = i;
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((ImageView) mDialogView.findViewWithTag(String.valueOf(finalI))).setImageResource(R.drawable.ic_grey_star);
                            }
                        });
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 1; i<=nCurrentStar; i++) {
                    try {
                        final int finalI = i;
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((ImageView) mDialogView.findViewWithTag(String.valueOf(finalI))).setImageResource(R.drawable.ic_gold_star);
                            }
                        });
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void goRate() {
        Uri uri = Uri.parse("market://details?id=" + mContext.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            mContext.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + mContext.getPackageName())));
        }
    }
}
