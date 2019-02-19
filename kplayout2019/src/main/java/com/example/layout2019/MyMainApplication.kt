package com.theme.junky.themeskotlin

import android.app.Application
import android.preference.PreferenceManager
import android.util.Log
import androidx.work.*
import com.example.layout2019.worker.rateUs.RateUsWorker
import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents
import java.util.concurrent.TimeUnit

var packageNameApp=""
var redirectGP = ""
class MyMainApplication: Application() {


    lateinit var MGAE :Module_GoogleAnalyticsEvents
    override fun onCreate() {
        super.onCreate()
        packageNameApp= packageName
        MGAE= Module_GoogleAnalyticsEvents.getInstance(this,"UA-100146217-1")//Themes for Android
        setOurRateUs()
    }


    /**
     * Set's our vip progression promotions;
     * Added a double check fix by using shared preferences ;
     * Although should be enough by using "ExistingWorkPolicy.KEEP" with "OneTimeWorkRequest"
     */



    private fun setOurRateUs() {
        val mPrefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        if (!mPrefs.getBoolean("rate_us_set", false)) {
            val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

            WorkManager.getInstance().beginUniqueWork("rate_us", ExistingWorkPolicy.KEEP, OneTimeWorkRequest.Builder(RateUsWorker::class.java).setConstraints(constraints).addTag("rate_us").setInitialDelay(1, TimeUnit.MINUTES).build()).enqueue()

            val mEditor = mPrefs.edit()
            mEditor.putBoolean("rate_us_set", true).apply()
        }
    }

}