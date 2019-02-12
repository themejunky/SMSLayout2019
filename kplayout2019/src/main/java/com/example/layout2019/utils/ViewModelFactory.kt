package com.theme.junky.themeskotlin.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.layout2019.screen.mainscreen.MainScreenViewModel
import com.example.layout2019.screen.moreapps.MoreAppsViewModel
import com.example.layout2019.screen.wallpapers.WallpaperViewModel

class ViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainScreenViewModel::class.java)) {
            return MainScreenViewModel() as T
        }else if (modelClass.isAssignableFrom(WallpaperViewModel::class.java)) {
            return WallpaperViewModel() as T
        }else if (modelClass.isAssignableFrom(MoreAppsViewModel::class.java)) {
            return MoreAppsViewModel() as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class")
    }



}
