package com.example.layout2019.screen.wallpapers

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.layout2019.model.AdsItem
import com.example.layout2019.screen.wallpapers.repository.RepositoryWallpapers

class WallpaperViewModel: ViewModel() {
    val repository = RepositoryWallpapers(this)
    var wallpapersList :MutableList<AdsItem> = arrayListOf()
    val triggerWallapper = MutableLiveData<Boolean>()
    val isInternet =  MutableLiveData<Boolean>()
    fun getWallpapers(){
        repository.getWallpapers()
    }
}