package com.mirea.kotov.lifedata

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class TimeController: ViewModel() {
    companion object{
        private val data = MutableLiveData<Long>()

        fun getTime(): LiveData<Long>{
            data.value = Date().time
            return data
        }

        fun setTime(){
            data.value = Date().time
        }

        private val getStringTime: LiveData<String?> = Transformations.map(data,
            Function<Long?, String?> {
                val calendar = Calendar.getInstance()
                val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                df.format(calendar.time)
            })

        fun getDate(): LiveData<String?>{
            return getStringTime
        }
    }
}