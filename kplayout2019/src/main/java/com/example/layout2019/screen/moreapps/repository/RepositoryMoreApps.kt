package com.example.layout2019.screen.moreapps.repository

import com.example.layout2019.model.ThemeItem
import com.example.layout2019.retrofit.RetrofitService
import com.example.layout2019.screen.moreapps.MoreAppsViewModel
import com.theme.junky.themeskotlin.packageNameApp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException

class RepositoryMoreApps(val mViewModel: MoreAppsViewModel) {

    private fun getThemeConfig(): Observable<List<ThemeItem>> {
        return RetrofitService().getInstance().interfaces.getThemeConfig(packageNameApp)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getMoreApps(): Disposable {
        return getThemeConfig().subscribe(::successeMoreApps, ::erroreMoreApps)
    }
    private fun successeMoreApps(mResult: List<ThemeItem>){

        with (mViewModel){
            isInternet.value = true
            moreAppsList.clear()
            for (mData in mResult[0].account_internal_ads){
                if(mData.text.contains("type_promo",true)){
                    moreAppsList.add(mData)
                    triggerMoreApps.value = true
                }
            }
        }


    }
    private fun erroreMoreApps(mError: Throwable){
        if (mError is IOException) {
            mViewModel.isInternet.value = false
        }
    }
}