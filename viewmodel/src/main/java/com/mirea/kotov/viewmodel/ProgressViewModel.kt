package com.mirea.kotov.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.os.Handler
import android.os.Looper

class ProgressViewModel: ViewModel() {
    companion object{
        private val isShowProgress = MutableLiveData<Boolean>()
    }
    
    fun showProgress(){
        isShowProgress.postValue(true)
        Handler(Looper.myLooper()!!).postDelayed({
            isShowProgress.postValue(false)
        }, 10000)
    }

    fun getProgressState(): MutableLiveData<Boolean>{
        return isShowProgress
    }
    
}