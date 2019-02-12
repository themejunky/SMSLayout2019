package com.example.layout2019.utils

import android.app.Activity
import android.util.Log
import com.appnext.ads.interstitial.Interstitial
import com.example.layout2019.ads.Loading
import com.example.layout2019.ads.ManagerAdmob
import com.example.layout2019.ads.ManagerAppNext
import com.google.android.gms.ads.InterstitialAd

open class ApplyingTheme : Loading.LoadingInterface, ManagerAdmob.ManagerAdmobInterface,  ManagerAppNext.ManagerAppNextInterface {

    private var idAppnext = ""
    private var idAdmob = ""
    private var nameLog: String = ""
    open fun loadingFirstAdManager(nContext: Activity,mIdAdmob:String,mIdAppnext:String,mNameLog:String) {
        idAdmob = mIdAdmob
        idAppnext = mIdAppnext
        nameLog = mNameLog
        Loading.getInstance().init(nContext,this)
    }

     /***************FIRST MANAGER********************/

    override fun onLoadingShowing(nContext: Activity) {
        ManagerAdmob.getInstance().init(nContext,this,idAdmob,nameLog)
    }

    override fun onAdmobLoaded(nAdmobInterstitial: InterstitialAd,nContext : Activity) {
        if (nAdmobInterstitial.isLoaded) {
            Loading.getInstance().close()
            nAdmobInterstitial.show()
        } else {
            loadingSecondManager(nContext)
        }
    }

    override fun onAdmobFailed(nContext : Activity) {
        Log.d("afwef","admob - onAdmobFailed")
        loadingSecondManager(nContext)
    }

    override fun onAdmobClosed(nContext: Activity) {
        applyTheme(nContext)
    }

    /***************SECOND MANAGER********************/

    private fun loadingSecondManager(nContext : Activity) {
        ManagerAppNext.getInstance().init(nContext,this,idAppnext,nameLog)
    }

    override fun onAppNextClosed(nContext: Activity) {
        applyTheme(nContext)
    }

    override fun onAppNextLoaded(nInterstitialAd: Interstitial, nContext: Activity) {
      if(nInterstitialAd.isAdLoaded) {
          Loading.getInstance().close()
          nInterstitialAd.showAd()
      } else {
          applyTheme(nContext)
      }
    }

    override fun onAppNextFailed(nContext: Activity) {
        Loading.getInstance().close()
        applyTheme(nContext)
    }

    open fun applyTheme(nContext: Activity) {
        Tools().applyTheme(nContext)
    }

}