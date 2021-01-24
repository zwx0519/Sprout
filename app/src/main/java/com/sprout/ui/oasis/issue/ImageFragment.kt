package com.sprout.ui.oasis.issue

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kotlinbase.bean.issue.ImgData
import com.sprout.R
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment (var index:Int, var path:String, var tags:MutableList<ImgData.Tag>):Fragment(){

    //伴生对象
    companion object{
        fun instance(i:Int,path:String,tagList:MutableList<ImgData.Tag>):ImageFragment{
            return ImageFragment(i,path,tagList)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(path.isNotEmpty()){
            //给图片赋值
            iv_img.setImageURI(Uri.parse(path))
        }
    }

    //TODO 添加标签到界面
    fun addTagsToView(type:Int,id:Int,name:String){
        var view = layoutInflater.inflate(R.layout.layout_tag_item,null)
        var imgTag = view.findViewById<ImageView>(R.id.iv_tag_img)
        var txtName = view.findViewById<TextView>(R.id.tv_tag_name)
        txtName.setText(name)
        when(type){
            1-> imgTag.setImageResource(R.mipmap.tag_icon_w_brand)
            2-> imgTag.setImageResource(R.mipmap.tag_icon_w_commodity)
        }
        var param = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        param.setMargins(0,0,0,0) //控制组件的坐标位置
        view.setOnTouchListener(layout_tags)
        var tag = ImgData.Tag(0f,0f,type,name,0.0, 0.0)
        tags.add(tag)
        view.tag = tag
        layout_tags.addView(view,param)
    }
}