package com.example.layout2019.screen

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.kplayout2019.R
import com.example.layout2019.utils.Tools
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.theme.junky.themeskotlin.MyMainApplication


class IntroPrivacyPolicy : AppCompatActivity(), View.OnClickListener {
    lateinit var prefs: SharedPreferences
    var nrOfEntries = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_privacy_policy)
        init()
        FirebaseApp.initializeApp(this)

        FirebaseMessaging.getInstance().subscribeToTopic("Sms_ThemesForAndroid")
         prefs = getSharedPreferences("my_app", Context.MODE_PRIVATE)

       if(prefs.getBoolean("first",false)){
           Tools().redirectLayout(this)
           nrOfEntries =  prefs.getInt("nrOfEntries",0)
           nrOfEntries++
           (application as MyMainApplication).MGAE.getEvents(getString(R.string.layout_event),"Number of Entries","Number of Entries: ${nrOfEntries}")
           prefs.edit().putInt("nrOfEntries", nrOfEntries).apply()
       }
    }

    private fun init() {
        supportActionBar?.hide()
        findViewById<View>(R.id.nView5).setOnClickListener(this)
        findViewById<View>(R.id.nView6).setOnClickListener(this)
        findViewById<View>(R.id.nView7).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.nView5 -> {
                Tools().redirectLayout(this)
                nrOfEntries++
                (application as MyMainApplication).MGAE.getEvents(getString(R.string.layout_event),"Number of Entries","Number of Entries: ${nrOfEntries}")
                prefs.edit().putBoolean("first", true).apply()
                prefs.edit().putInt("nrOfEntries", nrOfEntries).apply()


            }
            R.id.nView6 -> {
                Tools().showPrivacyPolicy(this)
                (application as MyMainApplication).MGAE.getEvents(getString(R.string.layout_event),"Click on Privacy Policy","Privacy Policy")
            }
            R.id.nView7 -> {
                Tools().showPrivacyPolicy(this)
                (application as MyMainApplication).MGAE.getEvents(getString(R.string.layout_event),"Click on Privacy Policy","Privacy Policy")
            }
        }
    }
}
