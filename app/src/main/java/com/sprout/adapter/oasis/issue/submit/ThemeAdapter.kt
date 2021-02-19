package com.sprout.adapter.oasis.issue.submit

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.kotlinbase.bean.issue.tag.ThemeData
import com.example.kotlinbase.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.sprout.BR
import com.sprout.R

class ThemeAdapter(
    context: Context,
    list: List<ThemeData>,
    layouts: SparseArray<Int>,
    click: IItemClick<ThemeData>
): BaseAdapter<ThemeData>(context,list,layouts,click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_theme_item
    }

    override fun bindData(binding: ViewDataBinding, data: ThemeData, layId: Int) {
        binding.setVariable(BR.themeClick,itemClick)
    }


}