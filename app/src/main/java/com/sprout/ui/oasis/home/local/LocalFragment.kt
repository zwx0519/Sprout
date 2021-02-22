package com.sprout.ui.oasis.home.local

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.shop.base.BaseFragment
import com.sprout.R
import com.sprout.bean.home.local.Local_ChannelBean
import com.sprout.databinding.FragmentLocalBinding
import com.sprout.viewmodel.oasis.home.local.LocalViewModel
import kotlinx.android.synthetic.main.fragment_local.*

//TODO 首页关注页面
class LocalFragment(var command:Int) : BaseFragment<LocalViewModel, FragmentLocalBinding>
    (R.layout.fragment_local, LocalViewModel::class.java) {

    var titles:MutableList<String> = mutableListOf()
    var list:MutableList<LocalInfoFragment> = mutableListOf()
    lateinit var trendsFragmentAdapter:TrendsFragmentAdapter

    //采用伴生对象 companion object==static 只能创建一次
    companion object {
        val instance by lazy { LocalFragment(3) }
    }

    override fun initData() {
        mViewModel.getLocal_Channel()
    }

    override fun initVariable() {

    }

    override fun initView() {

        trendsFragmentAdapter = childFragmentManager?.let { TrendsFragmentAdapter(it) }!!
        mVp_home_local.adapter = trendsFragmentAdapter
        mTab_home_local.setupWithViewPager(mVp_home_local)
        mTab_home_local.tabMode = TabLayout.MODE_SCROLLABLE
    }

    override fun initVM() {
        mViewModel.Local_Channel.observe(this, Observer {
            for (i  in 0 until it.size){
                mTab_home_local.addTab(mTab_home_local.newTab().setText(it.get(i).name))
                var localInfoFragment = LocalInfoFragment(command,it.get(i).id)
                list.add(localInfoFragment)
                titles.add(it.get(i).name)
                trendsFragmentAdapter.notifyDataSetChanged()
            }
        })

    }

    inner class TrendsFragmentAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(position: Int): Fragment {
            return list.get(position)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles.get(position)
        }
    }

}