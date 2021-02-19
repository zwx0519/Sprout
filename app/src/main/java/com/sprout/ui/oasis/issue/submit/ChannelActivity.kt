package com.sprout.ui.oasis.issue.submit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinbase.model.myitem.IItemClick
import com.shop.base.BaseActivity
import com.sprout.BR
import com.sprout.R
import com.sprout.adapter.oasis.issue.submit.ChannelAdapter
import com.sprout.bean.home.local.Local_ChannelBean
import com.sprout.databinding.ActivityChannelBinding
import com.sprout.viewmodel.oasis.issue.submit.ChannelViewModel
import kotlinx.android.synthetic.main.activity_channel.*

class ChannelActivity : BaseActivity<ChannelViewModel, ActivityChannelBinding>
    (R.layout.activity_channel,ChannelViewModel::class.java) {

    var list:MutableList<Local_ChannelBean> = mutableListOf()
    lateinit var adapter: ChannelAdapter
    lateinit var mIntent: Intent

    override fun initView() {
        var arr = SparseArray<Int>()
        arr.put(R.layout.layout_channel_item, BR.channel);
        adapter = ChannelAdapter(mContext,list,arr,ClickEvt())
        recy_channel.layoutManager = LinearLayoutManager(mContext)
        recy_channel.adapter = adapter
    }

    override fun initVM() {
        mViewModel.channels.observe(this, Observer {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun initData() {
        mIntent = intent
        mViewModel.getChannel()
    }

    override fun initVariable() {

    }

    //TODO 点击事件
    inner class ClickEvt: IItemClick<Local_ChannelBean> {
        override fun itemClick(data: Local_ChannelBean) {
            mIntent.putExtra("channelId",data.id)
            mIntent.putExtra("channelName",data.name)
            setResult(102,mIntent)
            finish()
        }

    }
}