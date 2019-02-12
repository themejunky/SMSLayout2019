package com.example.layout2019.dialogs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kplayout2019.R
import com.theme.junky.themeskotlin.MainApplication
import kotlinx.android.synthetic.main.activity_no_mother_installed.*

class NoMotherInstalled : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_mother_installed)

        installButton.setOnClickListener {
            (application as MainApplication).MGAE.getEvents(getString(R.string.layout_event),"Click on download no mother","Click on Button")
           val intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.data = Uri.parse("https://play.google.com/store/apps/details?id=com.smsplus.app")
            startActivity(intent)
            onBackPressed()
        }
    }
}
