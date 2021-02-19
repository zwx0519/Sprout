package com.sprout.adapter.oasis.issue.submit

import android.content.Context
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import com.example.kotlinbase.model.myitem.IItemClick
import com.example.myshop.base.BaseAdapter
import com.sprout.BR
import com.sprout.R
import com.sprout.bean.home.local.Local_ChannelBean

class ChannelAdapter(context: Context,
                     list: List<Local_ChannelBean>,
                     layouts: SparseArray<Int>,
                     click: IItemClick<Local_ChannelBean>):
    BaseAdapter<Local_ChannelBean>(context,list,layouts,click) {

    override fun layoutId(position: Int): Int {
        return R.layout.layout_channel_item
    }

    override fun bindData(binding: ViewDataBinding, data: Local_ChannelBean, layId: Int) {
        binding.setVariable(BR.channelClick,itemClick)
    }


}