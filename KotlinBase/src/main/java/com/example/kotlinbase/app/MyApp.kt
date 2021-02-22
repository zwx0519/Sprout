package com.shop.app

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.basemvvm.utils.MyMmkv
import iknow.android.utils.BaseUtils
import java.util.*

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        //视频编辑
        BaseUtils.init(this)
        initFFmpegBinary(this)
        instance = this
        app = this
        map = HashMap()
        MyMmkv.initMMKV()
    }


    //ffmpeg库的初始化
    private fun initFFmpegBinary(context: Context) {
//        if (!FFmpeg.getInstance(context).isSupported) {
//            Log.e("ZApplication", "Android cup arch not supported!")
//        }
    }

    companion object{
        var instance: MyApp? = null
        @JvmField
        var app: MyApp? = null
        @JvmStatic
        var map: HashMap<String, Any>? = null
    }

}