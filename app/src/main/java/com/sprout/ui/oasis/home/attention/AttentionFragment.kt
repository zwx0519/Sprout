package com.sprout.ui.oasis.home.attention

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
import com.sprout.adapter.oasis.home.attention.CityTrendsAdpater
import com.sprout.databinding.FragmentAttentionBinding
import com.sprout.ui.oasis.home.local.LocalFragment
import com.sprout.utils.ListDecoration
import com.sprout.viewmodel.oasis.home.attention.CityViewModel
import kotlinx.android.synthetic.main.fragment_attention.*

//TODO 首页同城页面
class AttentionFragment(var command:Int) :
    BaseFragment<CityViewModel, FragmentAttentionBinding>(R.layout.fragment_attention,CityViewModel::class.java) {

    lateinit var manager: StaggeredGridLayoutManager
    var list:MutableList<TrendsData> = mutableListOf()
    lateinit var cityTrendsAdapter: CityTrendsAdpater

    //采用伴生对象 companion object==static 只能创建一次
    companion object {
        val instance by lazy { AttentionFragment(1) }
    }

    override fun initView() {
        manager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        recy_city.layoutManager = manager
        var arr = SparseArray<Int>()
        arr.put(R.layout.layout_city_trends_item, BR.cityTrendsData)
        cityTrendsAdapter = CityTrendsAdpater(mContext,list,arr,ItemClick())
        recy_city.addItemDecoration(ListDecoration())//分割线
        recy_city.adapter = cityTrendsAdapter
    }

    override fun initVM() {
        mViewModel.list.observe(this, Observer {
            list.clear()
            list.addAll(it)
            cityTrendsAdapter.notifyDataSetChanged()
        })
    }

    override fun initData() {
        mViewModel.getTrends(command)
    }

    override fun initVariable() {

    }

    inner class ItemClick: IItemClick<TrendsData> {
        override fun itemClick(data: TrendsData) {

        }
    }
}