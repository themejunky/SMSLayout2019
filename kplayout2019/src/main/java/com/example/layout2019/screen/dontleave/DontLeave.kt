package com.example.layout2019.screen.dontleave

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kplayout2019.R
import com.theme.junky.themeskotlin.MyMainApplication
import kotlinx.android.synthetic.main.activity_dont_leave.*

class DontLeave : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dont_leave)

        buttonDownload.setOnClickListener {
            (application as MyMainApplication).MGAE.getEvents(getString(R.string.layout_event),"Click on download don't leave","Click on Button")
            startActivity(Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.smsplus.app"))) }
    }
}
