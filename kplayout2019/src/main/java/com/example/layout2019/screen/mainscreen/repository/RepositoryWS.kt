package com.example.layout2019.screen.mainscreen.repository

import com.example.layout2019.model.AdsReceived
import com.example.layout2019.model.ThemeItem
import com.example.layout2019.retrofit.RetrofitService
import com.theme.junky.themeskotlin.packageNameApp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryWS {

    fun getAdsConfig(): Observable<AdsReceived> {

        return RetrofitService().getInstance().interfaces.getAdsConfig(packageNameApp,"regular")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getThemeConfig(): Observable<List<ThemeItem>> {
        return RetrofitService().getInstance().interfaces.getThemeConfig(packageNameApp)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }


}