package com.example.layout2019.screen.moreapps

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.layout2019.model.AdsItem
import com.example.layout2019.screen.moreapps.repository.RepositoryMoreApps

class MoreAppsViewModel  : ViewModel() {

    val repository = RepositoryMoreApps(this)
    var moreAppsList :MutableList<AdsItem> = arrayListOf()
    val triggerMoreApps = MutableLiveData<Boolean>()
    val isInternet =  MutableLiveData<Boolean>()

    fun getMoreApps(){
        repository.getMoreApps()
    }
}