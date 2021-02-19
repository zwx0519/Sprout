package com.sprout.ui.oasis.issue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
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
import com.sprout.ui.oasis.issue.tags.AdressFragment
import com.sprout.ui.oasis.issue.tags.BrandFragment
import com.sprout.ui.oasis.issue.tags.GoodsFragment
import com.sprout.viewmodel.oasis.issue.TagsViewModel
import kotlinx.android.synthetic.main.activity_tags.*

/**
 * 用来显示标签列表的页面
 */
class TagsActivity : BaseActivity<TagsViewModel, ActivityTagsBinding>
    (R.layout.activity_tags,TagsViewModel::class.java){

    var fragments:MutableList<Fragment> = mutableListOf()
    var titles:List<String> = listOf("品牌","商品","地址")

    override fun initView() {

    }

    //TODO 初始化标签对应的页面
    override fun initVM() {
        fragments.add(BrandFragment(1))
        fragments.add(GoodsFragment(2))
        fragments.add(AdressFragment(3))
        tab_tags.setupWithViewPager(viewPager)
        tab_tags.addTab(tab_tags.newTab())
        tab_tags.addTab(tab_tags.newTab())
        tab_tags.addTab(tab_tags.newTab())
        viewPager.adapter = MyFragmentAdapter(supportFragmentManager)
    }

    override fun initData() {

    }

    override fun initVariable() {

    }

    //TODO 内部适配器
    inner class MyFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return fragments.size
        }

        override fun getItem(position: Int): Fragment {
            return fragments.get(position)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position].toString()
        }
    }
}