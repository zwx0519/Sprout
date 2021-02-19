package com.sprout.ui.oasis.issue.tags

import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinbase.bean.issue.Issue_GoodBean
import com.example.kotlinbase.model.myitem.IItemClick
import com.shop.base.BaseFragment
import com.sprout.BR
import com.sprout.R
import com.sprout.adapter.oasis.issue.Issue_GoodAdapter
import com.sprout.databinding.FragmentAdressBinding
import com.sprout.viewmodel.oasis.issue.tags.AdressViewModel
import com.sprout.viewmodel.oasis.issue.tags.GoodsViewModel
import kotlinx.android.synthetic.main.fragment_brand.*
import kotlinx.android.synthetic.main.fragment_goods.*

class GoodsFragment(var type:Int) : BaseFragment<GoodsViewModel, FragmentAdressBinding>
    (R.layout.fragment_goods, GoodsViewModel::class.java)  {

    lateinit var goodList:MutableList<Issue_GoodBean.Data>
    lateinit var goodAdapter: Issue_GoodAdapter


    override fun initView() {
        var goodArr = SparseArray<Int>()
        goodArr.put(R.layout.layout_good_item, BR.goodData)
        goodList = mutableListOf()
        recy_goods.layoutManager = LinearLayoutManager(mContext)
        goodAdapter = Issue_GoodAdapter(mContext,goodList,goodArr,GoodClick())
        recy_goods.adapter = goodAdapter
    }

    override fun initVM() {
        mViewModel.gList.observe(this, Observer {
            goodList.clear()
            goodList.addAll(it.data)
            goodAdapter.notifyDataSetChanged()
        })
    }

    override fun initData() {
        mViewModel.getGood()
    }

    override fun initVariable() {

    }

    //TODO 商品内部类
    inner class GoodClick: IItemClick<Issue_GoodBean.Data> {
        override fun itemClick(data: Issue_GoodBean.Data) {
            mActivity.intent.putExtra("name",data.name)
            mActivity.intent.putExtra("id",data.id)
            mActivity.setResult(2,mActivity.intent)
            mActivity.finish()
        }
    }
}