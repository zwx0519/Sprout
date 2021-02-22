package com.sprout.adapter.oasis.home.local

import android.content.Context
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.kotlinbase.bean.issue.tag.TrendsData
import com.example.kotlinbase.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.sprout.BR
import com.sprout.R

class LocalInfoAdapter(
    context: Context,
    list:List<TrendsData>,
    layouts: SparseArray<Int>,
    click: IItemClick<TrendsData>
): BaseAdapter<TrendsData>(context,list,layouts,click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_localinfo_item
    }

    override fun bindData(binding: ViewDataBinding, data: TrendsData, layId: Int) {
        binding.setVariable(BR.trendsClick,itemClick)
        var img = binding.root.findViewById<ImageView>(R.id.img_trends)//显示图片
        Glide.with(context).load(data.url).into(img)

        var imgType = binding.root.findViewById<ImageView>(R.id.img_type)//找到显示图标
        imgType.visibility = View.GONE
        //如果是图片
        if(data.url.endsWith(".png") || data.url.endsWith(".jpg") || data.url.endsWith(".gif")){
            if(data.res.size > 1){
                imgType.setImageResource(R.mipmap.icon_photos)//如果图片大于1张，则有一个折叠显示图片的图标
                imgType.visibility = View.VISIBLE
            }
        }else if(data.url.endsWith(".mp4")){//如果是视频的
            imgType.setImageResource(R.mipmap.icon_video)//显示视频图标
            imgType.visibility = View.VISIBLE
        }
    }

}