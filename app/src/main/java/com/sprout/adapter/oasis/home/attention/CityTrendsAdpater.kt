package com.sprout.adapter.oasis.home.attention

import android.content.Context
import android.util.SparseArray
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.bumptech.glide.Glide
import com.example.kotlinbase.bean.issue.tag.TrendsData
import com.example.kotlinbase.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.sprout.BR
import com.sprout.R

class CityTrendsAdpater(
    context: Context,
    list: List<TrendsData>,
    layouts: SparseArray<Int>,
    click: IItemClick<TrendsData>
): BaseAdapter<TrendsData>(context,list,layouts,click) {
    override fun layoutId(position: Int): Int {
        return R.layout.layout_city_trends_item
    }

    override fun bindData(binding: ViewDataBinding, data: TrendsData, layId: Int) {
        binding.setVariable(BR.cityClick,itemClick)
        var imgCity = binding.root.findViewById<ImageView>(R.id.img_city)
        Glide.with(context).load(data.url).into(imgCity)
    }

}