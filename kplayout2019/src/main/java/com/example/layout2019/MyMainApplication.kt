package com.theme.junky.themeskotlin

import android.app.Application
import android.preference.PreferenceManager
import android.util.Log
import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents

var packageNameApp=""
var redirectGP = ""
class MainApplication: Application() {


    lateinit var MGAE :Module_GoogleAnalyticsEvents
    override fun onCreate() {
        super.onCreate()
        packageNameApp= packageName
        MGAE= Module_GoogleAnalyticsEvents.getInstance(this,"UA-100146217-1")//Themes for Android
    }

}