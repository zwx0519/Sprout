package com.shop.app

import android.os.Environment
import com.example.basemvvm.utils.SpUtils
import java.io.File

class Constants {

    companion object{
        
        //基础地址
        val BASE_URL:String = "http://sprout.cdwan.cn/api/"

        //网络缓存的地址
     //   val PATH_DATA = MyApp.app!!.cacheDir.absolutePath + File.separator + "data"
        val token_key = "token_key"
        val token:String = "token"
    }

}