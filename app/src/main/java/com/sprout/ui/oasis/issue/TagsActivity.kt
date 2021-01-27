package com.sprout.ui.oasis.issue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinbase.bean.issue.Issue_BrandBean
import com.example.kotlinbase.bean.issue.Issue_GoodBean
import com.example.kotlinbase.model.myitem.IItemClick
import com.shop.base.BaseActivity
import com.sprout.BR
import com.sprout.R
import com.sprout.adapter.oasis.issue.Issue_BrandAdapter
import com.sprout.adapter.oasis.issue.Issue_GoodAdapter
import com.sprout.databinding.ActivityTagsBinding
import com.sprout.viewmodel.oasis.issue.TagsViewModel
import kotlinx.android.synthetic.main.activity_tags.*

class TagsActivity : BaseActivity<TagsViewModel, ActivityTagsBinding>
    (R.layout.activity_tags,TagsViewModel::class.java){

    lateinit var brandList:MutableList<Issue_BrandBean.Data>
    lateinit var brandAdapter:Issue_BrandAdapter

    lateinit var goodList:MutableList<Issue_GoodBean.Data>
    lateinit var goodAdapter: Issue_GoodAdapter

    override fun initView() {
        mRlv_tags.layoutManager = LinearLayoutManager(this)

        var brandArr = SparseArray<Int>()
        brandArr.put(R.layout.layout_brand_item, BR.brandData)
        brandList = mutableListOf()
        brandAdapter = Issue_BrandAdapter(this,brandList,brandArr,BrandClick())

        var goodArr = SparseArray<Int>()
        goodArr.put(R.layout.layout_good_item, BR.goodData)
        goodList = mutableListOf()
        goodAdapter = Issue_GoodAdapter(this,goodList,goodArr,GoodClick())

        mDataBinding.tagClick = TagsClick()
    }

    override fun initVM() {
        mViewModel.bList.observe(this, Observer {
            brandList.clear()
            brandList.addAll(it.data)
            mRlv_tags.adapter = brandAdapter
        })

        mViewModel.gList.observe(this, Observer {
            goodList.clear()
            goodList.addAll(it.data)
            mRlv_tags.adapter = goodAdapter
        })
    }

    //TODO 品牌内部类
    inner class BrandClick: IItemClick<Issue_BrandBean.Data> {
        override fun itemClick(data: Issue_BrandBean.Data) {
            intent.putExtra("name",data.name)
            intent.putExtra("id",data.id)
            setResult(1,intent)
            finish()
        }

    }

    //TODO 商品内部类
    inner class GoodClick:IItemClick<Issue_GoodBean.Data>{
        override fun itemClick(data: Issue_GoodBean.Data) {
            intent.putExtra("name",data.name)
            intent.putExtra("id",data.id)
            setResult(2,intent)
            finish()
        }
    }

    override fun initData() {

    }

    override fun initVariable() {

    }

    inner class TagsClick{
        fun click(type:Int){
            when(type){
                1->{
                    if(brandList.size == 0){
                        mViewModel.getBrand()
                    }else{
                        mRlv_tags.adapter = brandAdapter
                    }
                }
                2->{
                    if(goodList.size == 0){
                        mViewModel.getGood()
                    }else{
                        mRlv_tags.adapter = goodAdapter
                    }
                }
                3->{

                }
                4->{

                }
            }
        }
    }
}