package com.themejunkylayout2019

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.layout2019.ManagerLayout


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)
        ManagerLayout.getInstance().redirect(this,Main2Activity::class.java)

    }
}
