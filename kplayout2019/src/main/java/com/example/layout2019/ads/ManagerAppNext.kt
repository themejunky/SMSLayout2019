package com.example.layout2019.ads

import android.app.Activity
import android.util.Log
import com.appnext.ads.interstitial.Interstitial
import com.appnext.core.callbacks.*

class ManagerAppNext private constructor() : OnAdClicked, OnAdOpened {

    private var nameLog = ""

    interface ManagerAppNextInterface {
        fun onAppNextLoaded(nInterstitialAd : Interstitial,nContext : Activity )
        fun onAppNextClosed(nContext: Activity)
        fun onAppNextFailed(nContext: Activity)
    }

    companion object {
        private val mInstance: ManagerAppNext = ManagerAppNext()
        @Synchronized
        fun getInstance(): ManagerAppNext {
            return mInstance
        }
    }


    fun init(nContext: Activity, nListener : ManagerAppNextInterface, idAppnext:String, nameLog:String) {
        this.nameLog = nameLog
        Log.d("afwef","Appnext - init")
        /*"3cd8839d-7e84-4c69-9899-d0d17485fac8"*/
        val mInterstitialAd = Interstitial(nContext, idAppnext)
        Log.d("afwef","Appnext - init: idAppnext: " +idAppnext)
        if(idAppnext.isEmpty()){
            Log.d("afwef","Appnext -1 isEmpty")
            nListener.onAppNextFailed(nContext)
            Log.d("afwef","Appnext -2 isEmpty")
        }else{
            Log.d("afwef","Appnext - isNotEmpty")
            mInterstitialAd.loadAd()
        }

        mInterstitialAd.isBackButtonCanClose = true
        mInterstitialAd.onAdClosedCallback = OnAdClosed {
            Log.d(nameLog,"Appnext - OnAdClosed")
            nListener.onAppNextClosed(nContext)
        }
        mInterstitialAd.onAdErrorCallback = OnAdError {
            Log.d(nameLog,"Appnext - OnAdError: ${it}")
            nListener.onAppNextFailed(nContext)
        }
        mInterstitialAd.onAdLoadedCallback = OnAdLoaded {
            Log.d(nameLog,"Appnext - OnAdLoaded")
            nListener.onAppNextLoaded(mInterstitialAd,nContext)
        }
        mInterstitialAd.onAdOpenedCallback = this
    }
    override fun adClicked() {
        Log.d(nameLog,"Appnext - OnAdLoaded")
    }

    override fun adOpened() {
        Log.d(nameLog,"Appnext - OnAdLoaded")
    }
}