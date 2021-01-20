package com.shop.app

import android.os.Environment
import com.example.basemvvm.utils.SpUtils
import java.io.File

class Constants {

    companion object{
        
        //基础地址
        val BASE_URL:String = "https://cdplay.cn/api/"

        //网络缓存的地址
        val PATH_DATA = MyApp.app!!.cacheDir.absolutePath + File.separator + "data"
        //拼接一个地址
        @JvmField
        val PATH_IMGS = PATH_DATA + "/tp/imgs"
        @JvmField
        val APK_PATH = Environment.getStorageDirectory().toString() + File.separator + "apk/"

    }

}