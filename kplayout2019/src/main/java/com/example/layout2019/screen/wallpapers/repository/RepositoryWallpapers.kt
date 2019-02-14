package com.example.layout2019.screen.wallpapers.repository

import android.util.Log
import com.example.layout2019.model.ThemeItem
import com.example.layout2019.retrofit.RetrofitService
import com.example.layout2019.screen.wallpapers.WallpaperViewModel
import com.theme.junky.themeskotlin.packageNameApp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException

class RepositoryWallpapers (val mViewModel: WallpaperViewModel){


    private fun getThemeConfig(): Observable<List<ThemeItem>> {
        return RetrofitService().getInstance().interfaces.getThemeConfig(packageNameApp)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getWallpapers(): Disposable {
        return getThemeConfig().subscribe(::successeWallpaper, ::erroreWallpaper)
    }
    private fun successeWallpaper(mResult: List<ThemeItem>){

        Log.d("asfas","successeWallpaper")
        with (mViewModel){
            wallpapersList.clear()
            mViewModel.isInternet.value = true
            for (mData in mResult[0].account_internal_ads){
                if(mData.text.contains("type_wallpaper",true)){
                    wallpapersList.add(mData)
                    triggerWallapper.value = true

                }
            }
        }


    }
    private fun erroreWallpaper(mError: Throwable){
        Log.d("asfas","erroreWallpaper: " + mError.message)
        if (mError is IOException) {
            mViewModel.isInternet.value = false
        }

    }
}