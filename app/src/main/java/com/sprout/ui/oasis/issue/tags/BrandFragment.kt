package com.sprout.ui.oasis.issue.tags

import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinbase.bean.issue.Issue_BrandBean
import com.example.kotlinbase.model.myitem.IItemClick
import com.shop.base.BaseFragment
import com.sprout.BR
import com.sprout.R
import com.sprout.adapter.oasis.issue.Issue_BrandAdapter
import com.sprout.databinding.FragmentBrandBinding
import com.sprout.viewmodel.oasis.issue.tags.BrandViewModel
import kotlinx.android.synthetic.main.activity_tags.*
import kotlinx.android.synthetic.main.fragment_brand.*

//TODO 品牌的显示列表页面
class BrandFragment(var type:Int) : BaseFragment<BrandViewModel,FragmentBrandBinding>
    (R.layout.fragment_brand,BrandViewModel::class.java) {

    lateinit var brandList:MutableList<Issue_BrandBean.Data>
    lateinit var brandAdapter: Issue_BrandAdapter

    override fun initView() {
        var brandArr = SparseArray<Int>()
        brandArr.put(R.layout.layout_brand_item, BR.brandData)
        brandList = mutableListOf()
        recy_brand.layoutManager = LinearLayoutManager(mContext)
        brandAdapter = Issue_BrandAdapter(mContext,brandList,brandArr,BrandClick())
        recy_brand.adapter = brandAdapter
    }

    override fun initVM() {
        mViewModel.bList.observe(this, Observer {
            brandList.clear()
            brandList.addAll(it.data)
            brandAdapter.notifyDataSetChanged()
        })

    }

    override fun initData() {
        mViewModel.getBrand()
    }

    override fun initVariable() {

    }

    //TODO 品牌内部类
    inner class BrandClick: IItemClick<Issue_BrandBean.Data> {
        override fun itemClick(data: Issue_BrandBean.Data) {
            mActivity.intent.putExtra("name",data.name)
            mActivity.intent.putExtra("id",data.id)
            mActivity.setResult(1,  mActivity.intent)
            mActivity.finish()
        }
    }

}