package com.sprout.ui.oasis.home.local

import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.kotlinbase.bean.issue.tag.TrendsData
import com.example.kotlinbase.model.myitem.IItemClick
import com.shop.base.BaseFragment
import com.sprout.BR
import com.sprout.R
import com.sprout.adapter.oasis.home.local.LocalInfoAdapter
import com.sprout.databinding.FragmentLocalInfoBinding
import com.sprout.ui.oasis.home.attention.AttentionFragment
import com.sprout.utils.ListDecoration
import com.sprout.viewmodel.oasis.home.local.TrendsViewModel
import kotlinx.android.synthetic.main.fragment_local_info.*

class LocalInfoFragment (var command:Int,var channelId:Int): BaseFragment<TrendsViewModel,FragmentLocalInfoBinding>
    (R.layout.fragment_local_info,TrendsViewModel::class.java) {

    lateinit var trendsAdapter:LocalInfoAdapter
    var list:MutableList<TrendsData> = mutableListOf()
    lateinit var manager: StaggeredGridLayoutManager

    override fun initData() {
        mViewModel.getTrendsList(command,channelId)
    }

    override fun initView() {
        //瀑布流布局
        manager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        // manager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        recy_trends.layoutManager = manager

        var arr = SparseArray<Int>()
        arr.put(R.layout.layout_localinfo_item, BR.trendsData)
        trendsAdapter = LocalInfoAdapter(mContext,list,arr,ItemClick())
        recy_trends.adapter = trendsAdapter
        recy_trends.addItemDecoration(ListDecoration())
    }

    override fun initVM() {
        mViewModel.list.observe(this, Observer {
            list.clear()
            list.addAll(it)
            trendsAdapter.notifyDataSetChanged()
        })
    }

    override fun initVariable() {

    }

    //TODO 条目点击
    inner class ItemClick: IItemClick<TrendsData> {
        override fun itemClick(data: TrendsData) {

        }

    }
}