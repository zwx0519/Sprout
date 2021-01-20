package com.example.basemvvm.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.shop.app.Constants
import com.shop.app.MyApp

object ImageLoader {
    //url 图片地址
    fun loadImage(url: String?, img: ImageView?) {
        //用key为image的值的时候来判断当前时无图还有有图模式
        if (SpUtils.instance!!.getBoolean("image") && img != null) {
            Glide.with(MyApp.app!!).load(url).into(img)
        }
    }

    /**
     * 解析图片的路径
     * @param url
     * @return
     */
    fun splitUrl(url: String): Array<String?> {
        val arr = arrayOfNulls<String>(3)
        val end = url.lastIndexOf("/") + 1
        val baseUrl = url.substring(0, end)
        val imgName = url.substring(end, url.length)
        val path = Constants.PATH_IMGS + "/" + imgName
        arr[0] = baseUrl
        arr[1] = imgName
        arr[2] = path
        return arr
    }

    fun getIconBitmap(context: Context?, iconId: Int): Bitmap? {
        return try {
            val icon = ContextCompat.getDrawable(context!!, iconId) ?: return null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && icon is AdaptiveIconDrawable) {
                val bitmap = Bitmap.createBitmap(icon.getIntrinsicWidth(), icon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                icon.setBounds(0, 0, canvas.width, canvas.height)
                icon.draw(canvas)
                bitmap
            } else {
                (icon as BitmapDrawable).bitmap
            }
        } catch (e: Exception) {
            null
        }
    }
}